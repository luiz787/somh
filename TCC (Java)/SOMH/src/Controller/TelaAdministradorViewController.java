/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Run;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TelaAdministradorViewController extends Run implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button cadastroOrcamento;
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
    
    private Run run;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do administrador...");
    }

    public void cadastroOrcamento() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaOrcamentoView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
    }

    public void procuraOS() throws Exception {
        
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
        
    }

    public void cadastroFuncionario() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/CadastroUsuario.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        CadastroUsuarioController controller = loader.getController();
        controller.setRun(run);
    }

    public void cadastroOS() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        TelaCadastroOSController controlador = new TelaCadastroOSController(null);
        loader.setController(controlador);
        loader.setLocation(Run.class.getResource("../View/TelaCadastroOSView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaCadastroOSController controller = loader.getController();
        controller.setRun(run);
    }
    
    public void cadastroCliente() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/CadastroClienteView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        CadastroClienteViewController controller = loader.getController();
        controller.setRun(run);
    }

    public void procuraCliente() throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaListagemClienteView.fxml"));
        AnchorPane TelaFuncionario = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemClienteController controller = loader.getController();
        controller.setRun(run);
    }

    public void sair() throws Exception {
        System.exit(0);
    }
    
    public void setRun (Run run){
        this.run = run;
    }
}
