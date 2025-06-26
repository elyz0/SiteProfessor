package com.siteprofessor.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.Publicacao;
import com.siteprofessor.backend.repository.PublicacaoRepository;

@Service
public class PublicacaoService {

    private final PublicacaoRepository repository;

    public PublicacaoService(PublicacaoRepository repository) {
        this.repository = repository;
    }

    public List<Publicacao> listar() {
        return repository.findAll();
    }

    public Publicacao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Publicação não encontrada"));
    }

    public Publicacao salvar(Publicacao publicacao) {
        return repository.save(publicacao);
    }

    public Publicacao atualizar(Long id, Publicacao dados) {
        dados.setId(id);
        return repository.save(dados);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
