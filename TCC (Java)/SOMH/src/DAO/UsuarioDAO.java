package DAO;

import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface UsuarioDAO {
    public Long inserir(Usuario usuario) throws ExcecaoPersistencia;

    public boolean atualizar(Usuario usuario) throws ExcecaoPersistencia;

    public boolean delete(Usuario usuario) throws ExcecaoPersistencia;

    public List<Usuario> listarTodos() throws ExcecaoPersistencia;

    public Usuario consultarPorId(Long id) throws ExcecaoPersistencia;
}
