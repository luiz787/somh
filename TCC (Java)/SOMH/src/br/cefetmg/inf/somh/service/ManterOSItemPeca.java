/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;

import java.util.List;

import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

/**
 *
 * @author Luiz
 */
public interface ManterOSItemPeca {
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia;
}
