/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.OS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andro
 */
public class OSDAO implements OSDAOInterface {

    @Override
    public boolean insert(OS os) throws OSDAOException {
        boolean bRet = false;
        try {
            System.out.println("Deu");
            Connection conexao = JDBCManterConexao.getInstancia().getConexao();
            
            String sql = "INSERT INTO os (cod_cpf_cnpj, seq_equipto, "
            +"txt_reclamacao) VALUES (?,?,?)";
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.setInt(1, os.getCod_Cpf_Cnpj());
            pstmt.setInt(2, os.getSeq_Equipto());
            pstmt.setString(3, os.getTxt_Reclamacao());
            System.out.println("Deu2");
            
            if(pstmt.executeUpdate()!=0) {
                bRet = true;
            }
            
            ResultSet rs;
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM OS");
            
            if(rs.next()) {
                int nro_OS = rs.getInt(1);
                os.setNro_OS(nro_OS);
            }
            
            rs.close();
            pstmt.close();
            conexao.close();
            
        } catch (Exception ex) {
            throw new OSDAOException(ex);
        }
        return bRet;
    }
    
}
