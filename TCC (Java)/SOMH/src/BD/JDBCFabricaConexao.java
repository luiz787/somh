
package BD;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCFabricaConexao {

    public Connection getConexao() throws ClassNotFoundException, SQLException;
}