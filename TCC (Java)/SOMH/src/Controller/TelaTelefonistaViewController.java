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
    
    private Run run;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando tela do telefonista...");
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
    
    public void setRun(Run run){
        this.run = run;
    }

}
