package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder; 

import com.siteprofessor.backend.model.MensagemContato;
import com.siteprofessor.backend.service.MensagemContatoService; 
import com.siteprofessor.backend.model.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contato")

public class MensagemContatoController {

    private final MensagemContatoService service;

    public MensagemContatoController(MensagemContatoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MensagemContato> enviar(@Valid @RequestBody MensagemContato msg) {
        return ResponseEntity.ok(service.enviar(msg));
    } 
     
    
    @GetMapping
    public ResponseEntity<List<MensagemContato>> listarMensagens() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario usuario)) {
            return ResponseEntity.status(403).build();
        }

        if (!usuario.getPerfil().equals("ADMINISTRADOR") && !usuario.getPerfil().equals("AUXILIAR")) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(service.listar());
    }
}
