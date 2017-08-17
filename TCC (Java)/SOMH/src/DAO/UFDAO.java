/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Domain.UF;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author andro
 */
public interface UFDAO {
    public boolean insert(UF uf) throws ExcecaoPersistencia;
    public List<UF> listAll() throws ExcecaoPersistencia;
    public UF consultarPorId(String id) throws ExcecaoPersistencia;
}
