package Controller;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import DAOImpl.StatusDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSStatus;
import Domain.Perfil;
import Domain.Status;
import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOS;
import Service.ManterOSStatus;
import Service.ManterStatus;
import Service.ManterUsuario;
import ServiceImpl.ManterOSImpl;
import ServiceImpl.ManterOSStatusImpl;
import ServiceImpl.ManterStatusImpl;
import ServiceImpl.ManterUsuarioImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class TelaListagemOSController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane fundo;
    @FXML
    private Label faixa;
    @FXML
    private TableView<String[]> listaOS;
    @FXML
    private TableColumn<String[],String> colunaEquipamento;
    @FXML
    private TableColumn<String[],String> colunaCodigo;
    @FXML
    private TableColumn<String[],String> colunaDataReceb;
    @FXML
    private TableColumn<String[],String> colunaStatus;
    @FXML
    private TableColumn<String[],String> colunaDataFecha;
    @FXML
    private TableColumn<String[],String> colunaCliente;
    @FXML
    private Button pesquisa;
    @FXML
    private ChoiceBox<String> filtroOS;
    
    private Usuario usuarioLogado;

    private Run run;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(2L);
            usuario.setNome("Victor");
            Perfil perfil = new Perfil();
            perfil.setId(4L);
            perfil.setDescricao("técnico");
            usuario.setPerfil(perfil);
            usuario.setSenha("123");
            
            LoginController.setUsuarioLogado(usuario);
            usuarioLogado = LoginController.getUsuarioLogado();
            
            
            if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                colunaCliente.setVisible(false);
            }
            
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterStatus manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            ArrayList<OS> listOS = (ArrayList<OS>) manterOS.getAll();
            
            String[][] data = new String[listOS.size()][6];
            
            for(int i=0; i<listOS.size(); i++) {
                OS os = listOS.get(i);
                ArrayList<OSStatus> listOSStatus = 
                (ArrayList<OSStatus>) manterOSStatus.getAllByOS(os.getId());
                
                Equipamento equipamento = os.getEquipamento();
                
                long val = listOSStatus.get(0).getDatOcorrencia();
                Date date=new Date(val);
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(date);
                
                data[i][0] = os.getId().toString();
                data[i][1] = equipamento.getDesEquipto();
                data[i][2] = os.getCliente().getNome();
                data[i][3] = dateText;
                data[i][4] = listOSStatus.get(0).getStatus().getNome();
                if(listOSStatus.get(listOSStatus.size()-1).equals("Entregue")) {
                    data[i][5] = listOSStatus.get(listOSStatus.size()-1).getDatOcorrencia().toString();
                } else {
                    data[i][5] = "---";
                }
                
            }
            
            colunaCodigo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>0) {
                        return new SimpleStringProperty(x[0]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            colunaEquipamento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>1) {
                        return new SimpleStringProperty(x[1]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            colunaCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>2) {
                        return new SimpleStringProperty(x[2]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            colunaDataReceb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>3) {
                        return new SimpleStringProperty(x[3]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            colunaStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>4) {
                        return new SimpleStringProperty(x[4]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            colunaDataFecha.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>5) {
                        return new SimpleStringProperty(x[5]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                }
            });
            
            listaOS.getItems().addAll(Arrays.asList(data));
            
            listaOS.setRowFactory( tv -> {
                TableRow<String[]> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty())) {
                        try{
                            String[] rowItem = row.getItem();
                            OS os = manterOS.getOSById(Long.parseLong(rowItem[0]));
                            if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                                TelaManutencaoController controlador = new TelaManutencaoController(os);
                                controlador.setRun(run);
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(Run.class.getResource("../View/TelaManutencao.fxml"));
                                loader.setController(controlador);
                                AnchorPane TelaManutencao = (AnchorPane) loader.load();
                                run.getRootLayout().setCenter(TelaManutencao);
                            } else {
                                TelaOSController controlador = new TelaOSController(os);
                                controlador.setRun(run);
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(Run.class.getResource("../View/TelaOSView.fxml"));
                                loader.setController(controlador);
                                AnchorPane TelaOS = (AnchorPane) loader.load();
                                run.getRootLayout().setCenter(TelaOS);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExcecaoPersistencia ex) {
                            Logger.getLogger(TelaListagemOSController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
            
            ArrayList<Status> statusList = (ArrayList<Status>) manterStatus.getAll();
            ObservableList<String> observableStatusList = FXCollections.observableArrayList();
            observableStatusList.add("Todos");
            for(Status status : statusList) {
                observableStatusList.add(status.getNome());
            }
            filtroOS.setItems(observableStatusList);
            filtroOS.getSelectionModel().select(0);
            
            filtroOS.getSelectionModel().selectedIndexProperty().addListener(new
                    ChangeListener<Number>() {
                        public void changed(ObservableValue ov,
                                Number value, Number new_value) {
                                    String status = filtroOS.getItems().get(Integer.parseInt(new_value.toString()));
                                    if(status!="Todos") {
                                        ArrayList newDataList = new ArrayList();
                                        for(int i=0; i<data.length; i++) {
                                            if(data[i][3].equals(status)) {
                                                newDataList.add(i);
                                            }
                                        }
                                        String[][] newData = new String[newDataList.size()][5];
                                        for(int i=0; i<newData.length; i++) {
                                            newData[i][0] = data[i][0];
                                            newData[i][1] = data[i][1];
                                            newData[i][2] = data[i][2];
                                            newData[i][3] = data[i][3];
                                            newData[i][4] = data[i][4];
                                        }
                                        listaOS.getItems().clear();
                                        listaOS.getItems().addAll(Arrays.asList(newData));
                                    } else {
                                        listaOS.getItems().clear();
                                        listaOS.getItems().addAll(Arrays.asList(data));
                                    }
                        }
                    }
            );
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaListagemOSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setRun(Run run) {
        this.run = run;
    }
    
}
