package DAOImpl;

import BD.JDBCManterConexao;
import DAO.AcessorioDAO;
import DAO.OSAcessorioDAO;
import DAO.OSDAO;
import Domain.OSAcessorio;
import Domain.OSAcessorio;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OSAcessorioDAOImpl implements OSAcessorioDAO{
    private static OSAcessorioDAOImpl osAcessorioDAO = null;

    private OSAcessorioDAOImpl() {
    }

    public static OSAcessorioDAOImpl getInstance() {

        if (osAcessorioDAO == null) {
            osAcessorioDAO = new OSAcessorioDAOImpl();
        }

        return osAcessorioDAO;
    }
    
    @Override
    synchronized public boolean insert(OSAcessorio osAcessorio) throws ExcecaoPersistencia {
        try {
            if (osAcessorio == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO osacessorio ("
                    + "nro_OS, "
                    + "cod_acessorio"
                    + ") VALUES(?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, osAcessorio.getOs().getId());
            pstmt.setLong(2, osAcessorio.getAcessorio().getId());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public OSAcessorio delete(Long idOS, Long idAcessorio) throws ExcecaoPersistencia {
        try {
            OSAcessorio osAcessorio = this.getOSAcessorioById(idOS, idAcessorio);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM osacessorio WHERE nro_OS=? and cod_acessorio=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, idOS);
            pstmt.setLong(2, idAcessorio);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return osAcessorio;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSAcessorio> listAllByOS(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM osacessorio WHERE nro_OS=? ORDER BY cod_acessorio;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            List<OSAcessorio> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            AcessorioDAO acessorioDAOImpl = AcessorioDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSAcessorio osAcessorio = new OSAcessorio(
                        osDAOImpl.getOSById(rs.getLong("nro_OS")),
                        acessorioDAOImpl.getAcessorioById(rs.getLong("cod_acessorio"))
                    );
                    listAll.add(osAcessorio);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM osacessorio WHERE nro_OS=? and cod_acessorio=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, idOS);
            pstmt.setLong(1, idAcessorio);
            ResultSet rs = pstmt.executeQuery();
            
            OSAcessorio osAcessorio = null;
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            AcessorioDAO acessorioDAOImpl = AcessorioDAOImpl.getInstance();
            if (rs.next()) {
                osAcessorio = new OSAcessorio(
                    osDAOImpl.getOSById(rs.getLong("nro_OS")),
                    acessorioDAOImpl.getAcessorioById(rs.getLong("cod_acessorio"))
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return osAcessorio;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OSAcessorio> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM osacessorio ORDER BY nro_OS;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<OSAcessorio> listAll = new ArrayList<>();
            OSDAO osDAOImpl = OSDAOImpl.getInstance();
            AcessorioDAO acessorioDAOImpl = AcessorioDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OSAcessorio osAcessorio = new OSAcessorio(
                        osDAOImpl.getOSById(rs.getLong("nro_OS")),
                        acessorioDAOImpl.getAcessorioById(rs.getLong("cod_acessorio"))
                    );
                    listAll.add(osAcessorio);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public void deleteAll(Long idOS) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM osacessorio WHERE nro_OS=?";

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
