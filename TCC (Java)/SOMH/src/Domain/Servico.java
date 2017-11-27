/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Luiz
 */
public class Servico {

    public Servico(Long id, String descricao, Long quantidadeTempo, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.quantidadeTempo = quantidadeTempo;
        this.valor = valor;
    }
    Long id;
    String descricao;
    Long quantidadeTempo;
    String valor;

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

    public Long getQuantidadeTempo() {
        return quantidadeTempo;
    }

    public void setQuantidadeTempo(Long quantidadeTempo) {
        this.quantidadeTempo = quantidadeTempo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
