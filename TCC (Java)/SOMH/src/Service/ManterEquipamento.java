package Service;

import Domain.Equipamento;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ManterEquipamento {
    public Long cadastrarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Equipamento deletarEquipamento(Long id) throws ExcecaoPersistencia;
    public List<Equipamento> getAll() throws ExcecaoPersistencia;
}
