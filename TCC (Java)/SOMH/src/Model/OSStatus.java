/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author andro
 */
public class OSStatus {
    private int nro_OS;
    private Long dat_Ocorrencia;
    private int cod_Usuario;
    private int cod_Status;

    public OSStatus() {
    }

    public OSStatus(int nro_OS, Long dat_Ocorrencia, int cod_Usuario, int cod_Status) {
        this.nro_OS = nro_OS;
        this.dat_Ocorrencia = dat_Ocorrencia;
        this.cod_Usuario = cod_Usuario;
        this.cod_Status = cod_Status;
    }

        
    
    public int getNro_OS() {
        return nro_OS;
    }

    public void setNro_OS(int nro_OS) {
        this.nro_OS = nro_OS;
    }

    public Long getDat_Ocorrencia() {
        return dat_Ocorrencia;
    }

    public void setDat_Ocorrencia(Long dat_Ocorrencia) {
        this.dat_Ocorrencia = dat_Ocorrencia;
    }

    public int getCod_Usuario() {
        return cod_Usuario;
    }

    public void setCod_Usuario(int cod_Usuario) {
        this.cod_Usuario = cod_Usuario;
    }

    public int getCod_Status() {
        return cod_Status;
    }

    public void setCod_Status(int cod_Status) {
        this.cod_Status = cod_Status;
    }
    
    
}
