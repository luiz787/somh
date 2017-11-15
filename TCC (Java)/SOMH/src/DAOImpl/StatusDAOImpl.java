package DAOImpl;

import BD.JDBCManterConexao;
import DAO.StatusDAO;
import Domain.Acessorio;
import Domain.Status;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusDAOImpl implements StatusDAO{
    private static StatusDAOImpl statusDAO = null;

    private StatusDAOImpl() {
    }

    public static StatusDAOImpl getInstance() {

        if (statusDAO == null) {
            statusDAO = new StatusDAOImpl();
        }

        return statusDAO;
    }

    @Override
    public Status getStatusById(Integer id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM status WHERE cod_status = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Status status = null;
            if (rs.next()) {
                status = new Status(
                        rs.getInt("cod_status"),
                        rs.getString("des_status")
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return status;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Status> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM status ORDER BY cod_status;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Status> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Status status = new Status(
                        rs.getInt("cod_status"),
                        rs.getString("des_status")
                    );
                    listAll.add(status);
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
    
}
