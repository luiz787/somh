package DAO;

import Domain.Cliente;
import Exception.ExcecaoPersistencia;

public interface ClienteDAO {
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia;
}
