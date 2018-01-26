package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.AcessorioDAO;
import br.cefetmg.inf.somh.domain.Acessorio;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterAcessorio;

import java.util.List;

public class ManterAcessorioImpl implements ManterAcessorio{
    private final AcessorioDAO acessorioDAO;
    
    public ManterAcessorioImpl(AcessorioDAO acessorioDAO) {
        this.acessorioDAO = acessorioDAO;
    }
    
    @Override
    public Long cadastrarAcessorio(Acessorio acessorio) throws ExcecaoPersistencia, ExcecaoNegocio {
        Long result = acessorioDAO.insert(acessorio);
        return result;
    }

    @Override
    public Acessorio deletarAcessorio(Long cod_Acessorio) throws ExcecaoPersistencia {
        Acessorio result = acessorioDAO.delete(cod_Acessorio);
        return result;
    }

    @Override
    public List<Acessorio> getAll() throws ExcecaoPersistencia {
        List<Acessorio> result = acessorioDAO.listAll();
        return result;
    }

    @Override
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia {
        Acessorio result = acessorioDAO.getAcessorioById(id);
        return result;
    }
    
}
