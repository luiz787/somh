package Controller;

import Main.Run;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TelaOSController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private Label faixa;
    @FXML
    private Pane painel;
    @FXML
    private Label nroOS;
    @FXML
    private Label nomeCliente;
    @FXML
    private Button alterarBtn;
    @FXML
    private Button excluirBtn;
    @FXML
    private Button voltarBtn;
    @FXML
    private TextArea textComponentes;
    @FXML
    private ListView<?> listAcessorios;
    @FXML
    private TextField nomeEquipamento;
    @FXML
    private TextField nomeMarca;
    @FXML
    private TextField nomeModelo;
    @FXML
    private TextField nroSerie;
    @FXML
    private TextArea textReclamacao;
    @FXML
    private TextArea textObservacao;
    @FXML
    private Label statusOS;
    @FXML
    private Label dataCriacao;

    private Run run;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }

    @FXML
    private void redirecionaTelaCliente(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaCliente.fxml"));
            AnchorPane TelaCliente = (AnchorPane) loader.load();
            //run.setCliente(cliente);
            run.getRootLayout().setCenter(TelaCliente);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void alterarOS(ActionEvent event) {
    }

    @FXML
    private void excluirOS(ActionEvent event) {
    }

    @FXML
    private void voltarOS(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOS.fxml"));
            AnchorPane TelaListagemOS = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaListagemOS);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
