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
public class Equipamento {
    private int seq_Equipto;
    private int cod_Marca;
    private String des_Equipto;
    private String des_Modelo;
    private String des_Componentes;
    private int nro_Serie;

    public Equipamento() {
    }

    public Equipamento(int seq_Equipto, int cod_Marca, String des_Equipto, String des_Modelo, int nro_Serie) {
        this.seq_Equipto = seq_Equipto;
        this.cod_Marca = cod_Marca;
        this.des_Equipto = des_Equipto;
        this.des_Modelo = des_Modelo;
        this.nro_Serie = nro_Serie;
    }

    public String getDes_Componentes() {
        return des_Componentes;
    }

    public void setDes_Componentes(String des_Componentes) {
        this.des_Componentes = des_Componentes;
    }
    
    public int getSeq_Equipto() {
        return seq_Equipto;
    }

    public void setSeq_Equipto(int seq_Equipto) {
        this.seq_Equipto = seq_Equipto;
    }

    public int getCod_Marca() {
        return cod_Marca;
    }

    public void setCod_Marca(int cod_Marca) {
        this.cod_Marca = cod_Marca;
    }

    public String getDes_Equipto() {
        return des_Equipto;
    }

    public void setDes_Equipto(String des_Equipto) {
        this.des_Equipto = des_Equipto;
    }

    public String getDes_Modelo() {
        return des_Modelo;
    }

    public void setDes_Modelo(String des_Modelo) {
        this.des_Modelo = des_Modelo;
    }

    public int getNro_Serie() {
        return nro_Serie;
    }

    public void setNro_Serie(int nro_Serie) {
        this.nro_Serie = nro_Serie;
    }
    
    
}
