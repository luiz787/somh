/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author andro
 */
public class OS {
    private int nro_OS;
    private int cod_Cpf_Cnpj;
    private int seq_Equipto;
    private String txt_Reclamacao;
    private double vlr_Desconto;
    private double per_Desconto;
    private double vlr_Frete;

    public OS() {
    }
    
    
    public OS(int cod_Cpf_Cnpj, int seq_Equipto, String txt_Reclamacao) {
        this.cod_Cpf_Cnpj = cod_Cpf_Cnpj;
        this.seq_Equipto = seq_Equipto;
        this.txt_Reclamacao = txt_Reclamacao;
    }

    public int getNro_OS() {
        return nro_OS;
    }

    public void setNro_OS(int nro_OS) {
        this.nro_OS = nro_OS;
    }

    public int getCod_Cpf_Cnpj() {
        return cod_Cpf_Cnpj;
    }

    public void setCod_Cpf_Cnpj(int cod_Cpf_Cnpj) {
        this.cod_Cpf_Cnpj = cod_Cpf_Cnpj;
    }

    public int getSeq_Equipto() {
        return seq_Equipto;
    }

    public void setSeq_Equipto(int seq_Equipto) {
        this.seq_Equipto = seq_Equipto;
    }

    public String getTxt_Reclamacao() {
        return txt_Reclamacao;
    }

    public void setTxt_Reclamacao(String txt_Reclamacao) {
        this.txt_Reclamacao = txt_Reclamacao;
    }

    public double getVlr_Desconto() {
        return vlr_Desconto;
    }

    public void setVlr_Desconto(double vlr_Desconto) {
        this.vlr_Desconto = vlr_Desconto;
    }

    public double getPer_Desconto() {
        return per_Desconto;
    }

    public void setPer_Desconto(double per_Desconto) {
        this.per_Desconto = per_Desconto;
    }

    public double getVlr_Frete() {
        return vlr_Frete;
    }

    public void setVlr_Frete(double vlr_Frete) {
        this.vlr_Frete = vlr_Frete;
    }
    
}
