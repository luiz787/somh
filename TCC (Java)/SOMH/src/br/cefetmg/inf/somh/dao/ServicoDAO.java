/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao;

import br.cefetmg.inf.somh.domain.Servico;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author Luiz
 */
public interface ServicoDAO {
    public List<Servico> listAll() throws ExcecaoPersistencia;
    public Servico getById(Long id) throws ExcecaoPersistencia;
    public boolean insert(Servico servico) throws ExcecaoPersistencia;
}
