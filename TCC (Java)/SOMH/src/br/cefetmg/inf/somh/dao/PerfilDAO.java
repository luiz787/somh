package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.Perfil;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface PerfilDAO {
    public List<Perfil> listarTodos() throws ExcecaoPersistencia;

    public Perfil consultarPorId(Long id) throws ExcecaoPersistencia;
}
