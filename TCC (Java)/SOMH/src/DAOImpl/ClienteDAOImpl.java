package DAOImpl;

import BD.JDBCManterConexao;
import DAO.ClienteDAO;
import Domain.Cliente;
import Domain.Cliente;
import Exception.ExcecaoPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAOImpl implements ClienteDAO{

    @Override
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
