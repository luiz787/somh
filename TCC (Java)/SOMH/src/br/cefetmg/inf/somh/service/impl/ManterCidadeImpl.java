/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.CidadeDAO;
import br.cefetmg.inf.somh.domain.Cidade;
import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterCidade;

import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterCidadeImpl implements ManterCidade {
    private final CidadeDAO cidadeDAO;

    public ManterCidadeImpl(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }
    
    

        @Override
        public Long cadastrarCidade(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio {
            return cidadeDAO.insert(cidade);
        }

        @Override
        public Cidade deletarCidade(Long id) throws ExcecaoPersistencia {
            return cidadeDAO.delete(id);
        }

        @Override
        public Cidade getCidadeById(Long id) throws ExcecaoPersistencia {
            return cidadeDAO.getCidadeById(id);
        }

        @Override
        public List<Cidade> getAll() throws ExcecaoPersistencia {
           return cidadeDAO.listAll();
        }

        @Override
        public List<Cidade> getAllbyUF(UF uf) throws ExcecaoPersistencia {
            return cidadeDAO.listAllByUF(uf);
        }
    
}
