package DAOImpl;

import BD.JDBCManterConexao;
import DAO.EquipamentoDAO;
import Domain.Equipamento;
import Domain.Equipamento;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipamentoDAOImpl implements EquipamentoDAO{
    private static EquipamentoDAOImpl equipamentoDAO = null;

    private EquipamentoDAOImpl() {
    }

    public static EquipamentoDAOImpl getInstance() {

        if (equipamentoDAO == null) {
            equipamentoDAO = new EquipamentoDAOImpl();
        }

        return equipamentoDAO;
    }
    
    @Override
    synchronized public Long insert(Equipamento equipamento) throws ExcecaoPersistencia {
        try {
            if (equipamento == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO equipamento ("
                    + "des_equipto, "
                    + "des_marca, "
                    + "des_modelo, "
                    + "nro_serie, "
                    + "des_componentes "
                    + ") VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, equipamento.getDesEquipto());
            pstmt.setString(2, equipamento.getDesMarca());
            if(equipamento.getDesModelo()!=null) {
                pstmt.setString(3, equipamento.getDesModelo());
            } else {
                pstmt.setNull(3, java.sql.Types.NULL);
            }
            if(equipamento.getNroSerie()!=null) {
                pstmt.setInt(4, equipamento.getNroSerie());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if(equipamento.getDesComponentes()!=null) {
                pstmt.setString(5, equipamento.getDesComponentes());
            } else {
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM equipamento");
            
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                equipamento.setId(id);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public boolean update(Equipamento equipamento) throws ExcecaoPersistencia {
        try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE equipamento "
                    + "SET "
                    + "des_equipto=?, "
                    + "des_marca=?, "
                    + "des_modelo=?, "
                    + "nro_serie=?, "
                    + "des_componentes=? "
                    + " WHERE seq_equipto=?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, equipamento.getDesEquipto());
            pstmt.setString(2, equipamento.getDesMarca());
            if(equipamento.getDesModelo()!=null) {
                pstmt.setString(3, equipamento.getDesModelo());
            } else {
                pstmt.setNull(3, java.sql.Types.NULL);
            }
            if(equipamento.getNroSerie()!=null) {
                pstmt.setInt(4, equipamento.getNroSerie());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if(equipamento.getDesComponentes()!=null) {
                pstmt.setString(5, equipamento.getDesComponentes());
            } else {
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            pstmt.setLong(6, equipamento.getId());
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
    synchronized public Equipamento delete(Long id) throws ExcecaoPersistencia {
        try {
            Equipamento equipamento = this.getEquipamentoById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM equipamento WHERE seq_equipto=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return equipamento;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Equipamento> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM equipamento ORDER BY cod_equipamento;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Equipamento> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Equipamento equipamento = new Equipamento(
                        rs.getLong("seq_equipto"),
                        rs.getString("des_marca"),
                        rs.getString("des_equipto"),
                        rs.getString("des_modelo"),
                        rs.getString("des_componentes"),
                        rs.getInt("nro_serie")
                    );
                    listAll.add(equipamento);
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
    public Equipamento getEquipamentoById(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM equipamento WHERE cod_equipamento = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Equipamento equipamento = null;
            if (rs.next()) {
                equipamento = new Equipamento(
                    rs.getLong("seq_equipto"),
                    rs.getString("des_marca"),
                    rs.getString("des_equipto"),
                    rs.getString("des_modelo"),
                    rs.getString("des_componentes"),
                    rs.getInt("nro_serie")
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return equipamento;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EquipamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
}
