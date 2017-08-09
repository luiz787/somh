/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Domain.Cliente;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author F43L
 */
public interface ManterCliente {
    public boolean cadastrarCliente(Cliente cliente) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterarCliente(Cliente cliente) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Cliente deletarCliente(Long id) throws ExcecaoPersistencia;
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia;
    public List<Cliente> getAll() throws ExcecaoPersistencia;
}
