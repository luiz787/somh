package Service;

import Domain.Status;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ManterStatus {
    public Status getStatusById(Integer id) throws ExcecaoPersistencia;
    public List<Status> getAll() throws ExcecaoPersistencia;
}
