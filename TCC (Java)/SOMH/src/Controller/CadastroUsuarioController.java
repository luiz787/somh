/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.UsuarioDAOImpl;
import Domain.Perfil;
import Domain.Usuario;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterPerfil;
import Service.ManterUsuario;
import ServiceImpl.ManterPerfilImpl;
import ServiceImpl.ManterUsuarioImpl;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
        //btnCadastrarCliente.setStyle("#eff1f4");
        //System.out.println("test");
        //btnCadastrarCliente.setBorder(new Border(new BorderStroke(Paint.valueOf("#eff1f4"), BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
    }

    @FXML
    private void mouseClick(ActionEvent event) {
        //btnCadastrarCliente.setStyle("#d0d2d8");
        //System.out.println("test");
        //btnCadastrarCliente.setBorder(new Border(new BorderStroke(Paint.valueOf("#d0d2d8"), BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
    }

    @FXML
    private void cadastraUsuario() {
        if (!validacao() && txt_senha.getText() != confirmaSenha.getText()) {
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Usuario");
                alert.setHeaderText("Erro de cadastro");
                alert.setContentText("As senhas nao sao compativeis!!!");

                alert.showAndWait();
                
                System.out.println("Deu merda, senha n bate");
                
                throw new Exception("A confirmacao da senha esta errada!");
            } catch (Exception ex) {
                Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!validacao()) {
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Usuario");
                alert.setHeaderText("Erro de cadastro");
                alert.setContentText("Preencha todos os campos!!!");

                alert.showAndWait();
                
                System.out.println("Deu merda, falto campo");
                
                throw new Exception("Preencha os campos!");
            } catch (Exception ex) {
                Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (validacao()) {
            try {
                ManterUsuario manterUser = new ManterUsuarioImpl();
                ManterPerfil manterPefil = new ManterPerfilImpl();
                Usuario user = new Usuario();
                Perfil perfil = new Perfil();
                
                user.setNome(nom_usuario.getText());
                user.setSenha(txt_senha.getText());
                
                perfil.setDescricao(des_perfil.getText());
                
                perfil.setId(Long.MIN_VALUE);
                user.setPerfil(perfil);
                user.setId(manterUser.cadastrar(user));
                
                System.out.println("cole zeze, deu certo");
                
            } catch (ExcecaoPersistencia ex) {
                System.out.println("Excecao de persistencia!");
            } catch (ExcecaoNegocio ex) {
                System.out.println("Excecao de negocio!");

            }
        }
    }

    @FXML
    private void sair() {

    }

    private boolean validacao() {

        boolean validacao = true;

        if (nom_usuario.getText().isEmpty()) {
            validacao = false;
        }

        if (txt_senha.getText().isEmpty()) {
            validacao = false;
        }

        if (confirmaSenha.getText().isEmpty()) {
            validacao = false;
        }

        if (des_perfil.getText().isEmpty()) {
            validacao = false;
        }

        if (txt_senha.getText() != confirmaSenha.getText()) {
            validacao = false;
        }

        return validacao;
    }
}
