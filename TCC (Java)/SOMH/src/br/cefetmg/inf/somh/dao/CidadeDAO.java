/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao;



import br.cefetmg.inf.somh.domain.Cidade;
import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author andro
 */
public interface CidadeDAO {
    public Long insert(Cidade cidade) throws ExcecaoPersistencia;
    public boolean update(Cidade cidade) throws ExcecaoPersistencia;
    public Cidade delete(Long id) throws ExcecaoPersistencia;
    public Cidade getCidadeById(Long id) throws ExcecaoPersistencia;
    public List<Cidade> listAll() throws ExcecaoPersistencia;
    public List<Cidade> listAllByUF(UF uf) throws ExcecaoPersistencia;
    
}
