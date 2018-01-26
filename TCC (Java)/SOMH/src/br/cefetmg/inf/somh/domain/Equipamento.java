package br.cefetmg.inf.somh.domain;

public class Equipamento {
    private Long id;
    private String desMarca;
    private String desEquipto;
    private String desModelo;
    private String desComponentes;
    private Integer nroSerie;

    public Equipamento() {
    }

    public Equipamento(Long id, String desMarca, String desEquipto, String desModelo, String desComponentes, Integer nroSerie) {
        this.id = id;
        this.desMarca = desMarca;
        this.desEquipto = desEquipto;
        this.desModelo = desModelo;
        this.desComponentes = desComponentes;
        this.nroSerie = nroSerie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesMarca() {
        return desMarca;
    }

    public void setDesMarca(String desMarca) {
        this.desMarca = desMarca;
    }

    public String getDesEquipto() {
        return desEquipto;
    }

    public void setDesEquipto(String desEquipto) {
        this.desEquipto = desEquipto;
    }

    public String getDesModelo() {
        return desModelo;
    }

    public void setDesModelo(String desModelo) {
        this.desModelo = desModelo;
    }

    public String getDesComponentes() {
        return desComponentes;
    }

    public void setDesComponentes(String desComponentes) {
        this.desComponentes = desComponentes;
    }

    public Integer getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(Integer nroSerie) {
        this.nroSerie = nroSerie;
    }
}
