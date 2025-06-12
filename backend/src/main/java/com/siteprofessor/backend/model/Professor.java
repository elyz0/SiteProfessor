package com.siteprofessor.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String titulacao;

    @Column(nullable = false, unique = true)
    private String email;

    private String lattes;
    private String orcid;
    private String bio;
    private String foto;

    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Publicacao> publicacoes;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<AreaPesquisa> areasPesquisa; 
       
    public Long getId() {
        return this.id;
    }

    public void setDataAtualizacao(java.time.LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }  
      

     

    
}
