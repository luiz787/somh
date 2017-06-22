/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aluno
 */
public class Acessorio {
    private int cod_Acessorio;
    private String nom_Acessorio;

    public Acessorio() {
    }

    public Acessorio(int cod_Acessorio, String nom_Acessorio) {
        this.cod_Acessorio = cod_Acessorio;
        this.nom_Acessorio = nom_Acessorio;
    }

    public int getCod_Acessorio() {
        return cod_Acessorio;
    }

    public void setCod_Acessorio(int cod_Acessorio) {
        this.cod_Acessorio = cod_Acessorio;
    }

    public String getNom_Acessorio() {
        return nom_Acessorio;
    }

    public void setNom_Acessorio(String nom_Acessorio) {
        this.nom_Acessorio = nom_Acessorio;
    }
    
    
}
