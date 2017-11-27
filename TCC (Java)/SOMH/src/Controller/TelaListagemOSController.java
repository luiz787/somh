package Controller;
import DAOImpl.OSDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import DAOImpl.StatusDAOImpl;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSStatus;
import Domain.Status;
import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOS;
import Service.ManterOSStatus;
import Service.ManterStatus;
import ServiceImpl.ManterOSImpl;
import ServiceImpl.ManterOSStatusImpl;
import ServiceImpl.ManterStatusImpl;
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
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
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
    @FXML
    private TextField textoPesquisa;
    @FXML
    private Button menu;
    
    private Usuario usuarioLogado;
    private Run run;
    private String[][] data;
    
    public void setRun(Run run) {
        this.run = run;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usuarioLogado = LoginController.getUsuarioLogado();
            
            
            if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                colunaCliente.setVisible(false);
            }
            
            ManterOS manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            ManterStatus manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            ArrayList<OS> listOS = (ArrayList<OS>) manterOS.getAll();
            
            
            ArrayList<OS> osList = new ArrayList<OS>();
            
            for(int i=0; i<listOS.size(); i++) {
                OS os = listOS.get(i);
                ArrayList<OSStatus> listOSStatus = 
                (ArrayList<OSStatus>) manterOSStatus.getAllByOS(os.getId());
                if(usuarioLogado.getPerfil().getDescricao().equals("adm")) {
                    osList.add(os);
                } else if(usuarioLogado.getPerfil().getDescricao().equals("atendente")) {
                    String osStatus = listOSStatus.get(listOSStatus.size()-1).getStatus().getNome();
                    if(osStatus.equals("Em orçamento") || osStatus.equals("Orçado")
                       || osStatus.equals("Avisado") || osStatus.equals("Entregue")
                       || osStatus.equals("Garantia") || osStatus.equals("Recusado")) {
                        osList.add(os);
                    }
                } else if(usuarioLogado.getPerfil().getDescricao().equals("telefonista")) {
                    String osStatus = listOSStatus.get(listOSStatus.size()-1).getStatus().getNome();
                    if(osStatus.equals("Aguardando cliente") || osStatus.equals("Pronto")) {
                        osList.add(os);
                    }
                } else if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                    String osStatus = listOSStatus.get(listOSStatus.size()-1).getStatus().getNome();
                    if(osStatus.equals("Em orçamento") || osStatus.equals("Orçado")
                       || osStatus.equals("Aprovado") || osStatus.equals("Aguardando peça")) {
                        osList.add(os);
                    }
                    
                }
            }
            
            int i = 0;
            data = new String[osList.size()][6];
            for(OS os : osList) {
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
                data[i][4] = listOSStatus.get(listOSStatus.size()-1).getStatus().getNome();
                if(listOSStatus.get(listOSStatus.size()-1).getStatus().getNome().equals("Entregue")) {
                    long val2 = listOSStatus.get(listOSStatus.size()-1).getDatOcorrencia();
                    Date date2 = new Date(val);
                    SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yy");
                    String dateText2 = df3.format(date2);
                    data[i][5] = dateText2;
                } else {
                    data[i][5] = "---";
                }
                i++;
            }
            
            
            colunaCodigo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    String[] x = p.getValue();
                    if (x != null && x.length>0) {
                        return new SimpleStringProperty(x[0]);
                    } else {
                        return new SimpleStringProperty("---");
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
                        return new SimpleStringProperty("---");
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
                        return new SimpleStringProperty("---");
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
                        return new SimpleStringProperty("---");
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
                        return new SimpleStringProperty("---");
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
                        return new SimpleStringProperty("---");
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
                            
                            String osStatus = manterOSStatus.getAllByOS(
                            os.getId()).get(manterOSStatus.getAllByOS(os.getId()).size()-1).getStatus().getNome();
                            
                            if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                                if(osStatus.equals("Em orçamento") || osStatus.equals("Orçado")) {
                                    TelaOrcamentoViewController controlador = new TelaOrcamentoViewController(os);
                                    controlador.setRun(run);
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(Run.class.getResource("../View/TelaOrcamentoView.fxml"));
                                    loader.setController(controlador);
                                    AnchorPane TelaOrcamento = (AnchorPane) loader.load();
                                    run.getRootLayout().setCenter(TelaOrcamento);
                                } else if(osStatus.equals("Aprovado") || osStatus.equals("Aguardando peça")){
                                    TelaManutencaoController controlador = new TelaManutencaoController(os);
                                    controlador.setRun(run);
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(Run.class.getResource("../View/TelaManutencao.fxml"));
                                    loader.setController(controlador);
                                    AnchorPane TelaManutencao = (AnchorPane) loader.load();
                                    run.getRootLayout().setCenter(TelaManutencao);
                                }
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
                String osStatus = status.getNome();
                if(usuarioLogado.getPerfil().getDescricao().equals("técnico")) {
                    if(osStatus.equals("Em orçamento") || osStatus.equals("Orçado")
                       || osStatus.equals("Aprovado") || osStatus.equals("Aguardando peça")) {
                        observableStatusList.add(status.getNome());
                    }
                } else if(usuarioLogado.getPerfil().getDescricao().equals("telefonista")) {
                    if(osStatus.equals("Aguardando cliente") || osStatus.equals("Pronto")) {
                        observableStatusList.add(status.getNome());
                    }
                } else if(usuarioLogado.getPerfil().getDescricao().equals("atendente")) {
                    if(osStatus.equals("Em orçamento") || osStatus.equals("Orçado")
                       || osStatus.equals("Avisado") || osStatus.equals("Entregue")
                       || osStatus.equals("Garantia") || osStatus.equals("Recusado")) {
                        observableStatusList.add(status.getNome());
                    }
                } else if(usuarioLogado.getPerfil().getDescricao().equals("adm")) {
                    observableStatusList.add(status.getNome());
                }
            }
            filtroOS.setItems(observableStatusList);
            filtroOS.getSelectionModel().select(0);
            
            filtroOS.getSelectionModel().selectedIndexProperty().addListener(new
                    ChangeListener<Number>() {
                        public void changed(ObservableValue ov,
                                Number value, Number new_value) {
                                    String status = filtroOS.getItems().get(Integer.parseInt(new_value.toString()));
                                    if(status!="Todos") {
                                        ArrayList<Integer> newDataList = new ArrayList();
                                        for(int i=0; i<data.length; i++) {
                                            if(data[i][4].equals(status)) {
                                                newDataList.add(i);
                                            }
                                        }
                                        String[][] newData = new String[newDataList.size()][6];
                                        for(int i=0; i<newData.length; i++) {
                                            newData[i][0] = data[newDataList.get(i)][0];
                                            newData[i][1] = data[newDataList.get(i)][1];
                                            newData[i][2] = data[newDataList.get(i)][2];
                                            newData[i][3] = data[newDataList.get(i)][3];
                                            newData[i][4] = data[newDataList.get(i)][4];
                                            newData[i][5] = data[newDataList.get(i)][5];
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

    @FXML
    private void filtrarPesquisa(ActionEvent event) {
        String texto = textoPesquisa.getText();
        if(!texto.isEmpty()) {
            if(listaOS.getItems().isEmpty()) {
                String status = filtroOS.getSelectionModel().getSelectedItem().toString();
                if(status!="Todos") {
                    ArrayList<Integer> newDataList = new ArrayList();
                    for(int i=0; i<data.length; i++) {
                        if(data[i][4].equals(status)) {
                            newDataList.add(i);
                        }
                    }
                    String[][] newData = new String[newDataList.size()][6];
                    for(int i=0; i<newData.length; i++) {
                        newData[i][0] = data[newDataList.get(i)][0];
                        newData[i][1] = data[newDataList.get(i)][1];
                        newData[i][2] = data[newDataList.get(i)][2];
                        newData[i][3] = data[newDataList.get(i)][3];
                        newData[i][4] = data[newDataList.get(i)][4];
                        newData[i][5] = data[newDataList.get(i)][5];
                    }
                    listaOS.getItems().clear();
                    listaOS.getItems().addAll(Arrays.asList(newData));
                } else {
                    listaOS.getItems().clear();
                    listaOS.getItems().addAll(Arrays.asList(data));
                }
            }
            ObservableList<String[]> itemList = listaOS.getItems();
            ArrayList<String[]> newDataList = new ArrayList();

            int index=0;
            for(String[] item : itemList) {
                if(item[0].contains(texto) ||
                   item[1].contains(texto) ||
                   item[2].contains(texto) ||
                   item[3].contains(texto) ||
                   item[4].contains(texto) ||
                   item[5].contains(texto)) {
                    newDataList.add(item);
                }
            }

            String[][] newData = new String[newDataList.size()][6];

            for(int i=0; i<newDataList.size(); i++) {
                newData[i] = newDataList.get(i);
            }

            listaOS.getItems().clear();
            listaOS.getItems().addAll(Arrays.asList(newData));
        } else {
            String status = filtroOS.getSelectionModel().getSelectedItem().toString();
            if(status!="Todos") {
                ArrayList<Integer> newDataList = new ArrayList();
                for(int i=0; i<data.length; i++) {
                    if(data[i][4].equals(status)) {
                        newDataList.add(i);
                    }
                }
                String[][] newData = new String[newDataList.size()][6];
                for(int i=0; i<newData.length; i++) {
                    newData[i][0] = data[newDataList.get(i)][0];
                    newData[i][1] = data[newDataList.get(i)][1];
                    newData[i][2] = data[newDataList.get(i)][2];
                    newData[i][3] = data[newDataList.get(i)][3];
                    newData[i][4] = data[newDataList.get(i)][4];
                    newData[i][5] = data[newDataList.get(i)][5];
                }
                listaOS.getItems().clear();
                listaOS.getItems().addAll(Arrays.asList(newData));
            } else {
                listaOS.getItems().clear();
                listaOS.getItems().addAll(Arrays.asList(data));
            }
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
}
