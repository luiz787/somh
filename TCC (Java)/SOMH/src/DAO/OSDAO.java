/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Acessorio;
import Model.DateUtil;
import Model.Equipamento;
import Model.OS;
import Model.OSStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andro
 */
public class OSDAO implements OSDAOInterface {

    @Override
    public void insert(Equipamento equipamento, OS os, ArrayList<Acessorio> acessorios, OSStatus osStatus) throws OSDAOException {
        Connection conexao;
        String sql;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conexao = JDBCManterConexao.getInstancia().getConexao();
            
            sql = "INSERT INTO equipamento (des_marca, des_equipto, des_modelo,"
                + " des_componentes, nro_serie) VALUES (?,?,?,?,?)";
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, equipamento.getDes_Marca());
            pstmt.setString(2, equipamento.getDes_Equipto());
            
            if(equipamento.getDes_Modelo()!=null) {
                pstmt.setString(3, equipamento.getDes_Modelo());
            } else {
                pstmt.setNull(3, java.sql.Types.NULL);
            }
            if(equipamento.getDes_Componentes()!=null) {
                pstmt.setString(4, equipamento.getDes_Componentes());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if(equipamento.getNro_Serie()!=null) {
                pstmt.setLong(5, equipamento.getNro_Serie());
            } else {
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            
            pstmt.executeUpdate();
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM equipamento");
            
            if(rs.next()) {
                int seq_equipto = rs.getInt(1);
                equipamento.setSeq_Equipto(seq_equipto);
                os.setSeq_Equipto(seq_equipto);
            }
            
            sql = "INSERT INTO os (cod_cpf_cnpj, seq_equipto, "
            +"txt_reclamacao, txt_observacao_acessorios) VALUES (?,?,?,?)";
            pstmt = conexao.prepareStatement(sql);
            
            
            pstmt.setString(1, os.getCod_Cpf_Cnpj());
            pstmt.setInt(2, os.getSeq_Equipto());
            pstmt.setString(3, os.getTxt_Reclamacao());
            if(os.getTxt_Observacao_Acessorios()!=null && !(os.getTxt_Observacao_Acessorios().isEmpty())) {
                pstmt.setString(4, os.getTxt_Observacao_Acessorios());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            
            pstmt.executeUpdate();
            
            rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM os");
            
            if(rs.next()) {
                int nro_OS = rs.getInt(1);
                os.setNro_OS(nro_OS);
                osStatus.setNro_OS(nro_OS);
            }
            if(!(acessorios.isEmpty())) {
                rs = pstmt.executeQuery("SELECT * FROM acessorio");
                ArrayList<Acessorio> cadastrados = new ArrayList<Acessorio>();
                ArrayList<String> cadastradosString = new ArrayList<String>();
                while(rs.next()) {
                    cadastrados.add(new Acessorio(rs.getInt(1), rs.getString(2)));
                    cadastradosString.add(rs.getString(2));
                }
                for(int i=0; i<acessorios.size(); i++) {
                    
                    if(!(cadastradosString.contains(acessorios.get(i).getNom_Acessorio()))) {
                        sql = "INSERT INTO acessorio (nom_acessorio) VALUES (?)";
                        pstmt = conexao.prepareStatement(sql);

                        pstmt.setString(1, acessorios.get(i).getNom_Acessorio());

                        pstmt.executeUpdate();

                        rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM acessorio");

                        if(rs.next()) {
                            int cod_acessorio = rs.getInt(1);
                            acessorios.get(i).setCod_Acessorio(cod_acessorio);
                        }
                        sql = "INSERT INTO osacessorio "
                        + "(nro_OS, cod_acessorio) VALUES (?,?)";
                        pstmt = conexao.prepareStatement(sql);

                        pstmt.setInt(1, os.getNro_OS());
                        pstmt.setInt(2, acessorios.get(i).getCod_Acessorio());

                        pstmt.executeUpdate();
                    } else {
                        sql = "INSERT INTO osacessorio "
                        + "(nro_OS, cod_acessorio) VALUES (?,?)";
                        pstmt = conexao.prepareStatement(sql);

                        pstmt.setInt(1, os.getNro_OS());
                        pstmt.setInt(2, cadastrados.get(cadastradosString.indexOf(acessorios.get(i).getNom_Acessorio())).getCod_Acessorio());

                        pstmt.executeUpdate();
                    }
                }
            }
            sql = "INSERT INTO osstatus (nro_OS, dat_ocorrencia, cod_usuario,"
                + " cod_status) VALUES (?, ?,?,?)";
            
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setInt(1, osStatus.getNro_OS());
            pstmt.setTimestamp(2, new Timestamp(osStatus.getDat_Ocorrencia()));
            pstmt.setInt(3, osStatus.getCod_Usuario());
            pstmt.setInt(4, osStatus.getCod_Status());
            
            pstmt.executeUpdate();
            
            rs.close();
            pstmt.close();
            conexao.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            throw new OSDAOException(ex);
        }
    }
    
}
