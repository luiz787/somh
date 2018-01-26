/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.domain;

/**
 *
 * @author Luiz
 */
public class Peca {
    
    public Peca(){}

    public Peca(Long id, String descricao, double precoVenda, String marca) {
        this.id = id;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.marca = marca;
    }
    
    private Long id;
    private String descricao;
    private double precoVenda;
    private String marca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
