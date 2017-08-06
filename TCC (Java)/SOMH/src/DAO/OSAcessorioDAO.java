package DAO;

import Domain.OSAcessorio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface OSAcessorioDAO {
    public boolean insert(OSAcessorio osAcessorio) throws ExcecaoPersistencia;
    public OSAcessorio delete(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public List<OSAcessorio> listAllByOS(Long id) throws ExcecaoPersistencia;
}
