/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;


import br.cefetmg.inf.somh.domain.Cidade;
import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author F43L
 */
public interface ManterCidade {
    public Long cadastrarCidade(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Cidade deletarCidade(Long id) throws ExcecaoPersistencia;
    public Cidade getCidadeById(Long id) throws ExcecaoPersistencia;
    public List<Cidade> getAll() throws ExcecaoPersistencia;
    public List<Cidade> getAllbyUF(UF uf) throws ExcecaoPersistencia;
}
