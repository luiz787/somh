/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.domain;

/**
 *
 * @author andro
 */
public class Usuario {
    private Long id;
    private String nome;
    private Perfil perfil;
    private String senha;

    public Usuario() {
    }

    public Usuario(Long id, String nome, Perfil perfil, String senha) {
        this.id = id;
        this.nome = nome;
        this.perfil = perfil;
        this.senha = senha;
    }
    
    public Long getId() {
        return id;
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
