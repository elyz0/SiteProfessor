package com.siteprofessor.backend.service;

import com.siteprofessor.backend.model.Professor;
import com.siteprofessor.backend.repository.ProfessorRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService service;

    @Mock
    private ProfessorRepository repository;

    private Professor professor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        professor = new Professor();
            professor.setId(1L);
            professor.setNome("Dr. João Silva");
            professor.setEmail("joao@site.com");
            professor.setTitulacao("PhD");
    }

    @Test
    void deveRetornarProfessorExistente() {
        when(repository.findAll()).thenReturn(List.of(professor));

        Professor resultado = service.getDadosProfessor();
        assertNotNull(resultado);
        assertEquals("Dr. João Silva", resultado.getNome());
    }

    @Test
    void deveAtualizarProfessor() {
        when(repository.findById(1L)).thenReturn(Optional.of(professor));
        when(repository.save(any())).thenReturn(professor);

        Professor atualizado = service.atualizarProfessor(professor);
        assertEquals("joao@site.com", atualizado.getEmail());
    }
}
