/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author Luiz
 */
public interface OSItemPecaDAO {
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia;
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia;
}