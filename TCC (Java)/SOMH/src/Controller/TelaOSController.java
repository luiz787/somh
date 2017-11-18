package Controller;

import DAOImpl.EquipamentoDAOImpl;
import DAOImpl.OSAcessorioDAOImpl;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import DAOImpl.StatusDAOImpl;
import Domain.Acessorio;
import Domain.Cliente;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSAcessorio;
import Domain.OSStatus;
import Domain.Status;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterEquipamento;
import Service.ManterOS;
import Service.ManterOSAcessorio;
import Service.ManterOSStatus;
import Service.ManterStatus;
import ServiceImpl.ManterEquipamentoImpl;
import ServiceImpl.ManterOSAcessorioImpl;
import ServiceImpl.ManterOSImpl;
import ServiceImpl.ManterOSStatusImpl;
import ServiceImpl.ManterStatusImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Label nomeCliente;
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

    private Run run;
    private OS os;
    @FXML
    private Button salvarBtn;
    @FXML
    private Button cancelarBtn;

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
            colunaAcessorios.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterStatus manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            
            ArrayList<OSStatus> osStatus = (ArrayList<OSStatus>) manterOSStatus.getAllByOS(os.getId());
            Status status = osStatus.get(osStatus.size()-1).getStatus();
            if(!status.getNome().equals("Em orçamento")) {
                excluirBtn.setVisible(false);
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
            nroSerie.setText(os.getEquipamento().getNroSerie().toString());
            textComponentes.setText(os.getEquipamento().getDesComponentes());
            listAcessorios.setItems(acessoriosList);
            textObservacao.setText(os.getTxtObservacaoAcessorios());
            textReclamacao.setText(os.getTxtReclamacao());
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }
    
    @FXML
    private void redirecionaTelaCliente(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaCliente.fxml"));
            AnchorPane TelaCliente = (AnchorPane) loader.load();
            //run.setCliente(cliente);
            run.getRootLayout().setCenter(TelaCliente);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void alterarOS(ActionEvent event) {
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
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
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
            equipamentoAlterado.setNroSerie(Integer.parseInt(nroSerie.getText()));
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
    }
    
}
