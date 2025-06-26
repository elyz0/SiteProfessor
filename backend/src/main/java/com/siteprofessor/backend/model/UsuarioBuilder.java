package com.siteprofessor.backend.model;

public class UsuarioBuilder {

    private Long id;
    private String email;
    private String nome;
    private String senha;
    private Perfil perfil;
    private String googleId;

    public UsuarioBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder comPerfil(Perfil perfil) {
        this.perfil = perfil;
        return this;
    }

    public UsuarioBuilder comGoogleId(String googleId) {
        this.googleId = googleId;
        return this;
    }

    public Usuario construir() {
        return new Usuario(id, email, nome, senha, perfil, googleId);
    }
}
