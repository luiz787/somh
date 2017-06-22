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
public class Marca {
    private int cod_Marca;
    private String nom_Marca;

    public Marca() {
    }

    public Marca(int cod_Marca, String nom_Marca) {
        this.cod_Marca = cod_Marca;
        this.nom_Marca = nom_Marca;
    }

    public int getCod_Marca() {
        return cod_Marca;
    }

    public void setCod_Marca(int cod_Marca) {
        this.cod_Marca = cod_Marca;
    }

    public String getNom_Marca() {
        return nom_Marca;
    }

    public void setNom_Marca(String nom_Marca) {
        this.nom_Marca = nom_Marca;
    }
    
    
}
