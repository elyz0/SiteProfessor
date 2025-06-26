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

import com.siteprofessor.backend.model.Hobby;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.service.HobbyService;


@RestController
@RequestMapping("/api/hobbies")
public class HobbyController {

    private final HobbyService service; 

    public HobbyController(HobbyService service) {
        this.service = service;
    }
     
    private boolean isAdminOrAuxiliar() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario)) return false;
        var usuario = (Usuario) auth.getPrincipal();
        return usuario.getPerfil().equals("ADMINISTRADOR") || usuario.getPerfil().equals("AUXILIAR");
    }

    @GetMapping
    public ResponseEntity<List<Hobby>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Hobby> criar(@RequestBody Hobby hobby) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.salvar(hobby));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hobby> atualizar(@PathVariable Long id, @RequestBody Hobby dados) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
