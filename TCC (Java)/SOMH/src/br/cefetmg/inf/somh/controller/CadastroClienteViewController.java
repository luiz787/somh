/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

import br.cefetmg.inf.somh.dao.impl.CEPDAOImpl;
import br.cefetmg.inf.somh.dao.impl.CidadeDAOImpl;
import br.cefetmg.inf.somh.dao.impl.ClienteDAOImpl;
import br.cefetmg.inf.somh.dao.impl.UfDAOImpl;
import br.cefetmg.inf.somh.domain.CEP;
import br.cefetmg.inf.somh.domain.Cidade;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.domain.UF;
import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.main.Run;
import br.cefetmg.inf.somh.service.ManterCEP;
import br.cefetmg.inf.somh.service.ManterCidade;
import br.cefetmg.inf.somh.service.ManterCliente;
import br.cefetmg.inf.somh.service.ManterUF;
import br.cefetmg.inf.somh.service.impl.ManterCEPImpl;
import br.cefetmg.inf.somh.service.impl.ManterCidadeImpl;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastroClienteViewController implements Initializable {

    ObservableList<String> ufs; //variável com lista de ufs
    private TelaListagemClienteController listacliente; //instancia para pegar cliente escolhido na lista
    
    ManterCEP mantercep = new ManterCEPImpl(CEPDAOImpl.getInstance());
    ManterCidade mantercidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
    ManterUF manteruf = new ManterUFImpl(UfDAOImpl.getInstance());
    ManterCliente mantercliente = new ManterClienteImpl(ClienteDAOImpl.getInstance()); //inicia classes DAO
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private HBox hbox;
    @FXML
    private TextField nom_cliente;
    @FXML
    private TextField id_cliente;
    @FXML
    private TextField email_cliente;
    @FXML
    private TextField tel_cliente;
    @FXML
    private TextField cel_cliente;
    @FXML
    private TextField CEP_cliente;
    @FXML
    private ChoiceBox estado;
    @FXML
    private TextField cidade;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField complemento;
    @FXML
    private Button criar;
    @FXML
    private Button cancelar;
    @FXML
    private Label faixa1;
    @FXML
    private Label faixa;
    private Run run;
    
     private Usuario usuarioLogado;

    private boolean criaOS;

    public boolean isCriaOS() {
        return criaOS;
    }

    public void setCriaOS(boolean criaOS) {
        this.criaOS = criaOS;
    }
    
     public void setRun(Run run) {
        this.run = run;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usuarioLogado = LoginController.getUsuarioLogado();
            ManterUF esta= new ManterUFImpl(UfDAOImpl.getInstance());
            ufs = FXCollections.observableArrayList();
            for(int x=1;x<esta.getAll().size();x++){
                ufs.add(esta.getAll().get(x).getId());
            }
            estado.setItems(ufs); //preemche a lista de estado
            listacliente = new TelaListagemClienteController();
            if(listacliente.alterar){   //verifica se
                Cliente clienteaux = new Cliente();
                clienteaux = mantercliente.getClienteById(listacliente.ClienteSelect);
                id_cliente.setEditable(false);
                id_cliente.setText(clienteaux.getCodCPF_CNPJ().toString());
                nom_cliente.setText(clienteaux.getNome());
                email_cliente.setText(clienteaux.getEmail());
                if(clienteaux.getNroTelefoneFixo() != null){
                    tel_cliente.setText(clienteaux.getNroTelefoneFixo());
                }
                cel_cliente.setText(clienteaux.getNroTelefoneCelular());
                CEP_cliente.setText(clienteaux.getCep().getNroCEP().toString());
                estado.setValue(clienteaux.getCep().getCidade().getUf().getId());
                cidade.setText(clienteaux.getCep().getCidade().getNome());
                rua.setText(clienteaux.getEndereco());
                if(clienteaux.getNroEndereco() != null){
                    numero.setText(clienteaux.getNroEndereco().toString());
                }
                if(clienteaux.getDescricaoComplemento() != null){
                    complemento.setText(clienteaux.getDescricaoComplemento());
                }
                
            }else{
                estado.setValue("MG");
            }
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
        
    }    
    @FXML
    public void Cancelar(ActionEvent event){
        voltar();
    }
    @FXML
    private void cadastraCliente(ActionEvent event) {
        
        try {
           
            
            
            
            Cliente cliente = new Cliente();
            CEP cep = new CEP();
            UF uf = new UF();
            Cidade city = new Cidade();
            
            
           
            
            //Valida os dados no formulário
             if(!(validacao())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText("Erro");
                alert.setContentText("Preencha os campos corretamente! (Campos com * são obrigatórios!)");

                alert.showAndWait();
                throw new Exception("Preencha os campos corretamente! (Campos com * são obrigatórios!)");
            }
            
             
            uf = manteruf.getUFById(estado.getValue().toString());
            
            city.setNome(cidade.getText());
            city.setUf(uf);
            
            boolean testexiste=false;
            
            for(int i=0;i<mantercidade.getAll().size();i++){
                if( mantercidade.getAll().get(i).getNome().equals(cidade.getText())){
                    city.setId(mantercidade.getAll().get(i).getId());
                    testexiste=true;
                }
            }
            if(!testexiste) {  
                city.setId(mantercidade.cadastrarCidade(city));
            }
            
            cep.setCidade(city);
            cep.setNroCEP(Integer.parseInt(CEP_cliente.getText()));
            cliente.setCep(cep);
            cliente.setCodCPF_CNPJ(Long.parseLong(id_cliente.getText()));
            cliente.setEmail(email_cliente.getText());
            cliente.setNroTelefoneCelular(cel_cliente.getText());
            cliente.setNome(nom_cliente.getText());
            cliente.setEndereco(rua.getText());
            if(!tel_cliente.getText().isEmpty()){
                cliente.setNroTelefoneFixo(tel_cliente.getText());
            }
            
            if(!numero.getText().isEmpty()){
                cliente.setNroEndereco(Integer.parseInt(numero.getText()));
            }
            
            if(!complemento.getText().isEmpty()){
                cliente.setDescricaoComplemento(complemento.getText());
            }
            
           if(mantercep.getCEPById(Integer.parseInt(CEP_cliente.getText()))==null){
               mantercep.cadastrarCEP(cep);
               
           }
            
            if(listacliente.alterar){
                
                listacliente.alterar=false; //reseta cliente selecionado
                mantercliente.alterarCliente(cliente);
            }else{
                mantercliente.cadastrarCliente(cliente);
            }
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Concluído");
            alert.setContentText("O Cliente foi cadastrado com sucesso!");

            alert.showAndWait();
            if(criaOS) {
                try {
                    TelaCadastroOSController controlador = new TelaCadastroOSController(cliente);
                    controlador.setRun(run);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Run.class.getResource("../view/TelaCadastroOSView.fxml"));
                    loader.setController(controlador);
                    AnchorPane TelaCadastroOS = (AnchorPane) loader.load();
                    run.getRootLayout().setCenter(TelaCadastroOS);
                } catch (IOException ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                voltar();
            }
        } catch (Exception ex) {
            System.out.println("Problema ao criar Cliente: "+ex);
        }
        
    }

    private boolean validacao() {
        boolean validacao=true;
        if(nom_cliente.getText().isEmpty()) {
            validacao=false;
        }
        if(id_cliente.getText().isEmpty()) {
            validacao=false;
        }
        if(email_cliente.getText().isEmpty()) {
            validacao=false;
        }
        if(cel_cliente.getText().isEmpty()) {
            validacao=false;
        }
        if(CEP_cliente.getText().isEmpty()) {
            validacao=false;
        }
        if(cidade.getText().isEmpty()) {
            validacao=false;
        }
        if(rua.getText().isEmpty()) {
            validacao=false;
        }
       
        return validacao;
    }
    
    private void voltar(){
        try{
            String telaUsuario="";
            FXMLLoader loader;
            switch(Integer.parseInt(usuarioLogado.getPerfil().getId().toString())) {
                case 1: 
                    telaUsuario = "TelaAdministradorView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../view/"+telaUsuario));
                    AnchorPane TelaAdministrador = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAdministrador);

                    TelaAdministradorViewController controllerAdministrador = loader.getController();
                    controllerAdministrador.setRun(run);
                break;
                case 2:
                    telaUsuario = "TelaAtendenteView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../view/"+telaUsuario));
                    AnchorPane TelaAtendente = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAtendente);

                    TelaAtendenteViewController controllerAtendente = loader.getController();
                    controllerAtendente.setRun(run);
                break;
                case 3:
                    telaUsuario = "TelaTelefonistaView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../view/"+telaUsuario));
                    AnchorPane TelaTelefonista = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaTelefonista);

                    TelaTelefonistaViewController controllerTelefonista = loader.getController();
                    controllerTelefonista.setRun(run);
                break;
                case 4:
                    telaUsuario = "TelaTecnicoView.fxml";
                    
                    loader = new FXMLLoader();
            
                    loader.setLocation(Run.class.getResource("../view/"+telaUsuario));
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
