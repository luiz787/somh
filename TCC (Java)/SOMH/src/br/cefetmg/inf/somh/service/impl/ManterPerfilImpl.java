/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import java.util.List;

import br.cefetmg.inf.somh.dao.PerfilDAO;
import br.cefetmg.inf.somh.dao.impl.PerfilDAOImpl;
import br.cefetmg.inf.somh.domain.Perfil;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterPerfil;

/**
 *
 * @author aluno
 */
public class ManterPerfilImpl implements ManterPerfil {

    public ManterPerfilImpl() {
        perfilDAO = PerfilDAOImpl.getInstance();
    }
    PerfilDAO perfilDAO;
    @Override
    public List<Perfil> pesquisarTodos() throws ExcecaoPersistencia {
        return perfilDAO.listarTodos();
    }

    @Override
    public Perfil pesquisarPorId(Long id) throws ExcecaoPersistencia {
        return perfilDAO.consultarPorId(id);
    }
    
}
