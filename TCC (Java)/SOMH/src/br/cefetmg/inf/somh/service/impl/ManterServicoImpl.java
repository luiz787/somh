/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.ServicoDAO;
import br.cefetmg.inf.somh.domain.Servico;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterServico;

import java.util.List;

/**
 *
 * @author Luiz
 */
public class ManterServicoImpl implements ManterServico {
    
    private final ServicoDAO servicoDAO;
    
    public ManterServicoImpl(ServicoDAO instance) {
        servicoDAO = instance;
    }

    @Override
    public List<Servico> listAll() throws ExcecaoPersistencia {
        return servicoDAO.listAll();
    }

    @Override
    public Servico getById(Long id) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (id==null){
            throw new ExcecaoNegocio("ID do serviço não pode ser nulo.");
        }
        return servicoDAO.getById(id);
    }

    @Override
    public boolean insert(Servico servico) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (servico==null){
            throw new ExcecaoNegocio("Servico não podes ser nulo.");
        }
        String errList="";
        if (servico.getId()!=null){
            errList+="Servico não pode possuir ID.\n";
        }
        if (servico.getDescricao().isEmpty()){
            errList+="Servico deve possuir uma descricao.\n";
        }
        if (servico.getQuantidadeTempo()==null){
            errList+="Servico deve possuir quantidade de tempo.\n";
        }
        if (servico.getValor().isEmpty()){
            errList+="Servico deve possuir valor.";
        }
        if (!errList.equals("")){
            throw new ExcecaoNegocio(errList);
        }
        return servicoDAO.insert(servico);
    }
    
}
