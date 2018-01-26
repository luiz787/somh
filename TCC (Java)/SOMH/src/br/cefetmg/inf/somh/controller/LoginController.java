/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.main.Run;
import br.cefetmg.inf.somh.service.ManterUsuario;
import br.cefetmg.inf.somh.service.impl.ManterUsuarioImpl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        LoginController.usuarioLogado = usuarioLogado;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    @FXML
    private void login(ActionEvent event) throws ExcecaoPersistencia, ExcecaoNegocio {
        int codPerfil = -1;
        String nomeUsuario = user.getText();
        String senha = password.getText();
        System.out.println(nomeUsuario + ", " + senha);
        ManterUsuario manterUsuario = new ManterUsuarioImpl();
        usuarioLogado = manterUsuario.getUsuarioByNomeSenha(nomeUsuario, senha);
        System.out.println(usuarioLogado == null);
        if (usuarioLogado != null) {
            codPerfil = usuarioLogado.getPerfil().getId().intValue();
            System.out.println(codPerfil);
            switch (codPerfil) {
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
                    Alert alert = new Alert(AlertType.ERROR, "Erro: perfil de usuario invalido. Tente novamente.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        user.setText("");
                        password.setText("");
                    }
                    break;
            } // arrumar ordem das chamadas
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Erro: usuario ou senha incorretos. Tente novamente.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                user.setText("");
                password.setText("");
            }
        }
        //int codPerfil = 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /*private void redirecionaTelaFuncionario() {
        System.out.println("tela de funcionario");
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../view/teste.fxml"));
            AnchorPane TelaFuncionario = (AnchorPane) loader.load();

            run.getRootLayout().setCenter(TelaFuncionario);
            TelaFuncionarioV controllerAdministrador = loader.getController();
            controller.setRun(run);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    private void showTelaAdministradorView() {
        System.out.println("Tela Administrador");
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../view/TelaAdministradorView.fxml"));
            AnchorPane TelaAdm = (AnchorPane) loader.load();

            run.getRootLayout().setCenter(TelaAdm);

            TelaAdministradorViewController controllerAdministrador = loader.getController();
            controllerAdministrador.setRun(run);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTelaAtendenteView() {
        System.out.println("Tela Atendente");
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../view/TelaAtendenteView.fxml"));
            AnchorPane TelaAtendente = (AnchorPane) loader.load();

            run.getRootLayout().setCenter(TelaAtendente);
            TelaAtendenteViewController controllerAtendente = loader.getController();
            controllerAtendente.setRun(run);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTelaTelefonistaView() {
        System.out.println("Tela Telefonista");
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../view/TelaTelefonistaView.fxml"));
            AnchorPane TelaTelefonista = (AnchorPane) loader.load();

            run.getRootLayout().setCenter(TelaTelefonista);
            TelaTelefonistaViewController controllerTelefonista = loader.getController();
            controllerTelefonista.setRun(run);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTelaTecnicoView() {
        System.out.println("Tela Tecnico");
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../view/TelaTecnicoView.fxml"));
            AnchorPane TelaTecnico = (AnchorPane) loader.load();

            run.getRootLayout().setCenter(TelaTecnico);
            TelaTecnicoViewController controllerTecnico = loader.getController();
            controllerTecnico.setRun(run);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
