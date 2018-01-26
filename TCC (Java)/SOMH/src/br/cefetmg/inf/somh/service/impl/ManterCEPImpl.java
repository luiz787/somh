/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.CEPDAO;
import br.cefetmg.inf.somh.domain.CEP;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterCEP;

import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterCEPImpl implements ManterCEP {
private final CEPDAO cepdao;

    public ManterCEPImpl(CEPDAO cepdao) {
        this.cepdao = cepdao;
    }

    
    
    
    @Override
    public boolean cadastrarCEP(CEP cep) throws ExcecaoPersistencia, ExcecaoNegocio {
        return cepdao.insert(cep);
    }

    @Override
    public CEP deletarCEP(int id) throws ExcecaoPersistencia {
        return cepdao.delete(id);
    }

    @Override
    public CEP getCEPById(int id) throws ExcecaoPersistencia {
        return cepdao.getCEPById(id);
    }

    @Override
    public List<CEP> getAll() throws ExcecaoPersistencia {
        return cepdao.listAll();
    }
    
}
