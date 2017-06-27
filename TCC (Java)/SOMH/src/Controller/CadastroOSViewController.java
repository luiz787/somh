/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Date;
import DAO.OSDAOException;
import DAO.JDBCManterConexao;
import DAO.OSDAO;
import Main.Run;
import Model.Acessorio;
import Model.DateUtil;
import Model.Equipamento;
import Model.OS;
import Model.OSStatus;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author andro
 */
public class CadastroOSViewController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button cancelar;
    @FXML
    private TextArea reclamacao;
    @FXML
    private TextField marca;
    @FXML
    private TextField nroSerie;
    @FXML
    private Button criar;
    @FXML
    private TextField nomeAcessorioCadastro;
    @FXML
    private TextField modelo;
    @FXML
    private TextArea observacaoAcessorio;
    @FXML
    private Button cadastroCliente;
    @FXML
    private TextField nomeEquipamento;
    @FXML
    private TextArea componentes;
    @FXML
    private Label data;
    @FXML
    private Button pesquisa;
    @FXML
    private Label nomeCliente;
    @FXML
    private Label nroOS;
    @FXML
    private Button cadastroAcessorio;
    @FXML
    private TableView<Acessorio> acessoriosCadastrados;
    @FXML
    private TableColumn<Acessorio, String> colunaAcessoriosCadastrados;
    @FXML
    private Button adicionaAcessorio;
    @FXML
    private Button removeAcessorio;
    @FXML
    private TableView<Acessorio> acessoriosSelecionados;
    @FXML
    private TableColumn<Acessorio, String> colunaAcessoriosSelecionados;
    
    private Run run;
    
    /**
     * Initializes the controller class.
     */ 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregaForm();
            //criaOS();
            nomeCliente.setText("Victor Gabriel");
            nomeEquipamento.setText("Impressora HP");
            componentes.setText("Varios componentes aqui!!! :)");
            acessoriosCadastrados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaAcessoriosCadastrados.setCellValueFactory(cellData -> cellData.getValue().Nom_AcessorioProperty());
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
    }
    
    public void setRun(Run run) {
        this.run = run;

        // Adiciona os dados da observable list na tabela
        acessoriosCadastrados.setItems(run.getAcessorioData());
    }
    /*
    private void atualizaAcessorios(Acessorio acessorio) {
        ObservableList<Acessorio> acessorioData2 = FXCollections.observableArrayList();
        acessorioData2.add(acessorio);
        run.setAcessorioData(acessorioData2);
        colunaAcessoriosSelecionados.setCellValueFactory(cellData -> cellData.getValue().Nom_AcessorioProperty());
    }
    
    @FXML
    private void adicionaAcessorio() {
        acessoriosCadastrados.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> atualizaAcessorios(newValue));
    }
    */
    @FXML
    private void carregaForm() throws Exception {
        Connection conexao;
        String sql;
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT max(nro_OS) FROM os where nro_OS");
            if(rs.next()) {
                int nro_OS_Form = rs.getInt(1);
                System.out.println(nro_OS_Form);
                nro_OS_Form++;
                nroOS.setText(String.valueOf(nro_OS_Form));
            }
            
            data.setText(DateUtil.format(LocalDate.now()));
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
    }
    @FXML
    private void criaOS() throws OSDAOException {
        Connection conexao;
        String sql;
        PreparedStatement pstmt;
        ResultSet rs;
        
        OSDAO daoOS = new OSDAO();
        Equipamento equipamento = new Equipamento();
        OS os = new OS();
        ArrayList<Acessorio> acessorios = new ArrayList<Acessorio>();
        OSStatus osStatus = new OSStatus();
        
        equipamento.setDes_Marca(marca.getText());
        equipamento.setDes_Equipto(nomeEquipamento.getText());
        equipamento.setDes_Modelo(modelo.getText());
        equipamento.setDes_Componentes(componentes.getText());
        equipamento.setNro_Serie(Integer.parseInt(nroSerie.getText()));
        
        os.setCod_Cpf_Cnpj("77777777777");
        /*
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT cod_cpf_cnpj FROM cliente where 
            nom_cliente="+nomeCliente.getText());
            if(rs.next()) {
                os.setCod_Cpf_Cnpj(rs.getString(1));
            }
        */
        os.setTxt_Reclamacao(reclamacao.getText());
        os.setTxt_Observacao_Acessorios(observacaoAcessorio.getText());
        
        /*for(int i=0; i<acessoriosCadastrados.getItems().size(); i++) {
            acessoriosCadastrados.getItems().get(0).getNom_Acessorio();
        }*/
        
        
        
        daoOS.insert(equipamento, os, acessorios, osStatus);
        
    }
    @FXML
    private void  hello() {
        System.out.println("hy");
    }
    
}
