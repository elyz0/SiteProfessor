package com.siteprofessor.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.AreaPesquisa;
import com.siteprofessor.backend.repository.AreaPesquisaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaPesquisaService {

    private final AreaPesquisaRepository repository;

    public List<AreaPesquisa> listar() {
        return repository.findAll();
    }

    public AreaPesquisa salvar(AreaPesquisa area) {
        return repository.save(area);
    }

    public AreaPesquisa atualizar(Long id, AreaPesquisa dados) {
        dados.setId(id);
        return repository.save(dados);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
