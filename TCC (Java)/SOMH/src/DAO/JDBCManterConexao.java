/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author andro
 */
public class JDBCManterConexao {
    private static JDBCManterConexao conexao;
    private static JDBCFabricaConexao conFabrica;

    private JDBCManterConexao() {
        this(new JDBCConexaoMySQL());
    }
    
    private JDBCManterConexao(JDBCFabricaConexao cf) {
        this.conFabrica = cf;
    }

    public static JDBCManterConexao getInstancia() {
        if(conexao == null)
            conexao = new JDBCManterConexao();

        return conexao;
    }

    public Connection getConexao() throws Exception {

        return JDBCManterConexao.conFabrica.getConexao();
    }
}
