package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.Acessorio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface AcessorioDAO {
    public Long insert(Acessorio acessorio) throws ExcecaoPersistencia;
    public Acessorio delete(Long id) throws ExcecaoPersistencia;
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia;
    public List<Acessorio> listAll() throws ExcecaoPersistencia;
}
