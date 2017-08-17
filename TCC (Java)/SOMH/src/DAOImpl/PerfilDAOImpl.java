/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.PerfilDAO;
import Domain.Perfil;
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
public class PerfilDAOImpl implements PerfilDAO {
    private static PerfilDAOImpl perfilDAO = null;

    private PerfilDAOImpl() {
    }

    public static PerfilDAOImpl getInstance() {

        if (perfilDAO == null) {
            perfilDAO = new PerfilDAOImpl();
        }

        return perfilDAO;
    }
    
    @Override
    public List<Perfil> listarTodos() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM categoria ORDER BY descricao";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Perfil> listAll = null;
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Perfil perfil = new Perfil();
                    perfil.setId(rs.getLong("id"));
                    perfil.setDescricao(rs.getString("descricao"));
                    listAll.add(perfil);
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
    public Perfil consultarPorId(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM perfil WHERE cod_perfil = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            Perfil perfil = null;
            if (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getLong("cod_perfil"));
                perfil.setDescricao(rs.getString("des_perfil"));
            }
            rs.close();
            pstmt.close();
            connection.close();
            
            return perfil;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }
    
}
