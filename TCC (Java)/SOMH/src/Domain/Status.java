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
public class Status {
    private Integer id;
    private String nome;

    public Status() {
    }
    
    //Contrutor teste (Será removido futuramente)
    
    public Status(Integer id) {
        this.id = id;
    }

    public Status(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
