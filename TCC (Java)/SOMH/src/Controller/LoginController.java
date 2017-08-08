/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import Main.Run;
import ServiceImpl.ManterUsuarioImpl;
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
import Service.ManterUsuario;

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
    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void setRun(Run run) {
        this.run = run;
    }
    
    
    @FXML
    private void login(ActionEvent event) throws ExcecaoPersistencia {
        String nomeUsuario = user.getText();
        String senha = password.getText();
        System.out.println(nomeUsuario + ", " + senha);
        ManterUsuario manterUsuario = new ManterUsuarioImpl();
        //usuarioLogado = manterUsuario.getUsuarioByEmailSenha(nomeUsuario, senha);
        //Long codPerfil = usuarioLogado.getPerfil().getId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
}
