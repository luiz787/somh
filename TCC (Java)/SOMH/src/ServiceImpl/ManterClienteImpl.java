/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.ClienteDAO;

import Domain.Cliente;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterCliente;
import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterClienteImpl implements ManterCliente {
        private final ClienteDAO clienteDAO;

        public ManterClienteImpl(ClienteDAO clienteDAO) {
            this.clienteDAO = clienteDAO;
        }
            
        
        
            @Override
            public boolean cadastrarCliente(Cliente cliente) throws ExcecaoPersistencia, ExcecaoNegocio {
                  return clienteDAO.insert(cliente);
            }

            @Override
            public boolean alterarCliente(Cliente cliente) throws ExcecaoPersistencia, ExcecaoNegocio {
                return clienteDAO.update(cliente);
            }

            @Override
            public Cliente deletarCliente(Long id) throws ExcecaoPersistencia {
                return clienteDAO.delete(id);
            }

            @Override
            public Cliente getClienteById(Long id) throws ExcecaoPersistencia {
                return clienteDAO.getClienteById(id);
            }

            @Override
            public List<Cliente> getAll() throws ExcecaoPersistencia {
                return clienteDAO.listAll();
            }
    
}
