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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank; 

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(nullable = false) 
    @NotBlank(message = "A titulação é obrigatória")
    private String titulacao;

    @Column(nullable = false, unique = true) 
    @Email(message = "Email inválido") 
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
        
    public Professor() {
    }

    public Professor(Long id, String nome, String titulacao, String email) {
        this.id = id;
        this.nome = nome;
        this.titulacao = titulacao;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<AreaPesquisa> getAreasPesquisa() {
        return areasPesquisa;
    }

    public void setAreasPesquisa(List<AreaPesquisa> areasPesquisa) {
        this.areasPesquisa = areasPesquisa;
    }

     

    
}
