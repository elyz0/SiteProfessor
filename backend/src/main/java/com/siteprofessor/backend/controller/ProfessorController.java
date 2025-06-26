package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.Professor;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.service.ProfessorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }
 
    private boolean isAdminOrAuxiliar() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario)) return false;
        var usuario = (Usuario) auth.getPrincipal();
        return usuario.getPerfil().equals("ADMINISTRADOR") || usuario.getPerfil().equals("AUXILIAR");
    }

    @GetMapping
    public ResponseEntity<Professor> obter() {
        return ResponseEntity.ok(service.getDadosProfessor());
    }

    @PutMapping
    public ResponseEntity<Professor> atualizar(@RequestBody Professor dados) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.atualizarProfessor(dados));
    } 
     
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Professor novoProfessor) {
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();

        //isso aqui tem q servir para limitar a 1 professor
        if (service.temProfessorCadastrado()) {
            return ResponseEntity.badRequest().body("Já existe um professor cadastrado.");
        }

        return ResponseEntity.status(201).body(service.criarProfessor(novoProfessor));
    }
}
