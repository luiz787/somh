package br.cefetmg.inf.somh.domain;

public class OSAcessorio {
    private OS os;
    private Acessorio acessorio;

    public OSAcessorio() {
    }

    public OSAcessorio(OS os, Acessorio acessorio) {
        this.os = os;
        this.acessorio = acessorio;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }
}
