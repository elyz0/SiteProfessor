package com.siteprofessor.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.Hobby;
import com.siteprofessor.backend.repository.HobbyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository repository;

    public List<Hobby> listar() {
        return repository.findAll();
    }

    public Hobby salvar(Hobby hobby) {
        return repository.save(hobby);
    }

    public Hobby atualizar(Long id, Hobby dados) {
        dados.setId(id);
        return repository.save(dados);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
