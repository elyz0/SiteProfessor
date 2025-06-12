package com.siteprofessor.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    List<Hobby> findByProfessorId(Long professorId);
}
