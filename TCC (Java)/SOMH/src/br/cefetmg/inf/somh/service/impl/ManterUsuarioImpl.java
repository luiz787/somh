/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.service.impl;

import java.util.List;

import br.cefetmg.inf.somh.dao.UsuarioDAO;
import br.cefetmg.inf.somh.dao.impl.UsuarioDAOImpl;
import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.service.ManterUsuario;

/**
 *
 * @author aluno
 */
public class ManterUsuarioImpl implements ManterUsuario {

    public ManterUsuarioImpl() {
        usuarioDAO = UsuarioDAOImpl.getInstance();
    }
    
    private UsuarioDAO usuarioDAO;

    @Override
    public Long cadastrar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio {
        String error = null;
        if (usuario == null) {
            error += " Usuário deve existir.";
        } else {
            if (usuario.getNome() == null) {
                error += " Nome de usuário não pode estar vazio.";
            }
            if (usuario.getPerfil() == null) {
                error += " Usuário deve ter um perfil.";
            }
            if (usuario.getSenha() == null) {
                error += " Usuário deve ter uma senha.";
            }
            if (error != null) {
                throw new ExcecaoNegocio(error);
            }
        }
        return usuarioDAO.inserir(usuario);
    }

    @Override
    public boolean alterar(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio {
        String error=null;
        if (usuario == null) {
            error += " Usuário deve existir.";
        } else {
            if (usuario.getNome() == null) {
                error += " Nome de usuário nao pode estar vazio.";
            }
            if (usuario.getPerfil() == null) {
                error += " Usuário deve ter um perfil.";
            }
            if (usuario.getSenha() == null) {
                error += " Usuário deve ter uma senha.";
            }
            if (error != null) {
                throw new ExcecaoNegocio(error);
            }
        }
        return usuarioDAO.atualizar(usuario);
    }

    @Override
    public boolean excluir(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio {
        String error = null;
        if (usuario==null){
            error+=" Usuário deve existir.";
        }
        if (error!=null){
            throw new ExcecaoNegocio(error);
        }
        return usuarioDAO.delete(usuario);
    }
    
    @Override
    public boolean verificarExistencia(String nome) throws ExcecaoPersistencia {
        return usuarioDAO.consultarExistencia(nome);
    }

    @Override
    public List<Usuario> pesquisarTodos() throws ExcecaoPersistencia {
        return usuarioDAO.listarTodos();
    }

    @Override
    public Usuario pesquisarPorId(Long id) throws ExcecaoPersistencia {
        return usuarioDAO.consultarPorId(id);
    }

    @Override
    public Usuario getUsuarioByNomeSenha(String nome, String senha) throws ExcecaoPersistencia {
        return usuarioDAO.consultarPorNomeSenha(nome, senha);
    }
    
}
