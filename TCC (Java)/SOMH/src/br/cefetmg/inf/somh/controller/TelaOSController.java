package br.cefetmg.inf.somh.controller;

import br.cefetmg.inf.somh.dao.impl.AcessorioDAOImpl;
import br.cefetmg.inf.somh.dao.impl.EquipamentoDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSAcessorioDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSItemPecaDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSItemServicoDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSStatusDAOImpl;
import br.cefetmg.inf.somh.dao.impl.StatusDAOImpl;
import br.cefetmg.inf.somh.domain.Acessorio;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.domain.Equipamento;
import br.cefetmg.inf.somh.domain.OS;
import br.cefetmg.inf.somh.domain.OSAcessorio;
import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.domain.OSItemServico;
import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.domain.Status;
import br.cefetmg.inf.somh.domain.Usuario;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.main.Run;
import br.cefetmg.inf.somh.service.ManterAcessorio;
import br.cefetmg.inf.somh.service.ManterEquipamento;
import br.cefetmg.inf.somh.service.ManterOS;
import br.cefetmg.inf.somh.service.ManterOSAcessorio;
import br.cefetmg.inf.somh.service.ManterOSItemPeca;
import br.cefetmg.inf.somh.service.ManterOSItemServico;
import br.cefetmg.inf.somh.service.ManterOSStatus;
import br.cefetmg.inf.somh.service.ManterStatus;
import br.cefetmg.inf.somh.service.impl.ManterAcessorioImpl;
import br.cefetmg.inf.somh.service.impl.ManterEquipamentoImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSAcessorioImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSItemPecaImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSItemServicoImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSStatusImpl;
import br.cefetmg.inf.somh.service.impl.ManterStatusImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

