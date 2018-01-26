package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.OSAcessorioDAO;
import br.cefetmg.inf.somh.domain.OSAcessorio;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterOSAcessorio;

import java.util.List;

public class ManterOSAcessorioImpl implements ManterOSAcessorio{
    private final OSAcessorioDAO osAcessorioDAO;
    
    public ManterOSAcessorioImpl(OSAcessorioDAO osAcessorioDAO) {
        this.osAcessorioDAO = osAcessorioDAO;
    }
    
    @Override
    public boolean cadastrarOSAcessorio(OSAcessorio osAcessorio) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = osAcessorioDAO.insert(osAcessorio);
        return result;
    }

    @Override
    public OSAcessorio deletarOSAcessorio(Long idOS, Long idAcessorio) throws ExcecaoPersistencia {
        OSAcessorio result = osAcessorioDAO.delete(idOS, idAcessorio);
        return result;
    }
    
    @Override
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia {
        OSAcessorio result = osAcessorioDAO.getOSAcessorioById(idOS, idAcessorio);
        return result;
    }
    
    @Override
    public List<OSAcessorio> getAllByOS(Long id) throws ExcecaoPersistencia {
        List<OSAcessorio> result = osAcessorioDAO.listAllByOS(id);
        return result;
    }

    @Override
    public List<OSAcessorio> getAll() throws ExcecaoPersistencia {
        List<OSAcessorio> result = osAcessorioDAO.listAll();
        return result;
    }

    @Override
    public void deletarAllOSAcessorio(Long idOS) throws ExcecaoPersistencia {
        osAcessorioDAO.deleteAll(idOS);
    }
}
