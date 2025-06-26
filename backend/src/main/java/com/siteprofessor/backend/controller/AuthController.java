package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.Perfil;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.repository.UsuarioRepository;
import com.siteprofessor.backend.service.GoogleAuthService;
import com.siteprofessor.backend.util.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; 
     
    private final GoogleAuthService googleAuthService;

    public AuthController(AuthenticationManager authManager, GoogleAuthService googleAuthService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.authManager = authManager;
        this.googleAuthService = googleAuthService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.senha()));

        Usuario usuario = usuarioRepository.findByEmail(login.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtUtil.gerarToken(usuario); 
         
        // O frontend deve enviar esse token em cada requisição protegida no cabeçalho HTTP como: Authorization: Bearer <token>
        return ResponseEntity.ok(new TokenResponse(token, usuario.getPerfil()));
    }
 
    @PostMapping("/google")
    public ResponseEntity<?> loginGoogle(@RequestBody GoogleTokenRequest body) {
        String token = googleAuthService.autenticarViaGoogle(body.tokenGoogle());
        Usuario usuario = usuarioRepository.findByGoogleId(body.googleId()).orElseThrow();
        return ResponseEntity.ok(new TokenResponse(token, usuario.getPerfil())); //esse tbm
    }

    public record GoogleTokenRequest(String tokenGoogle, String googleId) {}
 

    public record LoginRequest(String email, String senha) {}
    public record TokenResponse(String token, String perfil) {
        public TokenResponse(String token, Perfil perfilEnum) {
            this(token, perfilEnum.name()); // converte enum Perfil em String
        }
    }
}
