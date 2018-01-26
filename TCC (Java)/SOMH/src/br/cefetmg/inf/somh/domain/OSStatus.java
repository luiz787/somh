package br.cefetmg.inf.somh.domain;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OSStatus {
    private OS os;
    private Long datOcorrencia;
    private Usuario usuario;
    private Status status;

    public OSStatus() {
    }

    public OSStatus(OS os, Long datOcorrencia, Usuario usuario, Status status) {
        this.os = os;
        this.datOcorrencia = datOcorrencia;
        this.usuario = usuario;
        this.status = status;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Long getDatOcorrencia() {
        return datOcorrencia;
    }

    public void setDatOcorrencia(Long dat_Ocorrencia) {
        this.datOcorrencia = dat_Ocorrencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
