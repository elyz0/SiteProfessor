package com.siteprofessor.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.AreaPesquisa;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.service.AreaPesquisaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/areas")

public class AreaPesquisaController {

    private final AreaPesquisaService service;

    public AreaPesquisaController(AreaPesquisaService service) {
        this.service = service;
    } 

    private boolean isAdminOrAuxiliar() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario)) return false;
        var usuario = (Usuario) auth.getPrincipal();
        return usuario.getPerfil().equals("ADMINISTRADOR") || usuario.getPerfil().equals("AUXILIAR");
    } 

    @GetMapping
    public ResponseEntity<List<AreaPesquisa>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<AreaPesquisa> criar(@Valid @RequestBody AreaPesquisa area) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.salvar(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaPesquisa> atualizar(@PathVariable Long id, @Valid @RequestBody AreaPesquisa area) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
