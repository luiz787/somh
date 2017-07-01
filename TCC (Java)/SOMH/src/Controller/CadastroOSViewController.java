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
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
            
            acessoriosCadastrados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            acessoriosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaAcessoriosCadastrados.setCellValueFactory(cellData -> cellData.getValue().Nom_AcessorioProperty());
            colunaAcessoriosSelecionados.setCellValueFactory(cellData -> cellData.getValue().Nom_AcessorioProperty());
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
    }
    
    public void setRun(Run run) {
        this.run = run;
        
    }
    
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
            rs = pstmt.executeQuery("SELECT max(nro_OS) FROM os");
            if(rs.next()) {
                int nro_OS_Form = rs.getInt(1);
                nro_OS_Form++;
                nroOS.setText(String.valueOf(nro_OS_Form));
            }
            
            data.setText(DateUtil.format(LocalDate.now()));
            
            rs = pstmt.executeQuery("SELECT * FROM acessorio");
            ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
            while(rs.next()) {
                novoAcessorioData.add(new Acessorio(rs.getInt(1), rs.getString(2)));
            }
            acessoriosCadastrados.setItems(novoAcessorioData);
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
    }
    
    @FXML
    private void selecionaAcessorio() {
        if(!acessoriosCadastrados.getSelectionModel().getSelectedItems().isEmpty()) {
            ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
            //Mantem os acessorios já contidos na colunaAcessoriosSelecionados
            int index=1;
            for (int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    Acessorio acessorioContido = acessoriosSelecionados.getItems().get(i);
                    novoAcessorioData.add(new Acessorio((index),
                    colunaAcessoriosSelecionados.getCellObservableValue(acessorioContido).getValue()));
                    index++;
            }
            //Adiciona os novos acessorios selecionados à colunaAcessoriosSelecionados
            for (int i=0; i<acessoriosCadastrados.getSelectionModel().getSelectedItems().size(); i++) {
                Acessorio acessorioSelecionado = acessoriosCadastrados.getSelectionModel().getSelectedItems().get(i);

                if(!(novoAcessorioData.contains(acessorioSelecionado))) {
                    novoAcessorioData.add(new Acessorio((index),
                    colunaAcessoriosCadastrados.getCellObservableValue(acessorioSelecionado).getValue()));
                    index++;
                }
            }
            acessoriosSelecionados.setItems(novoAcessorioData);
            limpaSelecaoTabela(acessoriosCadastrados);
        }
    }
    
    @FXML
    private void removeAcessorio() {
        if(!acessoriosSelecionados.getSelectionModel().getSelectedItems().isEmpty()) {
            ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
            acessoriosSelecionados.getItems().removeAll(
                 acessoriosSelecionados.getSelectionModel().getSelectedItems()
            ); 
            int index=1;
            
            for (int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    Acessorio acessorioContido = acessoriosSelecionados.getItems().get(i);
                    novoAcessorioData.add(new Acessorio((index),
                    colunaAcessoriosSelecionados.getCellObservableValue(acessorioContido).getValue()));
                    index++;
            }
            //run.setAcessorioData(acessorioData);
            acessoriosSelecionados.setItems(novoAcessorioData);
            limpaSelecaoTabela(acessoriosSelecionados);
        }
    }
    
    @FXML
    private void limpaSelecaoTabela(TableView<Acessorio> tabela) {
        tabela.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void criaOS() throws OSDAOException {
        try {
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
            
            os.setCod_Cpf_Cnpj("77777777777");//Substituir quando cliente estiver pronto
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
            //Acessorios
            /*
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT max(cod_acessorio) FROM acessorio");
            */
            int cod_Acessorio=1;
            for (Acessorio acessorio : acessoriosSelecionados.getItems()) {
                acessorios.add(new Acessorio((cod_Acessorio),
                        colunaAcessoriosSelecionados.getCellObservableValue(acessorio).getValue()));
                cod_Acessorio++;
            }
            
            osStatus.setDat_Ocorrencia(System.currentTimeMillis());
            osStatus.setCod_Usuario(123);//Substituir quando login estiver pronto
            /*
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT cod_usuario FROM usuario WHERE" 
            +"cod_usuario="+usuario.getCod_Usuario());
            if(rs.next()) {
            osStatus.setCod_Usuario(rs.getString(1));
            }
            */
            osStatus.setCod_Status(1);
            
            
            daoOS.insert(equipamento, os, acessorios, osStatus);
        } catch (Exception ex) {
            System.out.println("Problema ao criar OS: "+ex);
        }
    }
    
    @FXML
    private void validate() {
        
    }
    
    @FXML
    private void  hello() {
        System.out.println("hy");
    }
    
}
