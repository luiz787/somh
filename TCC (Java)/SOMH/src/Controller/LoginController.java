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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Service.ManterUsuario;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

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
        usuarioLogado = manterUsuario.getUsuarioByEmailSenha(nomeUsuario, senha);
        int codPerfil = -1;
        codPerfil = usuarioLogado.getPerfil().getId().intValue();
        System.out.println(codPerfil);
        //int codPerfil = 1;
        switch (codPerfil){
            case 1:
                showTelaAdministradorView();
                break;
            case 2:
                showTelaAtendenteView();
                break;
            case 3:
                showTelaTelefonistaView();
                break;
            case 4:
                showTelaTecnicoView();
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR, "Erro: perfil de usuário inválido. Tente novamente.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    user.setText("");
                    password.setText("");
                }
                break;
        } // arrumar ordem das chamadas
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    private void redirecionaTelaFuncionario() {
        System.out.println("tela de funcionario");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/teste.fxml"));
            AnchorPane TelaFuncionario = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaFuncionario);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTelaAdministradorView(){
        System.out.println("Tela Administrador");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaAdministradorView.fxml"));
            AnchorPane TelaAdm = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaAdm);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTelaAtendenteView(){
        System.out.println("Tela Atendente");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaAtendenteView.fxml"));
            AnchorPane TelaAtendente = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaAtendente);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTelaTelefonistaView(){
        System.out.println("Tela Telefonista");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaTelefonistaView.fxml"));
            AnchorPane TelaTelefonista = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaTelefonista);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTelaTecnicoView(){
        System.out.println("Tela Tecnico");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaTecnicoView.fxml"));
            AnchorPane TelaTecnico = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaTecnico);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
