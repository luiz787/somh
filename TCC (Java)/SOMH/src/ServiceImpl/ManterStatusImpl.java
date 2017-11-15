package ServiceImpl;

import DAO.StatusDAO;
import Domain.Status;
import Exception.ExcecaoPersistencia;
import Service.ManterStatus;
import java.util.List;

public class ManterStatusImpl implements ManterStatus{
    private final StatusDAO statusDAO;
    
    public ManterStatusImpl(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }
    
    @Override
    public Status getStatusById(Integer id) throws ExcecaoPersistencia {
        Status result = statusDAO.getStatusById(id);
        return result;
    }

    @Override
    public List<Status> getAll() throws ExcecaoPersistencia {
        List<Status> result = statusDAO.listAll();
        return result;
    }
    
}
