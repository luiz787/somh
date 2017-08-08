/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Run;
import Service.IManterUsuario;
import ServiceImpl.ManterUsuario;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Aluno
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button botaoEntrar;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    
    private Run run;

    public void setRun(Run run) {
        this.run = run;
    }
    
    
    @FXML
    private void login(ActionEvent event) {
        Connection conexao;
        String sql;
        PreparedStatement pstmt;
        ResultSet rs;
        String nomeUsuario = user.getText();
        String senha = password.getText();
        System.out.println(nomeUsuario + ", " + senha);
        IManterUsuario manterUsuario = new ManterUsuario();
        //Usuario currentUser = manterUsuario.getByUsuarioSenha(nomeUsuario, senha);
        //int codPerfil = currentUser.getPerfil().getCodigo();
        try {
            /*conexao = JDBCManterConexao.getInstancia().getConexao();
            sql=""SELECT cod_perfil
                  FROM usuario 
                  WHERE nom_usuario=(?) 
                  AND cod_senha = md5((?))"";
            pstmt.setString(1, nomeUsuario);
            pstmt.setString(2, senha);
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            codPerfil = rs.getInt(1);*/
            
            
            
        } catch (Exception ex) {
            System.out.println("Erro de persistência");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
}
