package br.cefetmg.inf.somh.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Acessorio {
    private Long id;
    private final StringProperty nomeAcessorio;
    
    public Acessorio(Long id, String nomeAcessorio) {
        this.id = id;
        this.nomeAcessorio = new SimpleStringProperty(nomeAcessorio);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAcessorio() {
        return nomeAcessorio.get();
    }
    public void setNomeAcessorio(String nomeAcessorio) {
        this.nomeAcessorio.set(nomeAcessorio);
    }
    public StringProperty NomeAcessorioProperty() {
        return nomeAcessorio;
    }
}
