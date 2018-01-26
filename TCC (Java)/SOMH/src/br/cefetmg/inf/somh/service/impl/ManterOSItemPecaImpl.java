/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.OSItemPecaDAO;
import br.cefetmg.inf.somh.dao.impl.OSItemPecaDAOImpl;
import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterOSItemPeca;

import java.util.List;

/**
 *
 * @author Luiz
 */
public class ManterOSItemPecaImpl implements ManterOSItemPeca {    
    private final OSItemPecaDAO osItemPecaDAO;
    
    public ManterOSItemPecaImpl(OSItemPecaDAOImpl instance) {
        osItemPecaDAO = instance;
    }

    @Override
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(id==null){
            throw new ExcecaoNegocio("Id da os não pode ser nulo.");
        }
        return osItemPecaDAO.getAllByOS(id);
    }

    @Override
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia {
        return osItemPecaDAO.getAll();
    }

    @Override
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia, ExcecaoNegocio {
        return osItemPecaDAO.cadastrarOSItemPeca(osItemPeca);
    }
    
}
