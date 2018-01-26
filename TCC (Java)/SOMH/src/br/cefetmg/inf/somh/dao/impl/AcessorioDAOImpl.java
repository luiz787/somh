package br.cefetmg.inf.somh.dao.impl;

import br.cefetmg.inf.somh.bd.JDBCManterConexao;
import br.cefetmg.inf.somh.dao.AcessorioDAO;
import br.cefetmg.inf.somh.domain.Acessorio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcessorioDAOImpl implements AcessorioDAO{
    private static AcessorioDAOImpl acessorioDAO = null;

    private AcessorioDAOImpl() {
    }

    public static AcessorioDAOImpl getInstance() {

        if (acessorioDAO == null) {
            acessorioDAO = new AcessorioDAOImpl();
        }

        return acessorioDAO;
    }
    
    @Override
    synchronized public Long insert(Acessorio acessorio) throws ExcecaoPersistencia {
        try {
            if (acessorio == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO acessorio ("
                    + "nom_acessorio"
                    + ") VALUES(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, acessorio.getNomeAcessorio());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM acessorio");
            
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                acessorio.setId(id);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public Acessorio delete(Long id) throws ExcecaoPersistencia {
        try {
            Acessorio acessorio = this.getAcessorioById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM acessorio WHERE cod_acessorio = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return acessorio;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Acessorio> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM acessorio ORDER BY cod_acessorio;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Acessorio> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Acessorio acessorio = new Acessorio(
                        rs.getLong("cod_acessorio"),
                        rs.getString("nom_acessorio")
                    );
                    listAll.add(acessorio);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM acessorio WHERE cod_acessorio = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Acessorio acessorio = null;
            if (rs.next()) {
                acessorio = new Acessorio(
                        rs.getLong("cod_acessorio"),
                        rs.getString("nom_acessorio")
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return acessorio;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
}
