/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao;


import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

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
