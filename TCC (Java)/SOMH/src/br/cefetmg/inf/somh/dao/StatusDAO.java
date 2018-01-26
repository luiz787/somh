package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.Status;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface StatusDAO {
    public Status getStatusById(Integer id) throws ExcecaoPersistencia;
    public List<Status> listAll() throws ExcecaoPersistencia;
}
