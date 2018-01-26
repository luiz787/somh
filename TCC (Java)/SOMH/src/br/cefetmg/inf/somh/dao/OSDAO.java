package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.OS;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface OSDAO {
    public Long insert(OS os) throws ExcecaoPersistencia;
    public boolean update(OS os) throws ExcecaoPersistencia;
    public OS delete(Long id) throws ExcecaoPersistencia;
    public OS getOSById(Long id) throws ExcecaoPersistencia;
    public List<OS> listAll() throws ExcecaoPersistencia;
}
