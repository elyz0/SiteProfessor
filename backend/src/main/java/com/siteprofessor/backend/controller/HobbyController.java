package com.siteprofessor.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.Hobby;
import com.siteprofessor.backend.service.HobbyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hobbies")
@RequiredArgsConstructor
public class HobbyController {

    private final HobbyService service;

    @GetMapping
    public ResponseEntity<List<Hobby>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Hobby> criar(@RequestBody Hobby hobby) {
        return ResponseEntity.ok(service.salvar(hobby));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hobby> atualizar(@PathVariable Long id, @RequestBody Hobby dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
