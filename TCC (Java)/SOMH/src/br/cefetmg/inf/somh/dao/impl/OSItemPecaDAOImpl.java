/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao.impl;

import br.cefetmg.inf.somh.bd.JDBCManterConexao;
import br.cefetmg.inf.somh.dao.OSDAO;
import br.cefetmg.inf.somh.dao.OSItemPecaDAO;
import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

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
public class OSItemPecaDAOImpl implements OSItemPecaDAO {

    private static OSItemPecaDAOImpl osItemPecaDAOImpl = null;

    private OSItemPecaDAOImpl() {
    }

    public static OSItemPecaDAOImpl getInstance() {

        if (osItemPecaDAOImpl == null) {
            osItemPecaDAOImpl = new OSItemPecaDAOImpl();
        }

        return osItemPecaDAOImpl;
    }

    @Override
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO ositempeca ("
                    + "nro_OS, "
                    + "cod_peca, "
                    + "qtd_peca, "
                    + "vlr_venda, "
                    + "idt_situacao"
                    + ") VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, osItemPeca.getOs().getId());
            pstmt.setLong(2, osItemPeca.getId());
            pstmt.setInt(3, osItemPeca.getQtd());
            pstmt.setDouble(4, osItemPeca.getValorVenda());
            pstmt.setString(5, osItemPeca.getSituacao());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e);
        }
    }

    @Override
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM ositempeca WHERE nro_OS = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            List<OSItemPeca> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSItemPeca osItemPeca = new OSItemPeca(
                        osDAOImpl.getOSById(rs.getLong(1)),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5)
                    );
                    listAll.add(osItemPeca);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSItemPecaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM ositempeca";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<OSItemPeca> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSItemPeca osItemPeca = new OSItemPeca(
                        osDAOImpl.getOSById(rs.getLong(1)),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5)
                    );
                    listAll.add(osItemPeca);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSItemPecaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

}
