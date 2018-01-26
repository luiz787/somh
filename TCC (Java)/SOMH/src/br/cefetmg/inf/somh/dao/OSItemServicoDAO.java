/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.OSItemServico;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

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
