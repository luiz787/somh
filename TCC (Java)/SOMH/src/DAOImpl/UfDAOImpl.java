/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.UFDAO;
import Domain.UF;
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
 * @author F43L
 */
public class UfDAOImpl implements UFDAO{
     private static UfDAOImpl ufDAO = null;

    private UfDAOImpl() {
    }

    public static UfDAOImpl getInstance() {

        if (ufDAO == null) {
            ufDAO = new UfDAOImpl();
        }

        return ufDAO;
    }

    @Override
    public boolean insert(UF uf) throws ExcecaoPersistencia {
        try {
            if (uf == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO `uf` ("
                    + "`cod_UF`, "
                    + "`nom_UF`"
                    + ") VALUES (?, ?)"
                    ;

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, uf.getId());
            pstmt.setString(2,uf.getNome() );
            
            
            pstmt.executeUpdate();
            
            
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<UF> listAll() throws ExcecaoPersistencia {
         try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM uf ORDER BY cod_UF;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<UF> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    UF uf = new UF(
                        rs.getLong("cod_UF"),
                        rs.getString("nom_UF")
                        
                    );
                    listAll.add(uf);
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
    public UF consultarPorId(Long id) throws ExcecaoPersistencia {
         try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM uf WHERE cod_UF = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            UF uf = null;
            if (rs.next()) {
                uf = new UF(
                        rs.getLong("cod_UF"),
                        rs.getString("nom_UF")
                        
                    );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return uf;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
}
