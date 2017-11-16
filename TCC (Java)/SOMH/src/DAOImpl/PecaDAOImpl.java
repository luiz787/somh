/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.PecaDAO;
import Domain.Peca;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiz
 */
public class PecaDAOImpl implements PecaDAO {

    @Override
    public List<Peca> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM peca;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Peca> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Peca peca = new Peca(
                            rs.getLong("cod_peca"), 
                            rs.getString("des_peca"), 
                            rs.getDouble("prc_venda"),
                            rs.getString("des_marca"));
                    listAll.add(peca);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public boolean insert(Peca peca) throws ExcecaoPersistencia {
        try {
            if (peca == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO peca ("
                    + "des_peca, "
                    + "prc_venda, "
                    + "des_marca"
                    + ") VALUES (?, ?, ?)"
                    ;
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, peca.getDescricao());
            pstmt.setDouble(2, peca.getPrecoVenda());
            pstmt.setString(3, peca.getMarca());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM peca");
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                peca.setId(id);
            }
            rs.close();
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
}
