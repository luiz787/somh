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
public class Cliente {
    private Long codCPF_CNPJ;
    private CEP cep;
    private String nome;
    private String email;
    private Integer nroTelefoneCelular;
    private String endereco;
    private Integer nroTelefoneFixo;
    private Integer nroTelefoneCelular2;
    private Integer nroEndereco;
    private String descricaoComplemento;

    public Cliente() {
    }
    
    //Contrutor teste (Será removido futuramente)

    public Cliente(Long codCPF_CNPJ) {
        this.codCPF_CNPJ = codCPF_CNPJ;
    }
    

    public Cliente(Long codCPF_CNPJ, CEP cep, String nome, String email, Integer nroTelefoneCelular, String endereco, Integer nroTelefoneFixo, Integer nroTelefoneCelular2, Integer nroEndereco, String descricaoComplemento) {
        this.codCPF_CNPJ = codCPF_CNPJ;
        this.cep = cep;
        this.nome = nome;
        this.email = email;
        this.nroTelefoneCelular = nroTelefoneCelular;
        this.endereco = endereco;
        this.nroTelefoneFixo = nroTelefoneFixo;
        this.nroTelefoneCelular2 = nroTelefoneCelular2;
        this.nroEndereco = nroEndereco;
        this.descricaoComplemento = descricaoComplemento;
    }

    public Long getCodCPF_CNPJ() {
        return codCPF_CNPJ;
    }

    public void setCodCPF_CNPJ(Long codCPF_CNPJ) {
        this.codCPF_CNPJ = codCPF_CNPJ;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNroTelefoneCelular() {
        return nroTelefoneCelular;
    }

    public void setNroTelefoneCelular(Integer nroTelefoneCelular) {
        this.nroTelefoneCelular = nroTelefoneCelular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNroTelefoneFixo() {
        return nroTelefoneFixo;
    }

    public void setNroTelefoneFixo(Integer nroTelefoneFixo) {
        this.nroTelefoneFixo = nroTelefoneFixo;
    }

    public Integer getNroTelefoneCelular2() {
        return nroTelefoneCelular2;
    }

    public void setNroTelefoneCelular2(Integer nroTelefoneCelular2) {
        this.nroTelefoneCelular2 = nroTelefoneCelular2;
    }

    public Integer getNroEndereco() {
        return nroEndereco;
    }

    public void setNroEndereco(Integer nroEndereco) {
        this.nroEndereco = nroEndereco;
    }

    public String getDescricaoComplemento() {
        return descricaoComplemento;
    }

    public void setDescricaoComplemento(String descricaoComplemento) {
        this.descricaoComplemento = descricaoComplemento;
    }
}
