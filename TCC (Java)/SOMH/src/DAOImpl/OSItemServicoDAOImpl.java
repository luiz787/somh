/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import BD.JDBCManterConexao;
import DAO.OSDAO;
import DAO.OSItemServicoDAO;
import Domain.OSItemPeca;
import Domain.OSItemServico;
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
public class OSItemServicoDAOImpl implements OSItemServicoDAO {

    private static OSItemServicoDAOImpl osItemServicoDAOImpl = null;

    private OSItemServicoDAOImpl() {
    }

    public static OSItemServicoDAOImpl getInstance() {

        if (osItemServicoDAOImpl == null) {
            osItemServicoDAOImpl = new OSItemServicoDAOImpl();
        }

        return osItemServicoDAOImpl;
    }

    @Override
    public boolean cadastrarOSItemServico(OSItemServico osItemServico) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO ositemservico ("
                    + "nro_OS, "
                    + "cod_servico, "
                    + "qtd_servico, "
                    + "vlr_servico, "
                    + "idt_situacao"
                    + ") VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, osItemServico.getOs().getId());
            pstmt.setLong(2, osItemServico.getServico().getId());
            pstmt.setInt(3, osItemServico.getQuantidadeServico());
            pstmt.setDouble(4, osItemServico.getValorServico());
            pstmt.setBoolean(5, osItemServico.isSituacao());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            throw new ExcecaoPersistencia(e);
        }
    }

    @Override
    public List<OSItemServico> getAllByOS(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM ositemservico WHERE nro_OS = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<OSItemServico> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            ServicoDAOImpl servicoDAOImpl = ServicoDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSItemServico osItemServico = new OSItemServico(
                        osDAOImpl.getOSById(rs.getLong(1)),
                        servicoDAOImpl.getById(rs.getLong(2)),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                    );
                    listAll.add(osItemServico);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSItemServicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSItemServico> getAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM ositemservico";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<OSItemServico> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            ServicoDAOImpl servicoDAOImpl = ServicoDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSItemServico osItemServico = new OSItemServico(
                        osDAOImpl.getOSById(rs.getLong(1)),
                        servicoDAOImpl.getById(rs.getLong(2)),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                    );
                    listAll.add(osItemServico);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSItemServicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

}
