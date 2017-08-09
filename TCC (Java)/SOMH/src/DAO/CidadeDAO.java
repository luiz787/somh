/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import Domain.Cidade;
import Domain.UF;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author andro
 */
public interface CidadeDAO {
    public boolean insert(Cidade cidade) throws ExcecaoPersistencia;
    public boolean update(Cidade cidade) throws ExcecaoPersistencia;
    public Cidade delete(Long id) throws ExcecaoPersistencia;
    public Cidade getCidadeById(Long id) throws ExcecaoPersistencia;
    public List<Cidade> listAll() throws ExcecaoPersistencia;
    public List<Cidade> listAllByUF(UF uf) throws ExcecaoPersistencia;
    
}
