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
public class Cidade {
    private UF uf;
    private Long id;
    private String nome;

    public Cidade() {
    }

    public Cidade(UF uf, Long id, String nome) {
        this.uf = uf;
        this.id = id;
        this.nome = nome;
    }
    
    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
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
}
