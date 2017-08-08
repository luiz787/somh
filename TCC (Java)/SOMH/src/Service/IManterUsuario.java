/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Domain.Usuario;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

/**
 *
 * @author aluno
 */
public interface IManterUsuario {
    public Long cadastrar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean excluir(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public List<Usuario> pesquisarTodos() throws ExcecaoPersistencia;
    public Usuario pesquisarPorId(Long id) throws ExcecaoPersistencia;
}
