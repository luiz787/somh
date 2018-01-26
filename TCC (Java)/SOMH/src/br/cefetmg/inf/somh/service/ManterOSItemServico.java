/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.OSItemServico;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

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
