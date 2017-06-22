/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Acessorio;
import Model.Equipamento;
import Model.Marca;
import Model.OS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andro
 */
public class CadastroOSDAO implements CadastroOSDAOInterface {

    @Override
    public boolean insert(Equipamento equipamento, Marca marca, OS os, ArrayList<Acessorio> acessorios, String observacao) throws CadastroOSDAOException {
        boolean bRet = false;
        Connection conexao;
        String sql;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conexao = JDBCManterConexao.getInstancia().getConexao();
            
            sql = "INSERT INTO marca (nom_marca) VALUES (?)";
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, marca.getNom_Marca());
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM marca");;
            
            if(rs.next()) {
                int cod_marca = rs.getInt(1);
                marca.setCod_Marca(cod_marca);
                equipamento.setCod_Marca(cod_marca);
            }
            
            sql = "INSERT INTO equipamento (cod_marca, des_equipto, des_modelo,"
                + " des_componentes, nro_serie) VALUES (?,?,?,?,?)";
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setInt(1, equipamento.getCod_Marca());
            pstmt.setString(2, equipamento.getDes_Equipto());
            pstmt.setString(3, equipamento.getDes_Modelo());
            pstmt.setString(4, equipamento.getDes_Componentes());
            pstmt.setInt(5, equipamento.getNro_Serie());
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM equipamento");;
            
            if(rs.next()) {
                int seq_equipto = rs.getInt(1);
                equipamento.setSeq_Equipto(seq_equipto);
                os.setSeq_Equipto(seq_equipto);
            }
            
            sql = "INSERT INTO os (cod_cpf_cnpj, seq_equipto, "
            +"txt_reclamacao) VALUES (?,?,?)";
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setInt(1, os.getCod_Cpf_Cnpj());
            pstmt.setInt(2, os.getSeq_Equipto());
            pstmt.setString(3, os.getTxt_Reclamacao());
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM os");
            
            if(rs.next()) {
                int nro_OS = rs.getInt(1);
                os.setNro_OS(nro_OS);
            }
            
            for(int i=0; i<acessorios.size(); i++) {
                sql = "INSERT INTO acessorio (nom_acessorio) VALUES (?)";
                pstmt = conexao.prepareStatement(sql);
                
                pstmt.setString(1, acessorios.get(i).getNom_Acessorio());
                
                rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM acessorio");

                if(rs.next()) {
                    int cod_acessorio = rs.getInt(1);
                    acessorios.get(i).setCod_Acessorio(cod_acessorio);
                }
                
                sql = "INSERT INTO osacessorio "
                + "(nro_OS, cod_acessorio, txt_observacao) VALUES (?,?,?)";
                pstmt = conexao.prepareStatement(sql);
                pstmt.setInt(1, os.getNro_OS());
                pstmt.setInt(2, acessorios.get(i).getCod_Acessorio());
                pstmt.setString(3, observacao);
            }
            
            
            
            
            
            if(pstmt.executeUpdate()!=0) {
                bRet = true;
            }
            
            rs.close();
            pstmt.close();
            conexao.close();
            
        } catch (Exception ex) {
            throw new CadastroOSDAOException(ex);
        }
        return bRet;
    }
    
}
