package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.Equipamento;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ManterEquipamento {
    public Long cadastrarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterarEquipamento(Equipamento equipamento) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Equipamento deletarEquipamento(Long id) throws ExcecaoPersistencia;
    public List<Equipamento> getAll() throws ExcecaoPersistencia;
}
