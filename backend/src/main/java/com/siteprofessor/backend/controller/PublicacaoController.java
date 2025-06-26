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

import com.siteprofessor.backend.model.Publicacao;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.service.PublicacaoService;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService service; 

    public PublicacaoController(PublicacaoService service) {
        this.service = service;
    }
     
    private boolean isAdminOrAuxiliar() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario)) return false;
        var usuario = (Usuario) auth.getPrincipal();
        return usuario.getPerfil().equals("ADMINISTRADOR") || usuario.getPerfil().equals("AUXILIAR");
    }

    @GetMapping
    public ResponseEntity<List<Publicacao>> listar() { 
        return ResponseEntity.ok(service.listar());
    } 
     
    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Publicacao> criar(@RequestBody Publicacao p) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.salvar(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> atualizar(@PathVariable Long id, @RequestBody Publicacao p) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(service.atualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) { 
        if (!isAdminOrAuxiliar()) return ResponseEntity.status(403).build();
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
 