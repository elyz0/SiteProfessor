package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.Professor;
import com.siteprofessor.backend.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<Professor> obter() {
        return ResponseEntity.ok(service.getDadosProfessor());
    }

    @PutMapping
    public ResponseEntity<Professor> atualizar(@RequestBody Professor dados) {
        return ResponseEntity.ok(service.atualizarProfessor(dados));
    }
}
