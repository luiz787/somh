/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.UsuarioDAO;
import Domain.Perfil;
import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class UsuarioDAOImpl implements UsuarioDAO {
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

            String sql = "INSERT INTO usuario (nome, perfil, senha) VALUES(?, ?, md5(?)) RETURNING id";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setObject(2, usuario.getPerfil());
            pstmt.setString(3, usuario.getSenha());
            ResultSet rs = pstmt.executeQuery();

            Long id = null;
            if (rs.next()) {
                id = new Long(rs.getLong("id"));
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
                    usuario.setPerfil((Perfil)rs.getObject("perfil"));
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

            String sql = "SELECT * FROM usuario WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil((Perfil)rs.getObject("perfil"));
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
    
}
