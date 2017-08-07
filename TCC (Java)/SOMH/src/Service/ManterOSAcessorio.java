package Service;

import Domain.OSAcessorio;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ManterOSAcessorio {
    public boolean cadastrarOSAcessorio(OSAcessorio osAcessorio) throws ExcecaoPersistencia, ExcecaoNegocio;
    public OSAcessorio deletarOSAcessorio(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public List<OSAcessorio> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSAcessorio> getAll() throws ExcecaoPersistencia;
}
