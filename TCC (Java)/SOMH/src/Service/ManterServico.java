/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.Servico;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author Luiz
 */
public interface ManterServico {
    public List<Servico> listAll() throws ExcecaoPersistencia;
    public Servico getById(Long id) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean insert(Servico servico) throws ExcecaoPersistencia, ExcecaoNegocio;
}
