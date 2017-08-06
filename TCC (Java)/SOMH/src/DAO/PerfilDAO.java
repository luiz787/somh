package DAO;

import Domain.Perfil;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface PerfilDAO {
    public List<Perfil> listarTodos() throws ExcecaoPersistencia;

    public Perfil consultarPorId(Long id) throws ExcecaoPersistencia;
}
