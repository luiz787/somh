package DAO;

import Domain.Cliente;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ClienteDAO {
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia;
    public boolean insert(Cliente cliente) throws ExcecaoPersistencia;
    public boolean update(Cliente cliente) throws ExcecaoPersistencia;
    public Cliente delete(Long id) throws ExcecaoPersistencia;
    public List<Cliente> listAll() throws ExcecaoPersistencia;
}
