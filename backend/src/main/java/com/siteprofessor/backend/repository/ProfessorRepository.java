package com.siteprofessor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
