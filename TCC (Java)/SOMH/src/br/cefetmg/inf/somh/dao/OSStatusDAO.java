package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface OSStatusDAO {
    public boolean insert(OSStatus osStatus) throws ExcecaoPersistencia;
    public void deleteAll(Long idOS) throws ExcecaoPersistencia;
    public List<OSStatus> listAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSStatus> listAll() throws ExcecaoPersistencia;
}
