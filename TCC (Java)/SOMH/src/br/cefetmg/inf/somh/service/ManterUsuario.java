/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

/**
 *
 * @author aluno
 */
public interface ManterUsuario {
    public Long cadastrar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean excluir(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean verificarExistencia(String nome) throws ExcecaoPersistencia;
    public List<Usuario> pesquisarTodos() throws ExcecaoPersistencia;
    public Usuario pesquisarPorId(Long id) throws ExcecaoPersistencia;
    public Usuario getUsuarioByNomeSenha(String nome, String senha) throws ExcecaoPersistencia;
}
