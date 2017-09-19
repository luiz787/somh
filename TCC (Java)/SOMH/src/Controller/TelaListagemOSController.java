/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Domain.Equipamento;
import Domain.Status;
import Main.Run;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class TelaListagemOSController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private Label faixa;
    @FXML
    private TableView<Equipamento> listaOS;
    @FXML
    private TableColumn<Equipamento, String> colunaEquipamento;
    @FXML
    private TableColumn<Long, String> colunaCodigo;
    @FXML
    private TableColumn<Long, String> colunaDataReceb;
    @FXML
    private TableColumn<String, String> colunaStatus;
    @FXML
    private TableColumn<Long, String> colunaDataFecha;
    @FXML
    private Button pesquisa;
    @FXML
    private ChoiceBox<String> filtroOS;

    private Run run;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listStatus = FXCollections.observableArrayList("Em orçamento", "Orçado");
        filtroOS.setItems(listStatus);
        /*
        try {
            
            listaOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaEquipamento.setCellValueFactory(cellData -> cellData.getValue().DesEquiptoProperty());
            ManterEquipamento manterEquipamento = new ManterEquipamentoImpl(EquipamentoDAOImpl.getInstance());
            
            List<Equipamento> listaEquipamento = manterEquipamento.getAll();
            
            ObservableList<Equipamento> novoEquipamentoData = FXCollections.observableArrayList();
            for(int i=0; i<listaEquipamento.size(); i++) {
                novoEquipamentoData.add(listaEquipamento.get(i));
            }
            
            listaOS.setItems(novoEquipamentoData);
            
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaListagemOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }
    
}
