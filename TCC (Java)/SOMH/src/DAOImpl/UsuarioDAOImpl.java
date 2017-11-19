/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.PerfilDAO;
import DAO.UsuarioDAO;
import Domain.Perfil;
import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class UsuarioDAOImpl implements UsuarioDAO, Serializable {

    private static UsuarioDAOImpl usuarioDAO = null;

    private UsuarioDAOImpl() {
    }

    public static UsuarioDAOImpl getInstance() {

        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAOImpl();
        }

        return usuarioDAO;
    }

    @Override
    public Long inserir(Usuario usuario) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO usuario (nom_usuario, cod_perfil, txt_senha) VALUES(?, ?, md5(?))";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setObject(2, usuario.getPerfil().getId());
            pstmt.setString(3, usuario.getSenha());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM usuario");

            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                usuario.setId(id);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return id;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public boolean atualizar(Usuario usuario) throws ExcecaoPersistencia {
        try {

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE usuario "
                    + "   SET nome = ?, "
                    + "       senha = md5(?), "
                    + "       perfil = ?, "
                    + " WHERE id = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setObject(3, usuario.getPerfil());
            pstmt.setLong(4, usuario.getId());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public boolean delete(Usuario usuario) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM usuario WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, usuario.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public boolean consultarExistencia(String nome) throws ExcecaoPersistencia {
        boolean existe;

        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT nom_usuario FROM usuario WHERE nom_usuario = " + "\'" + nome + "\'";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                existe = false;
            } else {
                existe = true;
            }

            pstmt.close();
            connection.close();
            return existe;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarTodos() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM usuario ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Usuario> listAll = null;
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setPerfil((Perfil) rs.getObject("perfil"));
                    usuario.setSenha(rs.getString("senha"));
                    listAll.add(usuario);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public Usuario consultarPorId(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM usuario WHERE cod_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            PerfilDAO perfilDAOImpl = PerfilDAOImpl.getInstance();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("cod_usuario"));
                usuario.setNome(rs.getString("nom_usuario"));
                usuario.setSenha(rs.getString("txt_senha"));
                Perfil perfil = perfilDAOImpl.consultarPorId(rs.getLong("cod_perfil"));
                usuario.setPerfil(perfil);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public Usuario consultarPorNomeSenha(String nome, String senha) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM usuario WHERE nom_usuario = ? AND txt_senha = md5(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            PerfilDAO perfilDAOImpl = PerfilDAOImpl.getInstance();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("cod_usuario"));
                Perfil perfil = perfilDAOImpl.consultarPorId(rs.getLong("cod_perfil"));
                usuario.setPerfil(perfil);
                usuario.setNome(rs.getString("nom_usuario"));
                usuario.setSenha(rs.getString("txt_senha"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
}
