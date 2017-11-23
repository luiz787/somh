/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.OSItemPecaDAOImpl;
import DAOImpl.OSStatusDAOImpl;
import DAOImpl.PecaDAOImpl;
import Domain.OS;
import Domain.OSItemPeca;
import Domain.OSStatus;
import Domain.Peca;
import Domain.Status;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOSItemPeca;
import Service.ManterOSStatus;
import Service.ManterPeca;
import ServiceImpl.ManterOSItemPecaImpl;
import ServiceImpl.ManterOSStatusImpl;
import ServiceImpl.ManterPecaImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Luiz
 */
public class TelaManutencaoController implements Initializable {

    public TelaManutencaoController() {
    }

    @FXML
    private Button addPeca;

    @FXML
    private Button adicionarPeca;

    @FXML
    private Button botaoAprovacao;

    @FXML
    private Button botaoIrrecuperavel;

    @FXML
    private Label codigoOS;

    @FXML
    private Label dataEntrada;

    @FXML
    private Label descricaoEquipamento;

    @FXML
    private TextField marcaPeca;

    @FXML
    private TextField nomePeca;

    @FXML
    private Label nomeTecnico;

    @FXML
    private TextArea observacoes;

    @FXML
    private TableView<Peca> pecasEstoque;

    @FXML
    private TableView<Peca> pecasUsadas;

    @FXML
    private TableColumn<Peca, String> pecasEstoqueColumn;

    @FXML
    private TableColumn<Peca, String> pecasUsadasColumn;

    @FXML
    private Label precoPecas;

    @FXML
    private Button removerPeca;

    @FXML
    private TextArea servicos;

    @FXML
    private TextField valorPeca;

    @FXML
    private TextField valorServicos;

    @FXML
    private TextField valorTotal;

    private Run run;
    private OS os;

    private ArrayList<Peca> pecas;

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public TelaManutencaoController(OS os) {
        this.os = os;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginController.getUsuarioLogado() == null) {
            nomeTecnico.setText("-");
        } else {
            nomeTecnico.setText(LoginController.getUsuarioLogado().getNome());
        }
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        try {
            pecas = (ArrayList<Peca>) manterPeca.listAll();
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Peca> pecasData = FXCollections.observableArrayList();
        for (Peca peca : pecas) {
            pecasData.add(peca);
        }
        pecasEstoque.setItems(pecasData);
        pecasEstoqueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        pecasUsadasColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        codigoOS.setText(String.valueOf(os.getId()));
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        try {
            dataEntrada.setText(String.valueOf(new Timestamp(manterOSStatus.getAllByOS(os.getId()).get(0).getDatOcorrencia())).substring(0, 10));
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        descricaoEquipamento.setText(os.getEquipamento().getDesEquipto());
        valorServicos.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if (!valorServicos.getText().isEmpty()) {
                Double tot = Double.parseDouble(valorServicos.getText()) + Double.parseDouble(precoPecas.getText());
                valorTotal.setText(String.valueOf(tot));
            } else {
                Double tot = Double.parseDouble(precoPecas.getText());
                valorTotal.setText(String.valueOf(tot));
            }
        });
    }

    public void adicionarPecaUso() {
        if (pecasEstoque.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Peca> newPecaData = FXCollections.observableArrayList();
            for (int i = 0; i < pecasUsadas.getItems().size(); i++) {
                Peca pecaExistente = pecasUsadas.getItems().get(i);
                newPecaData.add(pecaExistente);
            }
            newPecaData.add(pecasEstoque.getSelectionModel().getSelectedItem());
            pecasUsadas.setItems(newPecaData);
            recalcularPrecoPecas();
        }
    }

    public void removerPecaUso() {
        if (pecasUsadas.getSelectionModel().getSelectedItem() != null) {
            pecasUsadas.getItems().remove(pecasUsadas.getSelectionModel().getSelectedItem());
            recalcularPrecoPecas();
        }
    }

    public void recalcularPrecoPecas() {
        double total = 0.0;
        for (int i = 0; i < pecasUsadas.getItems().size(); i++) {
            total += pecasUsadas.getItems().get(i).getPrecoVenda();
        }
        precoPecas.setText(String.valueOf(total));
        if (!valorServicos.getText().isEmpty()) {
            valorTotal.setText(String.valueOf(Double.parseDouble(valorServicos.getText()) + total));
        } else {
            valorTotal.setText(String.valueOf(total));
        }
    }

    public void adicionarPecaEstoque() throws ExcecaoNegocio, ExcecaoPersistencia {
        Peca p = new Peca();
        p.setDescricao(nomePeca.getText());
        p.setPrecoVenda(Double.parseDouble(valorPeca.getText()));
        p.setMarca(marcaPeca.getText());
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        manterPeca.insert(p);
        pecasEstoque.getItems().add(p);
        refreshPecasEstoqueString();
        recalcularPrecoPecas();
    }

    public void refreshPecasEstoqueString() throws ExcecaoPersistencia {
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        pecas = (ArrayList<Peca>) manterPeca.listAll();
        for (Peca x1 : pecas) {
            if (!pecasEstoque.getItems().contains(x1)) {
                pecasEstoque.getItems().add(x1);
            }
        }
    }

    public void setIrrecuperavel() throws ExcecaoPersistencia, ExcecaoNegocio {
        //set próximo status da OS como irrecuperável
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        manterOSStatus.cadastrarOSStatus(new OSStatus(os, new Date().getTime(), LoginController.getUsuarioLogado(), new Status(11)));
        //cadastra um novo status da OS atual, informando a data atual, o funcionário responsável, e o status 11 (irrecuperavel)
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
        AnchorPane TelaFuncionario = null;
        try {
            TelaFuncionario = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        run.getRootLayout().setCenter(TelaFuncionario);
        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
        //retornar para listagem de OS
    }

    public void setPronto() throws ExcecaoPersistencia, ExcecaoNegocio {
        //set próximo status da OS como pronto
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        manterOSStatus.cadastrarOSStatus(new OSStatus(os, new Date().getTime(), LoginController.getUsuarioLogado(), new Status(7)));
        //cadastra um novo status da OS atual, informando a data atual, o funcionário responsável, e o status 7 (pronto)
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        ManterOSItemPeca manterOSItemPeca = new ManterOSItemPecaImpl(OSItemPecaDAOImpl.getInstance());
        Set<Peca> pecasUnicas = new HashSet<>(pecasUsadas.getItems());
        for (Peca x1 : pecasUnicas) { //itera sobre o SET, que contém apenas valores únicos
            OSItemPeca osItemPeca = new OSItemPeca();
            osItemPeca.setOs(os);
            osItemPeca.setId(x1.getId());
            osItemPeca.setQtd(Collections.frequency(pecasUsadas.getItems(), x1));//determina a quantidade a partir da frequencia de ocorrencia no arraylist
            osItemPeca.setValorVenda(x1.getPrecoVenda());
            osItemPeca.setSituacao("Peça trocada");
            System.out.println("Inseri uma peça. -> ");
            manterOSItemPeca.cadastrarOSItemPeca(osItemPeca);
        }
        //cadastra os objetos de OSItemPeca, fazendo a relacao entre a OS
        //e as pecas utilizadas por ela.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
        AnchorPane TelaFuncionario = null;
        try {
            TelaFuncionario = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        run.getRootLayout().setCenter(TelaFuncionario);

        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
        //retornar para listagem de OS
    }

}
