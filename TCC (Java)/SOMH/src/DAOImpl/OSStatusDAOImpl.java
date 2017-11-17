package DAOImpl;

import BD.JDBCManterConexao;
import DAO.OSDAO;
import DAO.OSStatusDAO;
import DAO.PerfilDAO;
import DAO.StatusDAO;
import DAO.UsuarioDAO;
import Domain.OSStatus;
import Domain.OSStatus;
import Domain.Status;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OSStatusDAOImpl implements OSStatusDAO{
    private static OSStatusDAOImpl osStatusDAO = null;

    private OSStatusDAOImpl() {
    }

    public static OSStatusDAOImpl getInstance() {

        if (osStatusDAO == null) {
            osStatusDAO = new OSStatusDAOImpl();
        }

        return osStatusDAO;
    }
    
    @Override
    synchronized public boolean insert(OSStatus osStatus) throws ExcecaoPersistencia {
        try {
            if (osStatus == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO osstatus ("
                    + "nro_OS, "
                    + "dat_ocorrencia, "
                    + "cod_usuario, "
                    + "cod_status "
                    + ") VALUES(?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, osStatus.getOs().getId());
            pstmt.setTimestamp(2, new Timestamp(osStatus.getDatOcorrencia()));
            pstmt.setLong(3, osStatus.getUsuario().getId());
            pstmt.setLong(4, osStatus.getStatus().getId());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSStatusDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSStatus> listAllByOS(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM osStatus WHERE nro_OS = ? ORDER BY dat_ocorrencia";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            List<OSStatus> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            UsuarioDAO usuarioDAOImpl = UsuarioDAOImpl.getInstance();
            StatusDAO statusDAOImpl = StatusDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSStatus osStatus = new OSStatus(
                        osDAOImpl.getOSById(rs.getLong("nro_OS")),
                        rs.getTimestamp("dat_ocorrencia").getTime(),
                        usuarioDAOImpl.consultarPorId(rs.getLong("cod_usuario")),
                        statusDAOImpl.getStatusById(rs.getInt("cod_status"))
                    );
                    listAll.add(osStatus);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSStatusDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSStatus> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM osStatus ORDER BY cod_osStatus;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<OSStatus> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            UsuarioDAO usuarioDAOImpl = UsuarioDAOImpl.getInstance();
            //StatusDAO statusDAOImpl = StatusDAOImp.getInstance();
            if (rs.next()) {
                do {
                    OSStatus osStatus = new OSStatus(
                        osDAOImpl.getOSById(rs.getLong("nro_OS")),
                        rs.getTimestamp("dat_ocorrencia").getTime(),
                        usuarioDAOImpl.consultarPorId(rs.getLong("cod_usuario")),
                        new Status(rs.getInt("cod_status"))//Modificar futuramente
                    );
                    listAll.add(osStatus);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSStatusDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public void deleteAll(Long idOS) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM osStatus WHERE nro_OS=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, idOS);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
}
