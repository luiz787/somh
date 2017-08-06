package Service;

import Domain.OSStatus;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ManterOSStatus {
    public boolean cadastrarOSStatus(OSStatus osStatus) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSStatus> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSStatus> getAll() throws ExcecaoPersistencia;
}
