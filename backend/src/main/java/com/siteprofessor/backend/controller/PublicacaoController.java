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

import com.siteprofessor.backend.model.Publicacao;
import com.siteprofessor.backend.service.PublicacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/publicacoes")
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService service;

    @GetMapping
    public ResponseEntity<List<Publicacao>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Publicacao> criar(@RequestBody Publicacao p) {
        return ResponseEntity.ok(service.salvar(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> atualizar(@PathVariable Long id, @RequestBody Publicacao p) {
        return ResponseEntity.ok(service.atualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
 