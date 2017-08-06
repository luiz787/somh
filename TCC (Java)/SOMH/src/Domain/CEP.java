/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author andro
 */
public class CEP {
    private Cidade cidade;
    private Integer nroCEP;

    public CEP() {
    }

    public CEP(Cidade cidade, Integer nroCEP) {
        this.cidade = cidade;
        this.nroCEP = nroCEP;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Integer getNroCEP() {
        return nroCEP;
    }

    public void setNroCEP(Integer nroCEP) {
        this.nroCEP = nroCEP;
    }
}
