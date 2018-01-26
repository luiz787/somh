/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import br.cefetmg.inf.somh.dao.ClienteDAO;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterCliente;

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
