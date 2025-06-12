package com.siteprofessor.backend.service;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.repository.UsuarioRepository;
import com.siteprofessor.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service; 
import com.google.api.client.json.jackson2.JacksonFactory; 

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public String autenticarViaGoogle(String idTokenString) {
        try {
            var transport = GoogleNetHttpTransport.newTrustedTransport();
            var jsonFactory = JacksonFactory.getDefaultInstance();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.emptyList()) // ou use clientId fixo se quiser restringir
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                throw new RuntimeException("Token inválido");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String nome = (String) payload.get("name");
            String googleId = payload.getSubject();

            Usuario usuario = usuarioRepository.findByGoogleId(googleId)
                    .orElseGet(() -> {
                        // Primeiro acesso: cria novo visualizador
                        Usuario novo = Usuario.builder()
                                .email(email)
                                .nome(nome)
                                .perfil("VISUALIZADOR")
                                .googleId(googleId)
                                .build();
                        return usuarioRepository.save(novo);
                    });

            return jwtUtil.gerarToken(usuario);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar com Google: " + e.getMessage());
        }
    }
}
