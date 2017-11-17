package ServiceImpl;

import DAO.OSStatusDAO;
import Domain.OSStatus;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterOSStatus;
import java.util.List;

public class ManterOSStatusImpl implements ManterOSStatus{
    private final OSStatusDAO osStatusDAO;
    
    public ManterOSStatusImpl(OSStatusDAO osStatusDAO) {
        this.osStatusDAO = osStatusDAO;
    }
    
    @Override
    public boolean cadastrarOSStatus(OSStatus osStatus) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = osStatusDAO.insert(osStatus);
        return result;
    }

    @Override
    public List<OSStatus> getAllByOS(Long id) throws ExcecaoPersistencia {
        List<OSStatus> result = osStatusDAO.listAllByOS(id);
        return result;
    }

    @Override
    public List<OSStatus> getAll() throws ExcecaoPersistencia {
        List<OSStatus> result = osStatusDAO.listAll();
        return result;
    }

    @Override
    public void deletarAllOSStatus(Long id) throws ExcecaoPersistencia {
        osStatusDAO.deleteAll(id);
    }
    
}
