/*
package Controller;

import BD.JDBCManterConexao;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSDAO_oldVersion;
import Main.Run;
import Domain.Acessorio;
import Data.DateUtil;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSStatus;
import Service.ManterOS;
import ServiceImpl.ManterOSImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CadastroOSViewController1 implements Initializable {

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
    private Connection conexao;
    private String sql="";
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregaForm();
            
            acessoriosCadastrados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            acessoriosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaAcessoriosCadastrados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            colunaAcessoriosSelecionados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            
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
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            List<OS> listaOS = manterOS.getAll();
            OS ultimaOS = listaOS.get(listaOS.size()-1);
            
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
            acessoriosSelecionados.setItems(novoAcessorioData);
            limpaSelecaoTabela(acessoriosSelecionados);
        }
    }
    
    @FXML
    private void limpaSelecaoTabela(TableView<Acessorio> tabela) {
        tabela.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void criaOS() throws OSDAOException_oldVersion {
        try {
            Connection conexao;
            String sql;
            PreparedStatement pstmt;
            ResultSet rs;
            
            OSDAO_oldVersion daoOS = new OSDAO_oldVersion();
            Equipamento equipamento = new Equipamento();
            OS os = new OS();
            ArrayList<Acessorio> acessorios = new ArrayList<Acessorio>();
            OSStatus osStatus = new OSStatus();
            
            //Valida os dados no formulário
            if(!(validacao())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Cadastro de OS");
                alert.setHeaderText("Erro");
                alert.setContentText("Preencha os campos corretamente!");

                alert.showAndWait();
                throw new Exception();
            }
            
            equipamento.setDes_Marca(marca.getText());
            equipamento.setDes_Equipto(nomeEquipamento.getText());
            if(!(modelo.getText().isEmpty())) {
                equipamento.setDes_Modelo(modelo.getText());
            }
            if(!(componentes.getText().isEmpty())) {
                equipamento.setDes_Componentes(componentes.getText());
            }
            if(!(nroSerie.getText().isEmpty())) {
                equipamento.setNro_Serie(Long.parseLong(nroSerie.getText()));
            }
            
            os.setCod_Cpf_Cnpj("15771757517");//Substituir quando cliente estiver pronto
            /*
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT cod_cpf_cnpj FROM cliente where
            nom_cliente="+nomeCliente.getText());
            if(rs.next()) {
            os.setCod_Cpf_Cnpj(rs.getString(1));
            }
            
            os.setTxt_Reclamacao(reclamacao.getText());
            if(!(observacaoAcessorio.getText().isEmpty())) {
                os.setTxt_Observacao_Acessorios(observacaoAcessorio.getText());
            }
            
            if(!(acessoriosSelecionados.getItems().isEmpty())) {
                int cod_Acessorio=1;
                for (Acessorio acessorio : acessoriosSelecionados.getItems()) {
                    acessorios.add(new Acessorio((cod_Acessorio),
                            colunaAcessoriosSelecionados.getCellObservableValue(acessorio).getValue()));
                    cod_Acessorio++;
                }
            }
            
            osStatus.setDat_Ocorrencia(System.currentTimeMillis());
            osStatus.setCod_Usuario(1234567);//Substituir quando login estiver pronto
            /*
            conexao = JDBCManterConexao.getInstancia().getConexao();
            sql="";
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery("SELECT cod_usuario FROM usuario WHERE" 
            +"cod_usuario="+usuario.getCod_Usuario());
            if(rs.next()) {
            osStatus.setCod_Usuario(rs.getString(1));
            }
            
            osStatus.setCod_Status(1);
            
            
            daoOS.insert(equipamento, os, acessorios, osStatus);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cadastro de OS");
            alert.setHeaderText("Concluído");
            alert.setContentText("A OS foi cadastrada com sucesso");

            alert.showAndWait();
            
        } catch (Exception ex) {
            System.out.println("Problema ao criar OS: "+ex);
        }
    }
    
    @FXML
    private void cadastrarAcessorio() throws Exception {
        if(!(nomeAcessorioCadastro.getText().isEmpty())) {
            try {
                conexao = JDBCManterConexao.getInstancia().getConexao();
                pstmt = conexao.prepareStatement(sql);
                rs = pstmt.executeQuery("SELECT nom_acessorio FROM acessorio");

                ArrayList<String> cadastradosString = new ArrayList<String>();
                while(rs.next()) {
                    cadastradosString.add(rs.getString(1));
                }
                if(!(cadastradosString.contains(nomeAcessorioCadastro.getText()))) {
                    sql = "INSERT INTO acessorio (nom_acessorio) VALUES (?)";
                    pstmt = conexao.prepareStatement(sql);

                    pstmt.setString(1, nomeAcessorioCadastro.getText());

                    pstmt.executeUpdate();

                    ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
                    //Mantem os acessorios já contidos na colunaAcessoriosCadastrados
                    int index=1;
                    for (int i=0; i<acessoriosCadastrados.getItems().size(); i++) {
                            Acessorio acessorioContido = acessoriosCadastrados.getItems().get(i);
                            novoAcessorioData.add(new Acessorio((index),
                            colunaAcessoriosCadastrados.getCellObservableValue(acessorioContido).getValue()));
                            index++;
                    }
                    //Adiciona novo acessorio cadastrado à colunaAcessoriosCadastrados
                    novoAcessorioData.add(new Acessorio((index), nomeAcessorioCadastro.getText()));

                    acessoriosCadastrados.setItems(novoAcessorioData);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Cadastro de OS");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Acessório já cadastrado!");

                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroOSViewController1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cadastro de OS");
            alert.setHeaderText("Erro");
            alert.setContentText("Informe o nome do acessório a ser cadastrado!");

            alert.showAndWait();
        }    
        
    }
    @FXML
    private void excluirAcessorio() throws Exception {
        if(!acessoriosCadastrados.getSelectionModel().getSelectedItems().isEmpty()) {
            try {
                ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
                ArrayList<String> acessoriosSelecionadosString = new ArrayList<>();
                for (int i=0; i<acessoriosCadastrados.getSelectionModel().getSelectedItems().size(); i++) {
                    acessoriosSelecionadosString.add(acessoriosCadastrados.getSelectionModel().getSelectedItems().get(i).getNom_Acessorio());
                }
                conexao = JDBCManterConexao.getInstancia().getConexao();
                
                sql="SELECT A.nom_acessorio FROM acessorio A "
                        + "LEFT JOIN osacessorio B ON A.cod_acessorio=B.cod_acessorio "
                        + "WHERE B.cod_acessorio IS NOT NULL";
                pstmt = conexao.prepareStatement(sql);
                rs = pstmt.executeQuery();
                int erro=0;
                while(rs.next() && erro==0) {
                    if(acessoriosSelecionadosString.contains(rs.getString(1))) {
                        erro=1;
                    }
                }
                for(int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    if(acessoriosSelecionadosString.contains(
                        acessoriosSelecionados.getItems().get(i).getNom_Acessorio())) {
                        erro=2;
                    }
                }
                
                if(erro==0) {
                    for (int i=0; i<acessoriosSelecionadosString.size(); i++) {
                        sql="DELETE FROM acessorio WHERE nom_acessorio = ?";
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.setString(1, acessoriosSelecionadosString.get(i));
                        pstmt.executeUpdate();
                    }
                    rs = pstmt.executeQuery("SELECT nom_acessorio FROM acessorio");
                    int index=1;
                    while(rs.next()) {
                            novoAcessorioData.add(new Acessorio((index),rs.getString(1)));
                    }
                    acessoriosCadastrados.setItems(novoAcessorioData);
                } else if(erro==1) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Cadastro de OS");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Você não pode excluir um acessorio "
                            + "utilizado por uma OS já cadastrada!");

                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Cadastro de OS");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Você não pode excluir um acessorio "
                            + "da tabela selecionados!");

                    alert.showAndWait();
                }
                limpaSelecaoTabela(acessoriosCadastrados);    
            } catch (SQLException ex) {
                Logger.getLogger(CadastroOSViewController1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cadastro de OS");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione os acessórios a serem excluídos!");

            alert.showAndWait();
        }
    }
    
    @FXML
    private boolean validacao() {
        boolean validacao=true;
        if(marca.getText().isEmpty()) {
            validacao=false;
        }
        if(nomeEquipamento.getText().isEmpty()) {
            validacao=false;
        }
        if(nomeCliente.getText().isEmpty()) {
            validacao=false;
        }
        if(reclamacao.getText().isEmpty()) {
            validacao=false;
        }
        return validacao;
    }
    
    @FXML
    private void redirecionaTelaFuncionario() {
        System.out.println("tela de funcionario");
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/teste.fxml"));
            AnchorPane TelaFuncionario = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaFuncionario);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
*/