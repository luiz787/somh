/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Perfil;
import Domain.Usuario;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterUsuario;
import ServiceImpl.ManterUsuarioImpl;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastroUsuarioController implements Initializable {

    @FXML
    private Button btnCadastrarCliente;
    @FXML
    private TextField nom_usuario;
    @FXML
    private TextField txt_senha;
    @FXML
    private TextField confirmaSenha;
    @FXML
    private SplitMenuButton des_perfil;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnSair;
    private Run run;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }

    @FXML
    private void mouseEntrou(MouseEvent event) {
        btnCadastrarCliente.setBackground(new Background(new BackgroundFill(Paint.valueOf("#eff1f4"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnCadastrarCliente.setBorder(new Border(new BorderStroke(Paint.valueOf("#eff1f4"), BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
    }
    
    @FXML
    private void mouseClick(ActionEvent event) {
        System.out.println("test");
        btnCadastrarCliente.setBackground(new Background(new BackgroundFill(Paint.valueOf("#d0d2d8"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnCadastrarCliente.setBorder(new Border(new BorderStroke(Paint.valueOf("#d0d2d8"), BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
    }
    
    @FXML
    private void confirma() {
        ManterUsuario manterUser = new ManterUsuarioImpl();
        
        Usuario user = new Usuario();
        Perfil perfil = new Perfil();
        
        user.setNome(nom_usuario.getText());
        
        perfil.setDescricao(des_perfil.getText());
        user.setPerfil(perfil);
        
        user.setSenha(txt_senha.getText());
        
        try {
            manterUser.cadastrar(user);
        } catch (ExcecaoPersistencia | ExcecaoNegocio ex) {
            
        }
    }
    
    @FXML
    private void sair() {
        
    }
}
