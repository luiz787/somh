package Controller;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TelaCadastroOSController implements Initializable {

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
    @FXML
    private AnchorPane fundo;
    @FXML
    private HBox hbox;
    @FXML
    private Pane painel;
    @FXML
    private Button cadastrarAcessorio;
    @FXML
    private Button excluirAcessorio;
    @FXML
    private Label faixa;
    @FXML
    private Button menu;
    
    private Run run;
    private Usuario usuarioLogado;
    private Cliente cliente;
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setRun(Run run) {
        this.run = run;
    }

    public TelaCadastroOSController(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usuarioLogado = LoginController.getUsuarioLogado();
            
            carregaForm();
            
            acessoriosCadastrados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            acessoriosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaAcessoriosCadastrados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            colunaAcessoriosSelecionados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            
        } catch (Exception ex) {
            System.out.println("Problema ao carregar formulario: "+ex);
        }
    }
    
    private void carregaForm() throws Exception {
        try {
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            List<OS> listaOS = manterOS.getAll();
            if(listaOS.isEmpty()) {
                nroOS.setText("1");
            } else {
                OS ultimaOS = listaOS.get(listaOS.size()-1);
                nroOS.setText(String.valueOf(ultimaOS.getId()+1));
            }
            if(cliente!=null) {
                nomeCliente.setText(cliente.getNome());
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
            for (int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    Acessorio acessorioContido = acessoriosSelecionados.getItems().get(i);
                    novoAcessorioData.add(acessorioContido);
            }
            //Adiciona os novos acessorios selecionados à colunaAcessoriosSelecionados
            for (int i=0; i<acessoriosCadastrados.getSelectionModel().getSelectedItems().size(); i++) {
                Acessorio acessorioSelecionado = acessoriosCadastrados.getSelectionModel().getSelectedItems().get(i);

                if(!(novoAcessorioData.contains(acessorioSelecionado))) {
                    novoAcessorioData.add(acessorioSelecionado);
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
            
            for (int i=0; i<acessoriosSelecionados.getItems().size(); i++) {
                    Acessorio acessorioContido = acessoriosSelecionados.getItems().get(i);
                    novoAcessorioData.add(acessorioContido);
            }
            acessoriosSelecionados.setItems(novoAcessorioData);
            limpaSelecaoTabela(acessoriosSelecionados);
        }
    }
    
    
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
            
            os.setCliente(cliente);
            
            os.setTxtReclamacao(reclamacao.getText());
            if(!(observacaoAcessorio.getText().isEmpty())) {
                os.setTxtObservacaoAcessorios(observacaoAcessorio.getText());
            }
            
            if(!(acessoriosSelecionados.getItems().isEmpty())) {
                for (Acessorio acessorio : acessoriosSelecionados.getItems()) {
                    acessorios.add(acessorio);
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
                    osAcessorio.setAcessorio(acessorios.get(i));
                    boolean check = manterOSAcessorio.cadastrarOSAcessorio(osAcessorio);
                }
            }
            
            osStatus.setDatOcorrencia(System.currentTimeMillis());
            osStatus.setUsuario(usuarioLogado);
            status.setId(1);
            status.setNome("Em orçamento");
            osStatus.setStatus(status);
            osStatus.setOs(os);
            manterOSStatus.cadastrarOSStatus(osStatus);
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cadastro de OS");
            alert.setHeaderText("Concluído");
            alert.setContentText("A OS foi cadastrada com sucesso");

            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
            AnchorPane TelaListagemOS = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaListagemOS);
            
            TelaListagemOSController controller = loader.getController();
            controller.setRun(run);
            
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
                    
                    ArrayList<Acessorio> acessorioList = (ArrayList<Acessorio>)manterAcessorio.getAll();
                    ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
                    for(Acessorio acessorio : acessorioList) {
                        novoAcessorioData.add(acessorio);
                    }

                    acessoriosCadastrados.setItems(novoAcessorioData);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de OS");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Acessório já cadastrado!");

                    alert.showAndWait();
                }
            } catch (Exception ex) {
                System.out.println("Problema ao cadastrar Acessório: "+ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
                    for (int i=0; i<listaAcessorio.size(); i++) {
                        novoAcessorioData.add(listaAcessorio.get(i));
                    }
                    acessoriosCadastrados.setItems(novoAcessorioData);
                } else if(erro==1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de OS");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Você não pode excluir um acessorio "
                            + "utilizado por uma OS já cadastrada!");

                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de OS");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione os acessórios a serem excluídos!");

            alert.showAndWait();
        }
    }
    
    
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

    @FXML
    private void cadastrarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/CadastroClienteView.fxml"));
            AnchorPane TelaCadastroCliente = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaCadastroCliente);
            
            CadastroClienteViewController controller = loader.getController();
            controller.setCriaOS(true);
            controller.setRun(run);
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pesquisarCliente(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemClienteView.fxml"));
            AnchorPane TelaCadastroCliente = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaCadastroCliente);
            
            TelaListagemClienteController controller = loader.getController();
            controller.setCriaOS(true);
            controller.setRun(run);
            
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}