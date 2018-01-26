/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;


import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author F43L
 */
public interface ManterUF {
    public boolean cadastrarUF(UF id) throws ExcecaoPersistencia, ExcecaoNegocio;
    public UF getUFById(String id) throws ExcecaoPersistencia;
    public List<UF> getAll() throws ExcecaoPersistencia;
    
}
