package DAO;

import Domain.Equipamento;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface EquipamentoDAO {
    public Long insert(Equipamento equipamento) throws ExcecaoPersistencia;
    public boolean update(Equipamento equipamento) throws ExcecaoPersistencia;
    public Equipamento delete(Long id) throws ExcecaoPersistencia;
    public Equipamento getEquipamentoById(Long id) throws ExcecaoPersistencia;
    public List<Equipamento> listAll() throws ExcecaoPersistencia;
}
