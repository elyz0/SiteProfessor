package com.siteprofessor.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
    List<Publicacao> findByAno(int ano);
}
