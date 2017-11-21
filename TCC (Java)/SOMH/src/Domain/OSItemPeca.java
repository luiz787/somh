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
public class OSItemPeca {

    public OSItemPeca(OS os, Long id, int qtd, double valorVenda, String situacao) {
        this.os = os;
        this.id = id;
        this.qtd = qtd;
        this.valorVenda = valorVenda;
        this.situacao = situacao;
    }
    
    public OSItemPeca() {}
    
    private OS os;
    private Long id;
    private int qtd;
    private double valorVenda;
    private String situacao;

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
    
}
