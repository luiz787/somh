/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.main.Run;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TelaTelefonistaViewController implements Initializable {

    @FXML
    private Button procuraCliente;
    @FXML
    private Button sair;
    @FXML 
    private Label nomeLog;
    
    private Usuario user;
    
    private Run run;
    @FXML
    private Label faixa;
    @FXML
    private Button procuraOS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do telefonista...");        
        
        user = LoginController.getUsuarioLogado();
        nomeLog.setText("Logado como " + user.getNome());
    }

    @FXML
    public void procuraCliente() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaListagemClienteView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemClienteController controller = loader.getController();
        controller.setRun(run);
    }

    @FXML
    public void sair() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/Login.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        LoginController controller = loader.getController();
        controller.setRun(run);
    }
    
    public void setRun(Run run){
        this.run = run;
    }

    @FXML
    private void procuraOS(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
            AnchorPane TelaFuncionario = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaFuncionario);
            
            TelaListagemOSController controller = loader.getController();
            controller.setRun(run);
        } catch (IOException ex) {
            Logger.getLogger(TelaTelefonistaViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
