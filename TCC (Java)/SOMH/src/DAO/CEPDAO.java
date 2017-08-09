/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.CEP;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author fael
 */
public interface CEPDAO {
    public boolean insert(CEP cep) throws ExcecaoPersistencia;
    public boolean update(CEP cep) throws ExcecaoPersistencia;
    public CEP delete(int id) throws ExcecaoPersistencia;
    public CEP getCEPById(int id) throws ExcecaoPersistencia;
    public List<CEP> listAll() throws ExcecaoPersistencia;

}
