/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Run;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    private Run run;

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do telefonista...");
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
