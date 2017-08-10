package Controller;

import BD.JDBCManterConexao;
import DAOImpl.AcessorioDAOImpl;
import DAOImpl.EquipamentoDAOImpl;
import DAOImpl.OSAcessorioDAOImpl;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import Main.Run;
import Domain.Acessorio;
import Data.DateUtil;
import Domain.Cliente;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSAcessorio;
import Domain.OSStatus;
import Domain.Perfil;
import Domain.Status;
import Domain.Usuario;
import Service.ManterAcessorio;
import Service.ManterEquipamento;
import Service.ManterOS;
import Service.ManterOSAcessorio;
import Service.ManterOSStatus;
import ServiceImpl.ManterAcessorioImpl;
import ServiceImpl.ManterEquipamentoImpl;
import ServiceImpl.ManterOSAcessorioImpl;
import ServiceImpl.ManterOSImpl;
import ServiceImpl.ManterOSStatusImpl;
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
        try {
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            List<OS> listaOS = manterOS.getAll();
            if(listaOS.size()==0) {
                nroOS.setText("1");
            } else {
                OS ultimaOS = listaOS.get(listaOS.size()-1);
                nroOS.setText(String.valueOf(ultimaOS.getId()+1));
            }
            
            data.setText(DateUtil.format(LocalDate.now()));
            
            ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
            
            List<Acessorio> listaAcessorio = manterAcessorio.getAll();
            
            ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
            for(int i=0; i<listaAcessorio.size(); i++) {
                novoAcessorioData.add(listaAcessorio.get(i));
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
            long index=1;
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
            long index=1;
            
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
    private void criaOS() {
        try {
            ManterEquipamento manterEquipamento = new ManterEquipamentoImpl(EquipamentoDAOImpl.getInstance());
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
            ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            
            Equipamento equipamento = new Equipamento();
            OS os = new OS();
            Cliente cliente = new Cliente();
            ArrayList<Acessorio> acessorios = new ArrayList<Acessorio>();
            OSAcessorio osAcessorio = new OSAcessorio();
            OSStatus osStatus = new OSStatus();
            Usuario usuario = new Usuario();
            Status status = new Status();
            
            //Valida os dados no formulário
            if(!(validacao())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Cadastro de OS");
                alert.setHeaderText("Erro");
                alert.setContentText("Preencha os campos corretamente!");

                alert.showAndWait();
                throw new Exception("Preencha os campos corretamente!");
            }
            
            equipamento.setDesMarca(marca.getText());
            equipamento.setDesEquipto(nomeEquipamento.getText());
            if(!(modelo.getText().isEmpty())) {
                equipamento.setDesModelo(modelo.getText());
            }
            if(!(componentes.getText().isEmpty())) {
                equipamento.setDesComponentes(componentes.getText());
            }
            if(!(nroSerie.getText().isEmpty())) {
                equipamento.setNroSerie(Integer.parseInt(nroSerie.getText()));
            }
            
            cliente.setCodCPF_CNPJ(Long.parseLong("15771757517"));//Substituir quando cliente estiver pronto
            os.setCliente(cliente);
            
            os.setTxtReclamacao(reclamacao.getText());
            if(!(observacaoAcessorio.getText().isEmpty())) {
                os.setTxtObservacaoAcessorios(observacaoAcessorio.getText());
            }
            
            if(!(acessoriosSelecionados.getItems().isEmpty())) {
                long codAcessorio=1;
                for (Acessorio acessorio : acessoriosSelecionados.getItems()) {
                    acessorios.add(new Acessorio((codAcessorio),
                            colunaAcessoriosSelecionados.getCellObservableValue(acessorio).getValue()));
                    codAcessorio++;
                }
            }
            
            
            Long seqEquipamento = manterEquipamento.cadastrarEquipamento(equipamento);
            equipamento.setId(seqEquipamento);
            
            os.setEquipamento(equipamento);
            manterOS.cadastrarOS(os);
            
            List<Acessorio> cadastrados = new ArrayList<Acessorio>();
            cadastrados = manterAcessorio.getAll();
            ArrayList<String> cadastradosString = new ArrayList<String>();
            for(int i=0; i<cadastrados.size(); i++) {
                cadastradosString.add(cadastrados.get(i).getNomeAcessorio());
            }
            
            for(int i=0; i<acessorios.size(); i++) {
                if(!(cadastradosString.contains(acessorios.get(i).getNomeAcessorio()))) {
                    Long idAcessorio = manterAcessorio.cadastrarAcessorio(acessorios.get(i));
                    
                    osAcessorio.setOs(os);
                    osAcessorio.setAcessorio(acessorios.get(i));
                    
                    boolean check = manterOSAcessorio.cadastrarOSAcessorio(osAcessorio);
                } else {
                    osAcessorio.setOs(os);
                    osAcessorio.setAcessorio(cadastrados.get(i));
                    boolean check = manterOSAcessorio.cadastrarOSAcessorio(osAcessorio);
                }
            }
            
            osStatus.setDatOcorrencia(System.currentTimeMillis());
            usuario.setId((long)(1));//Substituir quando login estiver pronto
            usuario.setNome("Gabriel Victor");
            Perfil perfil = new Perfil();
            perfil.setId((long)1);
            perfil.setDescricao("Balcão");
            usuario.setPerfil(perfil);
            usuario.setSenha("supersenha123");
            osStatus.setUsuario(usuario);
            status.setId(1);//Substituir quando login estiver pronto
            status.setNome("Em orçamento");
            osStatus.setStatus(status);
            osStatus.setOs(os);
            manterOSStatus.cadastrarOSStatus(osStatus);
            
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
                ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
                
                List<Acessorio> cadastrados = new ArrayList<Acessorio>();
                cadastrados = manterAcessorio.getAll();
                ArrayList<String> cadastradosString = new ArrayList<String>();
                for(int i=0; i<cadastrados.size(); i++) {
                    cadastradosString.add(cadastrados.get(i).getNomeAcessorio());
                }
                
                if(!(cadastradosString.contains(nomeAcessorioCadastro.getText()))) {
                    long fakeId = 1;
                    Acessorio novoAcessorio = new Acessorio(fakeId, nomeAcessorioCadastro.getText());
                    Long idAcessorio = manterAcessorio.cadastrarAcessorio(novoAcessorio);

                    ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
                    //Mantem os acessorios já contidos na colunaAcessoriosCadastrados
                    long index=1;
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
            } catch (Exception ex) {
                System.out.println("Problema ao cadastrar Acessório: "+ex);
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
                    acessoriosSelecionadosString.add(acessoriosCadastrados.getSelectionModel().getSelectedItems().get(i).getNomeAcessorio());
                }
                
                ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
                List<OSAcessorio> listaOSAcessorio = manterOSAcessorio.getAll();
                
                int erro=0;
                int contador=0;
                while(contador!=listaOSAcessorio.size() && erro==0) {
                    OSAcessorio osAcessorio = listaOSAcessorio.get(contador);
                    if(acessoriosSelecionadosString.contains(osAcessorio.getAcessorio().getNomeAcessorio())) {
                        erro=1;
                    }
                    contador++;
                }
                
                for(int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    if(acessoriosSelecionadosString.contains(
                        acessoriosSelecionados.getItems().get(i).getNomeAcessorio())) {
                        erro=2;
                    }
                }
                
                if(erro==0) {
                    ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
                    List<Acessorio> listaAcessorio = manterAcessorio.getAll();
                    for (int i=0; i<listaAcessorio.size(); i++) {
                        if(acessoriosSelecionadosString.contains(listaAcessorio.get(i).getNomeAcessorio())) {
                            manterAcessorio.deletarAcessorio(listaAcessorio.get(i).getId());
                        }
                    }
                    listaAcessorio = manterAcessorio.getAll();
                    long index=1;
                    for (int i=0; i<listaAcessorio.size(); i++) {
                        novoAcessorioData.add(new Acessorio((index), listaAcessorio.get(i).getNomeAcessorio()));
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
            } catch (Exception ex) {
                System.out.println("Problema ao excluir Acessório: "+ex);
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
