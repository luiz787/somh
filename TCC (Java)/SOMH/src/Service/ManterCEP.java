/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Domain.CEP;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author F43L
 */
public interface ManterCEP {
    public boolean cadastrarCEP(CEP cep) throws ExcecaoPersistencia, ExcecaoNegocio;
    public CEP deletarCEP(int id) throws ExcecaoPersistencia;
    public CEP getCEPById(int id) throws ExcecaoPersistencia;
    public List<CEP> getAll() throws ExcecaoPersistencia;
}
