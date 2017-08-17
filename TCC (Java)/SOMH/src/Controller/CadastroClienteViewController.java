/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.CEPDAOImpl;
import DAOImpl.CidadeDAOImpl;
import DAOImpl.ClienteDAOImpl;
import DAOImpl.UfDAOImpl;
import Domain.CEP;
import Domain.Cidade;
import Domain.Cliente;
import Domain.UF;
import Main.Run;
import Service.ManterCEP;
import Service.ManterCidade;
import Service.ManterCliente;
import Service.ManterUF;
import ServiceImpl.ManterCEPImpl;
import ServiceImpl.ManterCidadeImpl;
import ServiceImpl.ManterClienteImpl;
import ServiceImpl.ManterUFImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private SplitMenuButton estado;
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

    /**
     * Initializes the controller class.
     */
    
     public void setRun(Run run) {
        this.run = run;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    @FXML
    public void Cancelar(ActionEvent event){}
    @FXML
    private void cadastraCliente(ActionEvent event) {
        
        try {
           
            ManterCEP mantercep = new ManterCEPImpl(CEPDAOImpl.getInstance());
            ManterCidade mantercidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
            ManterUF manteruf = new ManterUFImpl(UfDAOImpl.getInstance());
            ManterCliente mantercliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
            
            
            Cliente cliente = new Cliente();
            CEP cep = new CEP();
            UF uf = new UF();
            Cidade city = new Cidade();
            
            
           
            
            //Valida os dados no formulário
             if(!(validacao())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText("Erro");
                alert.setContentText("Preencha os campos corretamente!(Campos com * são obrigatórios!)");

                alert.showAndWait();
                throw new Exception("Preencha os campos corretamente!(Campos com * são obrigatórios!)");
            }
            
            uf = manteruf.getUFById("MG");
            city.setNome(cidade.getText());
            city.setUf(uf);
            
            cep.setCidade(city);
            cep.setNroCEP(Integer.parseInt(CEP_cliente.getText()));
            
            cliente.setCep(cep);
            cliente.setCodCPF_CNPJ(Long.parseLong(id_cliente.getText()));
            cliente.setEmail(email_cliente.getText());
            cliente.setNroTelefoneCelular(cel_cliente.getText());
            
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
           
           if(mantercidade.getCidadeById(Long.parseLong(cidade.getText()))==null){
               mantercidade.cadastrarCidade(city);
           }
           
           mantercliente.cadastrarCliente(cliente);
           
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Concluído");
            alert.setContentText("O Cliente foi cadastrada com sucesso");

            alert.showAndWait();
            
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
        if(estado.getText().isEmpty()) {
            validacao=false;
        }
        return validacao;
    }

    
}
