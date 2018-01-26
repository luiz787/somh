package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.OSAcessorio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface OSAcessorioDAO {
    public boolean insert(OSAcessorio osAcessorio) throws ExcecaoPersistencia;
    public OSAcessorio delete(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public void deleteAll(Long idOS) throws ExcecaoPersistencia;
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public List<OSAcessorio> listAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSAcessorio> listAll() throws ExcecaoPersistencia;
}
