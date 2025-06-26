package com.siteprofessor.backend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
import jakarta.validation.constraints.*;

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Column(nullable = false) 
    @NotBlank(message = "O objetivo é obrigatório")
    private String objetivo;

    private String idealizadores;

    @Column(name = "data_criacao", nullable = false) 
    @NotNull(message = "A data de criação é obrigatória")
    private LocalDate dataCriacao;

    private String localCriacao;

    private String detalhes;

    private String imagem;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)  
    @NotNull(message = "É necessário informar o professor responsável")
    @JsonIgnoreProperties("projetos")  //Impede o loop infinito de projeto professor professor projeto 
    private Professor professor;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getIdealizadores() {
        return idealizadores;
    }

    public void setIdealizadores(String idealizadores) {
        this.idealizadores = idealizadores;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getLocalCriacao() {
        return localCriacao;
    }

    public void setLocalCriacao(String localCriacao) {
        this.localCriacao = localCriacao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}