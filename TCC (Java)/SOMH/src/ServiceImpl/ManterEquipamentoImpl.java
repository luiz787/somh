package ServiceImpl;

import DAO.EquipamentoDAO;
import Domain.Equipamento;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterEquipamento;
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
