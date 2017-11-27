/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.OSItemServico;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author Luiz
 */
public interface OSItemServicoDAO {
    public boolean cadastrarOSItemServico(OSItemServico osItemPeca) throws ExcecaoPersistencia;
    public List<OSItemServico> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSItemServico> getAll() throws ExcecaoPersistencia;
}
