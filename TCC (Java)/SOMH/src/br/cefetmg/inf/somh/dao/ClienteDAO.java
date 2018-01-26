package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ClienteDAO {
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia;
    public boolean insert(Cliente cliente) throws ExcecaoPersistencia;
    public boolean update(Cliente cliente) throws ExcecaoPersistencia;
    public Cliente delete(Long id) throws ExcecaoPersistencia;
    public List<Cliente> listAll() throws ExcecaoPersistencia;
}
