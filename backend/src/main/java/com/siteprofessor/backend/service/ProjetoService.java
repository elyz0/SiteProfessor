package com.siteprofessor.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.Projeto;
import com.siteprofessor.backend.repository.ProjetoRepository;


@Service

public class ProjetoService {

    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public List<Projeto> listar() {
        return repository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    public Projeto salvar(Projeto projeto) {
        return repository.save(projeto);
    }

    public Projeto atualizar(Long id, Projeto dados) {
        Projeto existente = buscarPorId(id); 

        existente.setTitulo(dados.getTitulo());
        existente.setObjetivo(dados.getObjetivo());
        existente.setIdealizadores(dados.getIdealizadores());
        existente.setLocalCriacao(dados.getLocalCriacao());
        existente.setDetalhes(dados.getDetalhes());
        existente.setImagem(dados.getImagem()); 

        return repository.save(dados);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
