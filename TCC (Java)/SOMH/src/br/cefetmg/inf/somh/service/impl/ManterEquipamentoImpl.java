package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.EquipamentoDAO;
import br.cefetmg.inf.somh.domain.Equipamento;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterEquipamento;

import java.util.List;

public class ManterEquipamentoImpl implements ManterEquipamento{
    private final EquipamentoDAO equipamentoDAO;
    
    public ManterEquipamentoImpl(EquipamentoDAO equipamentoDAO) {
        this.equipamentoDAO = equipamentoDAO;
    }
    
    @Override
    public Long cadastrarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio {
        Long result = equipamentoDAO.insert(equipamento);
        return result;
    }

    @Override
    public boolean alterarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = equipamentoDAO.update(equipamento);
        return result;
    }

    @Override
    public Equipamento deletarEquipamento(Long id) throws ExcecaoPersistencia {
        Equipamento result = equipamentoDAO.delete(id);
        return result;
    }

    @Override
    public List<Equipamento> getAll() throws ExcecaoPersistencia {
        List<Equipamento> result = equipamentoDAO.listAll();
        return result;
    }
    
}
