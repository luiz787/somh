package DAO;

import Domain.Status;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface StatusDAO {
    public Status getStatusById(Integer id) throws ExcecaoPersistencia;
    public List<Status> listAll() throws ExcecaoPersistencia;
}
