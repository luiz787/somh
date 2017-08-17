/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.CidadeDAO;
import DAO.UFDAO;
import Domain.Cidade;
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
public class CidadeDAOImpl implements CidadeDAO{
    
    private static CidadeDAOImpl cidadeDAO = null;

    private CidadeDAOImpl() {
    }

    public static CidadeDAOImpl getInstance() {

        if (cidadeDAO == null) {
            cidadeDAO = new CidadeDAOImpl();
        }

        return cidadeDAO;
    }

    @Override
    public Long insert(Cidade cidade) throws ExcecaoPersistencia {
        try {
            if (cidade == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO `cidade` ("
                    + "`cod_UF`,"
                    + " `nom_cidade`"
                    + ") VALUES (?,  ?)" ;
            
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, cidade.getUf().getId());
            pstmt.setString(2, cidade.getNome());
            
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM cidade");
            
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                cidade.setId(id);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public boolean update(Cidade cidade) throws ExcecaoPersistencia {
        try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE cidade "
                    + "SET "
                    + "cod_UF=?,"
                    
                    + "nom_cidade=?, "
                    + " WHERE cod_cidade=?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cidade.getUf().getId());
            
            pstmt.setString(2, cidade.getNome());
            pstmt.setLong(3, cidade.getId());
            
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
    public Cidade delete(Long id) throws ExcecaoPersistencia {
        try {
            Cidade cidade = this.getCidadeById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM cidade WHERE cod_cidade=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return cidade;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public Cidade getCidadeById(Long id) throws ExcecaoPersistencia {
         try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cidade WHERE cod_cidade=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Cidade cidade = null;
            
            UFDAO ufDAOImpl = UfDAOImpl.getInstance();
            if (rs.next()) {
                cidade = new Cidade(
                    ufDAOImpl.consultarPorId(rs.getString("cod_UF")),
                    rs.getLong("cod_cidade"),
                    rs.getString("nom_cidade")
                    
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cidade;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Cidade> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cidade ORDER BY cod_cidade;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Cidade> listAll = new ArrayList<>();
            
            UFDAO ufDAOImpl = UfDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    Cidade cidade = new Cidade(
                    ufDAOImpl.consultarPorId(rs.getString("cod_UF")),
                    rs.getLong("cod_cidade"),
                    rs.getString("nom_cidade")
                    
                );
                    listAll.add(cidade);
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

    @Override
    public List<Cidade> listAllByUF(UF uf) throws ExcecaoPersistencia {
         try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cidade WHERE cod_UF=? ORDER BY cod_cidade;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, uf.getId());
            ResultSet rs = pstmt.executeQuery();
            
            

            List<Cidade> listAll = new ArrayList<>();
            
            UFDAO ufDAOImpl = UfDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    Cidade cidade = new Cidade(
                    ufDAOImpl.consultarPorId(rs.getString("cod_UF")),
                    rs.getLong("cod_cidade"),
                    rs.getString("nom_cidade")
                    
                );
                    listAll.add(cidade);
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
