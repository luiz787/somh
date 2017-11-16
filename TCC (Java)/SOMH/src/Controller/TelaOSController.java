package Controller;

import DAOImpl.OSAcessorioDAOImpl;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import DAOImpl.StatusDAOImpl;
import Domain.Acessorio;
import Domain.OS;
import Domain.OSAcessorio;
import Domain.OSStatus;
import Domain.Status;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOS;
import Service.ManterOSAcessorio;
import Service.ManterOSStatus;
import Service.ManterStatus;
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

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public TelaOSController(OS os) {
        System.out.println("Salve!");
        this.os = os;
        System.out.println("OSzinha: "+os.getEquipamento().getDesEquipto());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            colunaAcessorios.setCellValueFactory(cellData -> cellData.getValue().NomeAcessorioProperty());
            System.out.println("?");
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterStatus manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            ManterOSAcessorio manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            
            ArrayList<OSStatus> osStatus = (ArrayList<OSStatus>) manterOSStatus.getAllByOS(os.getId());
            Status status = osStatus.get(osStatus.size()-1).getStatus();
            long val = osStatus.get(0).getDatOcorrencia();
            Date date = new Date(val);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
            String dataCriacaoString = df2.format(date);
            ObservableList<Acessorio> acessoriosList = FXCollections.observableArrayList();
            ArrayList<OSAcessorio> osAcessorioList = (ArrayList<OSAcessorio>) manterOSAcessorio.getAllByOS(os.getId());
            for(OSAcessorio osAcessorio : osAcessorioList) {
                System.out.println("Acessorio: "+osAcessorio.getAcessorio());
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
    }

    @FXML
    private void excluirOS(ActionEvent event) {
    }

    @FXML
    private void voltarOS(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOS.fxml"));
            AnchorPane TelaListagemOS = (AnchorPane) loader.load();
            
            run.getRootLayout().setCenter(TelaListagemOS);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
