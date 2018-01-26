/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.cefetmg.inf.somh.bd.JDBCManterConexao;
import br.cefetmg.inf.somh.dao.ServicoDAO;
import br.cefetmg.inf.somh.domain.Servico;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

/**
 *
 * @author Luiz
 */
public class ServicoDAOImpl implements ServicoDAO {
    
    private static ServicoDAOImpl servicoDAOImpl = null;

    private ServicoDAOImpl() {
    }

    public static ServicoDAOImpl getInstance() {

        if (servicoDAOImpl == null) {
            servicoDAOImpl = new ServicoDAOImpl();
        }

        return servicoDAOImpl;
    }

    @Override
    public List<Servico> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM servico;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Servico> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Servico servico = new Servico(
                            rs.getLong("cod_servico"), 
                            rs.getString("des_servico"), 
                            rs.getLong("qtd_tempo_servico"),
                            rs.getString("vlr_servico"));
                    listAll.add(servico);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public Servico getById(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM servico WHERE cod_servico = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            Servico servico = null;
            if (rs.next()) {
                servico = new Servico(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getString(4)
                );
            }
            rs.close();
            pstmt.close();
            connection.close();
            return servico;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e.getMessage());
        }
    }

    @Override
    public boolean insert(Servico servico) throws ExcecaoPersistencia {
        try {
            if (servico == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO servico ("
                    + "des_servico, "
                    + "qtd_tempo_servico, "
                    + "vlr_servico"
                    + ") VALUES (?, ?, ?)"
                    ;
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, servico.getDescricao());
            pstmt.setLong(2, servico.getQuantidadeTempo());
            pstmt.setString(3, servico.getValor());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM servico");
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                servico.setId(id);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
}