public class TelaOSController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private Label faixa;
    @FXML
    private Pane painel;
    @FXML
    private Label nroOS;
    @FXML
    private Button alterarBtn;
    @FXML
    private Button excluirBtn;
    @FXML
    private Button voltarBtn;
    @FXML
    private TextArea textComponentes;
    @FXML
    private TableView<Acessorio> listAcessorios;
    @FXML
    private TableColumn<Acessorio, String> colunaAcessorios;
    @FXML
    private TextField nomeEquipamento;
    @FXML
    private TextField nomeMarca;
    @FXML
    private TextField nomeModelo;
    @FXML
    private TextField nroSerie;
    @FXML
    private TextArea textReclamacao;
    @FXML
    private TextArea textObservacao;
    @FXML
    private Label statusOS;
    @FXML
    private Label dataCriacao;
    @FXML
    private Button salvarBtn;
    @FXML
    private Button cancelarBtn;
    @FXML
    private Button excluirAcessorio;
    @FXML
    private TableView<Acessorio> acessoriosSelecionados;
    @FXML
    private TableColumn<Acessorio, String> colunaAcessoriosSelecionados;
    @FXML
    private Button removeAcessorio;
    @FXML
    private Button adicionaAcessorio;
    @FXML
    private TableView<Acessorio> acessoriosCadastrados;
    @FXML
    private TableColumn<Acessorio, String> colunaAcessoriosCadastrados;
    @FXML
    private Button cadastrarAcessorio;
    @FXML
    private TextField nomeAcessorioCadastro;
    @FXML
    private Label cadastrarAcessorioLB;
    @FXML
    private Button menu;
    @FXML
    private HBox hbox;
    @FXML
    private Label nomeCliente;
    @FXML
    private Button aprovarBtn;
    @FXML
    private Button recusarBtn;
    @FXML
    private Button avisarBtn;
    @FXML
    private Button entregarBtn;
    @FXML
    private Label precoFinal;
    @FXML
    private Label precoFinalLB;
    
    private Run run;
    private OS os;
    private Usuario usuarioLogado;

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public TelaOSController(OS os) {
        this.os = os;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usuarioLogado = LoginController.getUsuarioLogado();
            
            colunaAcessorios.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterStatus manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            ManterOSItemPeca manterOSItemPeca = new ManterOSItemPecaImpl(OSItemPecaDAOImpl.getInstance());
            ManterOSItemServico manterOSItemServico = new ManterOSItemServicoImpl(OSItemServicoDAOImpl.getInstance());
            
            ArrayList<OSStatus> osStatus = (ArrayList<OSStatus>) manterOSStatus.getAllByOS(os.getId());
            Status status = osStatus.get(osStatus.size()-1).getStatus();
            if(!status.getNome().equals("Em orçamento")) {
                excluirBtn.setVisible(false);
                alterarBtn.setVisible(false);
            }
            if(usuarioLogado.getPerfil().getDescricao().equals("telefonista")) {
                if(status.getNome().equals("Aguardando cliente")) {
                    aprovarBtn.setVisible(true);
                    recusarBtn.setVisible(true);
                } else if(status.getNome().equals("Pronto")) {
                    avisarBtn.setVisible(true);
                }
            }
            if(usuarioLogado.getPerfil().getDescricao().equals("atendente")) {
                if(status.getNome().equals("Avisado")) {
                    entregarBtn.setVisible(true);
                }
            }
            if(status.getId()>=7) {
                precoFinalLB.setVisible(true);
                precoFinal.setVisible(true);
                ArrayList<OSItemPeca> osItemPecaList = (ArrayList<OSItemPeca>) manterOSItemPeca.getAllByOS(os.getId());
                Double valorTotal = 0.0;
                for(OSItemPeca osItemPeca : osItemPecaList) {
                    valorTotal += (osItemPeca.getValorVenda()*osItemPeca.getQtd());
                }
                ArrayList<OSItemServico> osItemServicoList = (ArrayList<OSItemServico>) manterOSItemServico.getAllByOS(os.getId());
                for(OSItemServico osItemServico : osItemServicoList) {
                    valorTotal += (osItemServico.getValorServico()*osItemServico.getQuantidadeServico());
                }
                precoFinal.setText(valorTotal.toString());
            }
            long val = osStatus.get(0).getDatOcorrencia();
            Date date = new Date(val);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
            String dataCriacaoString = df2.format(date);
            ObservableList<Acessorio> acessoriosList = FXCollections.observableArrayList();
            ArrayList<OSAcessorio> osAcessorioList = (ArrayList<OSAcessorio>) manterOSAcessorio.getAllByOS(os.getId());
            for(OSAcessorio osAcessorio : osAcessorioList) {
                acessoriosList.add(osAcessorio.getAcessorio());
            }
            
            nroOS.setText(os.getId().toString());
            nomeCliente.setText(os.getCliente().getNome());
            statusOS.setText(status.getNome());
            dataCriacao.setText(dataCriacaoString);
            nomeEquipamento.setText(os.getEquipamento().getDesEquipto());
            nomeMarca.setText(os.getEquipamento().getDesMarca());
            nomeModelo.setText(os.getEquipamento().getDesModelo());
            if(os.getEquipamento().getNroSerie()!=0) {
                nroSerie.setText(os.getEquipamento().getNroSerie().toString());    
            }
            textComponentes.setText(os.getEquipamento().getDesComponentes());
            listAcessorios.setItems(acessoriosList);
            textObservacao.setText(os.getTxtObservacaoAcessorios());
            textReclamacao.setText(os.getTxtReclamacao());
            
            acessoriosCadastrados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            acessoriosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            colunaAcessoriosCadastrados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            colunaAcessoriosSelecionados.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaOSController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcecaoNegocio ex) {
            Logger.getLogger(TelaOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }

    @FXML
    private void alterarOS(ActionEvent event) throws ExcecaoPersistencia {
        nomeEquipamento.setEditable(true);
        nomeMarca.setEditable(true);
        nomeModelo.setEditable(true);
        nroSerie.setEditable(true);
        textComponentes.setEditable(true);
        textObservacao.setEditable(true);
        textReclamacao.setEditable(true);
        alterarBtn.setVisible(false);
        excluirBtn.setVisible(false);
        voltarBtn.setVisible(false);
        salvarBtn.setVisible(true);
        cancelarBtn.setVisible(true);
        
        excluirAcessorio.setVisible(true);
        acessoriosSelecionados.setVisible(true);
        removeAcessorio.setVisible(true);
        adicionaAcessorio.setVisible(true);
        acessoriosCadastrados.setVisible(true);
        cadastrarAcessorio.setVisible(true);
        nomeAcessorioCadastro.setVisible(true);
        cadastrarAcessorioLB.setVisible(true);
        listAcessorios.setVisible(false);
        
        ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
            
        List<Acessorio> listaAcessorio = manterAcessorio.getAll();

        ObservableList<Acessorio> novoAcessorioData = FXCollections.observableArrayList();
        for(int i=0; i<listaAcessorio.size(); i++) {
            novoAcessorioData.add(listaAcessorio.get(i));
        }

        acessoriosCadastrados.setItems(novoAcessorioData);
    }

    @FXML
    private void excluirOS(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exclusão de OS");
        alert.setHeaderText("Aviso");
        alert.setContentText("Tem certeza que deseja excluir essa OS?");

        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")) {
            try {
                ManterEquipamento manterEquipamento = new ManterEquipamentoImpl(EquipamentoDAOImpl.getInstance());
                ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
                ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
                ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
                
                manterOSStatus.deletarAllOSStatus(os.getId());
                manterOSAcessorio.deletarAllOSAcessorio(os.getId());
                manterOS.deletarOS(os.getId());
                manterEquipamento.deletarEquipamento(os.getEquipamento().getId());
                
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exclusão de OS");
                alert.setHeaderText("Concluído");
                alert.setContentText("A OS foi excluída com sucesso");

                alert.showAndWait();
                voltarOS();
            } catch (ExcecaoPersistencia ex) {
                Logger.getLogger(TelaOSController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void voltarOS() {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../view/TelaListagemOSView.fxml"));
            AnchorPane TelaListagemOSView = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaListagemOSView);
            
            TelaListagemOSController controller = loader.getController();
            controller.setRun(run);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salvarOS(ActionEvent event) {
        try {
            ManterEquipamento manterEquipamento = new ManterEquipamentoImpl(EquipamentoDAOImpl.getInstance());
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            
            OS osAlterada = os;
            Equipamento equipamentoAlterado = os.getEquipamento();
            
            equipamentoAlterado.setDesEquipto(nomeEquipamento.getText());
            equipamentoAlterado.setDesMarca(nomeMarca.getText());
            equipamentoAlterado.setDesModelo(nomeModelo.getText());
            if(os.getEquipamento().getNroSerie()!=0) {
                equipamentoAlterado.setNroSerie(Integer.parseInt(nroSerie.getText()));
            }
            equipamentoAlterado.setDesComponentes(textComponentes.getText());
            
            osAlterada.setEquipamento(equipamentoAlterado);
            osAlterada.setTxtObservacaoAcessorios(textObservacao.getText());
            osAlterada.setTxtReclamacao(textReclamacao.getText());
            osAlterada.setId(Long.parseLong(nroOS.getText()));
            Cliente cliente = new Cliente();
            cliente.setCodCPF_CNPJ(10213683695L);
            osAlterada.setCliente(cliente);
            
            manterOS.alterarOS(osAlterada);
            manterEquipamento.alterarEquipamento(equipamentoAlterado);
            
            ArrayList<Acessorio> acessorios = new ArrayList<Acessorio>();
            OSAcessorio osAcessorio = new OSAcessorio();
            ManterAcessorio manterAcessorio = new ManterAcessorioImpl(AcessorioDAOImpl.getInstance());
            ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            if(!(acessoriosSelecionados.getItems().isEmpty())) {
                for (Acessorio acessorio : acessoriosSelecionados.getItems()) {
                    acessorios.add(acessorio);
                }
            }
            
            List<Acessorio> cadastrados = new ArrayList<Acessorio>();
            cadastrados = manterAcessorio.getAll();
            ArrayList<String> cadastradosString = new ArrayList<String>();
            for(int i=0; i<cadastrados.size(); i++) {
                cadastradosString.add(cadastrados.get(i).getNomeAcessorio());
            }
            manterOSAcessorio.deletarAllOSAcessorio(os.getId());
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
            
            ObservableList<Acessorio> acessoriosList = FXCollections.observableArrayList();
            ArrayList<OSAcessorio> osAcessorioList = (ArrayList<OSAcessorio>) manterOSAcessorio.getAllByOS(os.getId());
            for(OSAcessorio osAcessorioFor : osAcessorioList) {
                acessoriosList.add(osAcessorioFor.getAcessorio());
            }
            
            listAcessorios.setItems(acessoriosList);
            
            nomeEquipamento.setEditable(false);
            nomeMarca.setEditable(false);
            nomeModelo.setEditable(false);
            nroSerie.setEditable(false);
            textComponentes.setEditable(false);
            textObservacao.setEditable(false);
            textReclamacao.setEditable(false);
            alterarBtn.setVisible(true);
            excluirBtn.setVisible(true);
            voltarBtn.setVisible(true);
            salvarBtn.setVisible(false);
            cancelarBtn.setVisible(false);
            
            excluirAcessorio.setVisible(false);
            acessoriosSelecionados.setVisible(false);
            removeAcessorio.setVisible(false);
            adicionaAcessorio.setVisible(false);
            acessoriosCadastrados.setVisible(false);
            cadastrarAcessorio.setVisible(false);
            nomeAcessorioCadastro.setVisible(false);
            cadastrarAcessorioLB.setVisible(false);
            listAcessorios.setVisible(true);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alteração de OS");
            alert.setHeaderText("Concluído");
            alert.setContentText("A OS foi alterada com sucesso");

            alert.showAndWait();
        } catch (ExcecaoPersistencia|ExcecaoNegocio ex) {
            Logger.getLogger(TelaOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelarOS(ActionEvent event) {
        nomeEquipamento.setEditable(false);
        nomeMarca.setEditable(false);
        nomeModelo.setEditable(false);
        nroSerie.setEditable(false);
        textComponentes.setEditable(false);
        textObservacao.setEditable(false);
        textReclamacao.setEditable(false);
        alterarBtn.setVisible(true);
        excluirBtn.setVisible(true);
        voltarBtn.setVisible(true);
        salvarBtn.setVisible(false);
        cancelarBtn.setVisible(false);

        excluirAcessorio.setVisible(false);
        acessoriosSelecionados.setVisible(false);
        removeAcessorio.setVisible(false);
        adicionaAcessorio.setVisible(false);
        acessoriosCadastrados.setVisible(false);
        cadastrarAcessorio.setVisible(false);
        nomeAcessorioCadastro.setVisible(false);
        cadastrarAcessorioLB.setVisible(false);
        listAcessorios.setVisible(true);
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
    
    private void limpaSelecaoTabela(TableView<Acessorio> tabela) {
        tabela.getSelectionModel().clearSelection();
    }

    @FXML
    private void cadastrarAcessorio(ActionEvent event) {
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
    private void redirecionaTelaFuncionario(ActionEvent event) {
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

    @FXML
    private void aprovarOS(ActionEvent event) throws ExcecaoPersistencia, ExcecaoNegocio {
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        OSStatus osStatus = new OSStatus();
        osStatus.setDatOcorrencia(System.currentTimeMillis());
        osStatus.setUsuario(usuarioLogado);
        Status status = new Status();
        status.setId(5);
        status.setNome("Aprovado");
        osStatus.setStatus(status);
        osStatus.setOs(os);
        manterOSStatus.cadastrarOSStatus(osStatus);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aprovar OS");
        alert.setHeaderText("Concluído");
        alert.setContentText("A OS foi aprovada com sucesso!");

        alert.showAndWait();
        voltarOS();
    }

    @FXML
    private void recusarOS(ActionEvent event) throws ExcecaoPersistencia, ExcecaoNegocio {
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        OSStatus osStatus = new OSStatus();
        osStatus.setDatOcorrencia(System.currentTimeMillis());
        osStatus.setUsuario(usuarioLogado);
        Status status = new Status();
        status.setId(4);
        status.setNome("Recusado");
        osStatus.setStatus(status);
        osStatus.setOs(os);
        manterOSStatus.cadastrarOSStatus(osStatus);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Recusar OS");
        alert.setHeaderText("Concluído");
        alert.setContentText("A OS foi recusada com sucesso!");

        alert.showAndWait();
        voltarOS();
    }

    @FXML
    private void avisarOS(ActionEvent event) throws ExcecaoPersistencia, ExcecaoNegocio {
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        OSStatus osStatus = new OSStatus();
        osStatus.setDatOcorrencia(System.currentTimeMillis());
        osStatus.setUsuario(usuarioLogado);
        Status status = new Status();
        status.setId(8);
        status.setNome("Avisado");
        osStatus.setStatus(status);
        osStatus.setOs(os);
        manterOSStatus.cadastrarOSStatus(osStatus);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avisar OS");
        alert.setHeaderText("Concluído");
        alert.setContentText("A OS foi avisada com sucesso!");

        alert.showAndWait();
        voltarOS();
    }

    @FXML
    private void entregarOS(ActionEvent event) throws ExcecaoPersistencia, ExcecaoNegocio {
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        OSStatus osStatus = new OSStatus();
        osStatus.setDatOcorrencia(System.currentTimeMillis());
        osStatus.setUsuario(usuarioLogado);
        Status status = new Status();
        status.setId(9);
        status.setNome("Entregue");
        osStatus.setStatus(status);
        osStatus.setOs(os);
        manterOSStatus.cadastrarOSStatus(osStatus);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Entregar OS");
        alert.setHeaderText("Concluído");
        alert.setContentText("A OS foi entregue com sucesso!");

        alert.showAndWait();
        voltarOS();
    }
}