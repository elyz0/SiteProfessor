package com.siteprofessor.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.AreaPesquisa;

public interface AreaPesquisaRepository extends JpaRepository<AreaPesquisa, Long> {
    List<AreaPesquisa> findByProfessorId(Long professorId);
}
