package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.model.Perfil;
import com.siteprofessor.backend.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository; 
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository; 
        this.passwordEncoder = passwordEncoder;
    }

    private boolean isAdmin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario)) return false;
        var usuario = (Usuario) auth.getPrincipal();
        return usuario.getPerfil() == Perfil.ADMINISTRADOR;
    }

    @PostMapping("/cadastrar-auxiliar")
    public ResponseEntity<?> cadastrarAuxiliar(@RequestBody Usuario novoUsuario) {
        if (!isAdmin()) {
            return ResponseEntity.status(403).body("Apenas administradores podem cadastrar auxiliares.");
        }

        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Já existe um usuário com esse e-mail.");
        }

        novoUsuario.setPerfil(Perfil.AUXILIAR); 
        if (novoUsuario.getSenha() != null && !novoUsuario.getSenha().isBlank()) {
            novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        } 
        Usuario salvo = usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok(salvo);
    }
}
