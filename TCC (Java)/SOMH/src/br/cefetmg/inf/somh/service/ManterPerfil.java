/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.Perfil;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author aluno
 */
public interface ManterPerfil {
    public List<Perfil> pesquisarTodos() throws ExcecaoPersistencia;
    public Perfil pesquisarPorId(Long id) throws ExcecaoPersistencia;
}
