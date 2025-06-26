package com.siteprofessor.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.Professor;
import com.siteprofessor.backend.repository.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor getDadosProfessor() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

    public Professor atualizarProfessor(Professor dados) {
        Optional<Professor> existente = repository.findById(dados.getId());
        if (existente.isPresent()) {
            dados.setDataAtualizacao(java.time.LocalDateTime.now());
            return repository.save(dados);
        }
        throw new RuntimeException("Professor não encontrado");
    } 
     
    public boolean temProfessorCadastrado() {
        return repository.count() > 0;
    }

    public Professor criarProfessor(Professor novo) {
        novo.setDataAtualizacao(java.time.LocalDateTime.now());
        return repository.save(novo);
    }
}
