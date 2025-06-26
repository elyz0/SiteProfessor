package com.siteprofessor.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.AreaPesquisa;
import com.siteprofessor.backend.repository.AreaPesquisaRepository;

@Service
public class AreaPesquisaService {

    private final AreaPesquisaRepository repository;

    public AreaPesquisaService(AreaPesquisaRepository repository) {
        this.repository = repository;
    }

    public List<AreaPesquisa> listar() {
        return repository.findAll();
    }

    public AreaPesquisa salvar(AreaPesquisa area) {
        return repository.save(area);
    }

    public AreaPesquisa atualizar(Long id, AreaPesquisa dados) {
        AreaPesquisa existente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Área de pesquisa não encontrada")); 
         
        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao()); 

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
