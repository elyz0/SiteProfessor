package com.siteprofessor.backend.service;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.model.UsuarioBuilder;
import com.siteprofessor.backend.repository.UsuarioRepository;
import com.siteprofessor.backend.util.JwtUtil;
import org.springframework.stereotype.Service; 
import com.google.api.client.json.jackson2.JacksonFactory; 
import com.siteprofessor.backend.model.Perfil;
import java.util.Collections;

@Service
public class GoogleAuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public GoogleAuthService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

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
                        //em um primeiro acesso vai criar novo visualizador
                        Usuario novo = new UsuarioBuilder()
                                .comEmail(email)
                                .comNome(nome)
                                .comPerfil(Perfil.VISUALIZADOR)
                                .comGoogleId(googleId)
                                .construir();
                        return usuarioRepository.save(novo);
                    });

            return jwtUtil.gerarToken(usuario);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar com Google: " + e.getMessage());
        }
    }
}
