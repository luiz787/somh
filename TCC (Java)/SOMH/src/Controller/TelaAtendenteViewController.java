/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TelaAtendenteViewController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button cadastroOS;
    @FXML
    private Button procuraOS;
    @FXML
    private Button cadastroCliente;
    @FXML
    private Button sair;
    @FXML
    private Label nomeFuncionario;
    /**
     * Initializes the controller class.
     */
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do atendente...");
    }

    public void cadastroOS() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CadastroOSView.fxml"));
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


    public void procuraOS() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/BuscaOSView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void sair() throws Exception {
        System.exit(0);
    }
}
