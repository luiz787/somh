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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do administrador...");
    }

    public void cadastroOrcamento() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CadastroOrçamentoView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cadastroOS() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CadastroOSView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void procuraOS() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/BuscaOSView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void cadastroFuncionario() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CadastroFuncionarioView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void cadastroCliente() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CadastroClienteView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void procuraCliente() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/BuscaClienteView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void sair() throws Exception {
        System.exit(0);
    }
}
