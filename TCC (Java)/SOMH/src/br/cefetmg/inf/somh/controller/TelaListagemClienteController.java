/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

import br.cefetmg.inf.somh.dao.impl.ClienteDAOImpl;
import br.cefetmg.inf.somh.dao.impl.UfDAOImpl;
import br.cefetmg.inf.somh.domain.Cidade;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.main.Run;
import br.cefetmg.inf.somh.service.ManterCliente;
import br.cefetmg.inf.somh.service.ManterUF;
import br.cefetmg.inf.somh.service.impl.ManterClienteImpl;
import br.cefetmg.inf.somh.service.impl.ManterUFImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public static boolean alterar=false;
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
    private Button voltar;
    
    private Usuario usuarioLogado;
    
    private Run run;
    
    private boolean criaOS;

    public boolean isCriaOS() {
        return criaOS;
    }

    public void setCriaOS(boolean criaOS) {
        this.criaOS = criaOS;
    }
    
    
    
    ManterCliente mantercliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
    
    public ObservableList<Cliente> listCliente;
    public ObservableList<Cliente> listClienteaux; //lista de Clientes após filtro de pesquisa
    
    

    public TelaListagemClienteController() {
        try {
            listCliente = FXCollections.observableArrayList(mantercliente.getAll()); //prenche a lista de clientes
           
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaListagemClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            filtroCliente.setItems(parametros);
            filtroCliente.setValue("CPF/CNPJ");
            usuarioLogado = LoginController.getUsuarioLogado();
            
           colunaCPF.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("codCPF_CNPJ"));
           colunaNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
           colunaEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
           colunaTel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nroTelefoneFixo"));
           colunaCel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nroTelefoneCelular"));
           
           
           listaCliente.setItems(listCliente); //prenche a lista de clientes
           
           } catch (Exception ex) {
            System.out.println("Problema ao carregar clientes: "+ex);
            }
        
        listaCliente.setOnMouseClicked(new EventHandler<MouseEvent>() { //seleciona o cliente clicado
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    if(criaOS) {
                        try {
                            ManterCliente manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
                            Cliente cliente = manterCliente.getClienteById(
                                    listaCliente.getSelectionModel().getSelectedItem().getCodCPF_CNPJ());
                            TelaCadastroOSController controlador = new TelaCadastroOSController(cliente);
                            controlador.setRun(run);
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(Run.class.getResource("../View/TelaCadastroOSView.fxml"));
                            loader.setController(controlador);
                            AnchorPane TelaCadastroOS = (AnchorPane) loader.load();
                            run.getRootLayout().setCenter(TelaCadastroOS);
                        } catch (ExcecaoPersistencia|IOException ex) {
                            Logger.getLogger(TelaListagemClienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        alterar=true;
                        ClienteSelect = listaCliente.getSelectionModel().getSelectedItem().getCodCPF_CNPJ(); //seta o id do cliente selecionado
                        run.showCadastroClienteView(); // mostra o cliente selecionado
                    }
                }
            }
        });
    } 

    
     public void setRun(Run run) {
        this.run = run;
    }

        
    
    @FXML
    private void realizarPesquisa(ActionEvent event) {
        try {
           
          
            
            listCliente.clear(); 
            listCliente = FXCollections.observableArrayList(mantercliente.getAll()); //repopula a lista de clientes
            
            listClienteaux = FXCollections.observableArrayList(); //inicia lista auxiliar
            
            
            for(int i=0; i<listCliente.size();i++){
                
                if(listCliente.get(i).getCodCPF_CNPJ().toString().contains(textoPesquisa.getText()) && filtroCliente.getValue().toString().contains("CPF/CNPJ")){
                    listClienteaux.add(listCliente.get(i));
                    
                }
                if(listCliente.get(i).getNome().toLowerCase().contains(textoPesquisa.getText().toLowerCase()) && filtroCliente.getValue().toString().contains("Nome")){
                    listClienteaux.add(listCliente.get(i));
                    
                }
                
                
            }
            
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

    @FXML
    private void Voltar(ActionEvent event) {
        try{
            String telaUsuario="";
            FXMLLoader loader;
            switch(Integer.parseInt(usuarioLogado.getPerfil().getId().toString())) {
                case 1: 
                    telaUsuario = "TelaAdministradorView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../View/"+telaUsuario));
                    AnchorPane TelaAdministrador = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAdministrador);

                    TelaAdministradorViewController controllerAdministrador = loader.getController();
                    controllerAdministrador.setRun(run);
                break;
                case 2:
                    telaUsuario = "TelaAtendenteView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../View/"+telaUsuario));
                    AnchorPane TelaAtendente = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAtendente);

                    TelaAtendenteViewController controllerAtendente = loader.getController();
                    controllerAtendente.setRun(run);
                break;
                case 3:
                    telaUsuario = "TelaTelefonistaView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../View/"+telaUsuario));
                    AnchorPane TelaTelefonista = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaTelefonista);

                    TelaTelefonistaViewController controllerTelefonista = loader.getController();
                    controllerTelefonista.setRun(run);
                break;
                case 4:
                    telaUsuario = "TelaTecnicoView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../View/"+telaUsuario));
                    AnchorPane TelaTecnico = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaTecnico);

                    TelaTecnicoViewController controllerTecnico = loader.getController();
                    controllerTecnico.setRun(run);
                break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
