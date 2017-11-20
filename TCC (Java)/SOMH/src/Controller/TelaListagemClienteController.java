/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.ClienteDAOImpl;
import DAOImpl.UfDAOImpl;
import Domain.Cidade;
import Domain.Cliente;
import Domain.UF;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterCliente;
import Service.ManterUF;
import ServiceImpl.ManterClienteImpl;
import ServiceImpl.ManterUFImpl;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author F43L
 */
public class TelaListagemClienteController implements Initializable {
    
    
    ObservableList<String> parametros =FXCollections.observableArrayList("Nome","CPF/CNPJ");
    
    public static Long ClienteSelect;
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private Label faixa;
    @FXML
    private TableView<Cliente> listaCliente;
    @FXML
    private TableColumn<Cliente, Long> colunaCPF;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaEmail;
    @FXML
    private TableColumn<Cliente, String> colunaTel;
    @FXML
    private TableColumn<Cliente, String> colunaCel;
    @FXML
    private TextField textoPesquisa;
    @FXML
    private Button pesquisa;
    @FXML
    private ChoiceBox filtroCliente;
    @FXML
    private CheckBox exibirEndereco;
    
    private Run run;
    
    ManterCliente mantercliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
    
    public ObservableList<Cliente> listCliente;
    public ObservableList<Cliente> listClienteaux; //lista de Clientes após filtro de pesquisa
    

    public TelaListagemClienteController() {
        try {
            listCliente = FXCollections.observableArrayList(mantercliente.getAll());
            
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaListagemClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            filtroCliente.setItems(parametros);
          
           colunaCPF.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("codCPF_CNPJ"));
           colunaNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
           colunaEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
           colunaTel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nroTelefoneFixo"));
           colunaCel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nroTelefoneCelular"));
           
           
           listaCliente.setItems(listCliente);
           
           } catch (Exception ex) {
            System.out.println("Problema ao carregar clientes: "+ex);
        }
        listaCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    ClienteSelect = listaCliente.getSelectionModel().getSelectedItem().getCodCPF_CNPJ();
                    System.out.println(ClienteSelect);
                }
            }
        }} 
    
    
     public void setRun(Run run) {
        this.run = run;
    }

        
    
    @FXML
    private void realizarPesquisa(ActionEvent event) {
        try {
           
           ClienteSelect = listaCliente.getSelectionModel().getSelectedItem().getCodCPF_CNPJ();
                        System.out.println(ClienteSelect);
          
            
            listCliente.clear(); 
            listCliente = FXCollections.observableArrayList(mantercliente.getAll()); //repopula a lista de clientes
            
            listClienteaux = FXCollections.observableArrayList(); //inicia lista auxiliar
            
            
            for(int i=0; i<listCliente.size();i++){
                
                if(listCliente.get(i).getCodCPF_CNPJ().toString().contains(textoPesquisa.getText()) && filtroCliente.getValue().toString().contains("CPF/CNPJ")){
                    listClienteaux.add(listCliente.get(i));
                    System.out.println("CPF");
                }
                if(listCliente.get(i).getNome().toLowerCase().contains(textoPesquisa.getText().toLowerCase()) && filtroCliente.getValue().toString().contains("Nome")){
                    listClienteaux.add(listCliente.get(i));
                    System.out.println("nome");
                }
                
                
            }
            System.out.println("debug");
            if(listClienteaux.isEmpty()){
                listaCliente.setItems(listCliente);
            }else{
            listaCliente.setItems(listClienteaux);}
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Pesquisa Concluida!");
            alert.setHeaderText("Concluído");
            alert.setContentText("A Pesquisa retornou "+listClienteaux.size()+" resultados!");

            alert.showAndWait();
            
        } catch (Exception ex) {
            System.out.println("Problema ao criar Cliente: "+ex);
        }
    }
}
