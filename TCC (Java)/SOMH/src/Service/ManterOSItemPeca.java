/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.OSItemPeca;
import Domain.OSStatus;
import Exception.ExcecaoPersistencia;
import Exception.ExcecaoNegocio;
import java.util.List;

/**
 *
 * @author Luiz
 */
public interface ManterOSItemPeca {
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia;
}
