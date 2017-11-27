/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.OSItemServico;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author Luiz
 */
public interface ManterOSItemServico {
    public boolean cadastrarOSItemServico(OSItemServico osItemPeca) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemServico> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemServico> getAll() throws ExcecaoPersistencia;
}
