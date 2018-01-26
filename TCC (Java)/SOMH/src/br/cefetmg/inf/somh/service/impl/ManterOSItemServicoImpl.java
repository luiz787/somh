/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.OSItemServicoDAO;
import br.cefetmg.inf.somh.dao.impl.OSItemServicoDAOImpl;
import br.cefetmg.inf.somh.domain.OSItemServico;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterOSItemServico;

import java.util.List;

/**
 *
 * @author Luiz
 */
public class ManterOSItemServicoImpl implements ManterOSItemServico{
    private final OSItemServicoDAO osItemServicoDAO;
    
    public ManterOSItemServicoImpl(OSItemServicoDAOImpl instance) {
        osItemServicoDAO = instance;
    }

    @Override
    public boolean cadastrarOSItemServico(OSItemServico osItemServico) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (osItemServico==null){
            throw new ExcecaoNegocio("Entidade não pode ser nula.");
        }
        String errList = "";
        if (osItemServico.getOs()==null){
            errList+="OS não pode ser nula.\n";
        }
        if (osItemServico.getServico()==null){
            errList+="Serviço realizado não pode ser nulo.\n";
        }
        if (osItemServico.getValorServico()==null){
            errList+="Valor do serviço não pode ser nulo.\n";
        }
        if (!errList.equals("")){
            throw new ExcecaoNegocio(errList);
        }
        return osItemServicoDAO.cadastrarOSItemServico(osItemServico);
    }

    @Override
    public List<OSItemServico> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio {
        if (id==null){
            throw new ExcecaoNegocio("Id da OS não pode ser nulo.");
        }
        return osItemServicoDAO.getAllByOS(id);
    }

    @Override
    public List<OSItemServico> getAll() throws ExcecaoPersistencia {
        return osItemServicoDAO.getAll();
    }
    
}
