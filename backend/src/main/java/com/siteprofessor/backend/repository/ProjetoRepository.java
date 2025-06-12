package com.siteprofessor.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByProfessorId(Long professorId);
}
