/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.main.Run;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TelaAdministradorViewController extends Run implements Initializable {

    @FXML
    private Button procuraOS;
    @FXML
    private Button sair;
    @FXML
    private Button cadastroFuncionario;
    @FXML
    private Button cadastroOS;
    @FXML
    private Button cadastroCliente;
    @FXML
    private Button procuraCliente;
    @FXML
    private Label nomeLog;
    
    private Usuario usuario;
    
    private Run run;
    @FXML
    private Label faixa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do administrador...");
        
        usuario = LoginController.getUsuarioLogado();
        nomeLog.setText("Logado como " + usuario.getNome());        
    }

    @FXML
    public void procuraOS() throws Exception {
        
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/TelaListagemOSView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
        
    }

    @FXML
    public void cadastroFuncionario() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/CadastroUsuario.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        CadastroUsuarioController controller = loader.getController();
        controller.setRun(run);
    }

    @FXML
    public void cadastroOS() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        TelaCadastroOSController controlador = new TelaCadastroOSController(null);
        loader.setController(controlador);
        loader.setLocation(Run.class.getResource("../view/TelaCadastroOSView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaCadastroOSController controller = loader.getController();
        controller.setRun(run);
    }
    
    @FXML
    public void cadastroCliente() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/CadastroClienteView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        CadastroClienteViewController controller = loader.getController();
        controller.setRun(run);
    }

    @FXML
    public void procuraCliente() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/TelaListagemClienteView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemClienteController controller = loader.getController();
        controller.setRun(run);
    }

    @FXML
    public void sair() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/Login.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        LoginController controller = loader.getController();
        controller.setRun(run);
    }
    
    public void setRun (Run run){
        this.run = run;
    }
}
