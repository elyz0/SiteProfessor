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

import com.siteprofessor.backend.model.AreaPesquisa;
import com.siteprofessor.backend.service.AreaPesquisaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
public class AreaPesquisaController {

    private final AreaPesquisaService service;

    @GetMapping
    public ResponseEntity<List<AreaPesquisa>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<AreaPesquisa> criar(@RequestBody AreaPesquisa area) {
        return ResponseEntity.ok(service.salvar(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaPesquisa> atualizar(@PathVariable Long id, @RequestBody AreaPesquisa area) {
        return ResponseEntity.ok(service.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
