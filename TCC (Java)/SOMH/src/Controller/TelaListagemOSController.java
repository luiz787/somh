/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.EquipamentoDAOImpl;
import Domain.Equipamento;
import Exception.ExcecaoPersistencia;
import Service.ManterEquipamento;
import ServiceImpl.ManterEquipamentoImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
}
