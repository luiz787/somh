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
public class OSItemServico {

    public OSItemServico(OS os, Servico servico, int quantidadeServico, Double valorServico, boolean situacao) {
        this.os = os;
        this.servico = servico;
        this.quantidadeServico = quantidadeServico;
        this.valorServico = valorServico;
        this.situacao = situacao;
    }
    
    OS os;
    Servico servico;
    int quantidadeServico;
    Double valorServico;
    boolean situacao;

    public OSItemServico() {}

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public int getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(int quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
}
