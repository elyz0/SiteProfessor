package com.siteprofessor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siteprofessor.backend.model.MensagemContato;

public interface MensagemContatoRepository extends JpaRepository<MensagemContato, Long> {
}
