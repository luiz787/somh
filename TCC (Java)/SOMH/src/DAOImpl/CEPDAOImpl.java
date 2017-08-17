/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.CEPDAO;
import DAO.CidadeDAO;
import Domain.CEP;
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
public class CEPDAOImpl implements CEPDAO {

     private static CEPDAOImpl cepDAO = null;

    private CEPDAOImpl() {
    }

    public static CEPDAOImpl getInstance() {

        if (cepDAO == null) {
            cepDAO = new CEPDAOImpl();
        }

        return cepDAO;
    }
    
    @Override
    public boolean insert(CEP cep) throws ExcecaoPersistencia {
        try {
            if (cep == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO `cep` ("
                    + "`cod_UF`,"
                    + " `cod_cidade`,"
                    + " `nro_cep`"
                    + ") VALUES (?,?,?)";
                     
            
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cep.getCidade().getUf().getId());
            pstmt.setLong(2, cep.getCidade().getId());
            pstmt.setInt(3, cep.getNroCEP());
            
            
            pstmt.executeUpdate();
            
            
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public boolean update(CEP cep) throws ExcecaoPersistencia {
         try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE cep "
                    + "SET "
                    + "cod_UF=?,"
                    + "cod_cidade=?, "
                    + "nro_cep=?, "
                    + " WHERE nro_cep=?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cep.getCidade().getUf().getId());
            pstmt.setLong(2, cep.getCidade().getId());
            pstmt.setInt(3, cep.getNroCEP());
            pstmt.setLong(4, cep.getNroCEP());
            
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public CEP delete(int id) throws ExcecaoPersistencia {
        try {
            CEP cep = this.getCEPById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM cep WHERE nro_cep=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return cep;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public CEP getCEPById(int id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cep WHERE nro_cep=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            CEP cep = null;
            
            CidadeDAO cidadeDAOImpl = CidadeDAOImpl.getInstance();
            if (rs.next()) {
                cep = new CEP(
                     cidadeDAOImpl.getCidadeById(rs.getLong("cod_cidade")),
                      rs.getInt("nro_cep")
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cep;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<CEP> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cep ORDER BY nro_cep;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<CEP> listAll = new ArrayList<>();
            
            CidadeDAO cidadeDAOImpl = CidadeDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    CEP cep = new CEP(
                     cidadeDAOImpl.getCidadeById(rs.getLong("cod_cidade")),
                      rs.getInt("nro_cep")
                );
                    listAll.add(cep);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
}
