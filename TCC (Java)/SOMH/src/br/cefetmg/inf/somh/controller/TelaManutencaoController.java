/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.controller;

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

import br.cefetmg.inf.somh.dao.impl.OSItemPecaDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSItemServicoDAOImpl;
import br.cefetmg.inf.somh.dao.impl.OSStatusDAOImpl;
import br.cefetmg.inf.somh.dao.impl.PecaDAOImpl;
import br.cefetmg.inf.somh.dao.impl.ServicoDAOImpl;
import br.cefetmg.inf.somh.domain.OS;
import br.cefetmg.inf.somh.domain.OSItemPeca;
import br.cefetmg.inf.somh.domain.OSItemServico;
import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.domain.Peca;
import br.cefetmg.inf.somh.domain.Servico;
import br.cefetmg.inf.somh.domain.Status;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;
import br.cefetmg.inf.somh.main.Run;
import br.cefetmg.inf.somh.service.ManterOSItemPeca;
import br.cefetmg.inf.somh.service.ManterOSItemServico;
import br.cefetmg.inf.somh.service.ManterOSStatus;
import br.cefetmg.inf.somh.service.ManterPeca;
import br.cefetmg.inf.somh.service.ManterServico;
import br.cefetmg.inf.somh.service.impl.ManterOSItemPecaImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSItemServicoImpl;
import br.cefetmg.inf.somh.service.impl.ManterOSStatusImpl;
import br.cefetmg.inf.somh.service.impl.ManterPecaImpl;
import br.cefetmg.inf.somh.service.impl.ManterServicoImpl;
import javafx.beans.property.SimpleStringProperty;
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
    private Button adicionarServico;
    
    @FXML
    private Button removerServico;

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
    private TableView<Servico> servicosEstoque;
    
    @FXML
    private TableView<Servico> servicosUsados;
    
    @FXML
    private TableColumn<Servico, String> servicosEstoqueColumn;
    
    @FXML
    private TableColumn<Servico, String> servicosUsadosColumn;

    @FXML
    private Label precoPecas;

    @FXML
    private Button removerPeca;

    @FXML
    private TextField valorPeca;

    @FXML
    private Label valorServicos;

    @FXML
    private Label valorTotal;

    private Run run;
    private OS os;

    private ArrayList<Servico> servicos;
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
        ManterServico manterServico = new ManterServicoImpl(ServicoDAOImpl.getInstance());
        try {
            servicos = (ArrayList<Servico>) manterServico.listAll();
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Servico> servicosData = FXCollections.observableArrayList();
        for (Servico servico:servicos){
            servicosData.add(servico);
        }
        servicosEstoque.setItems(servicosData);
        servicosEstoqueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        servicosUsadosColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        codigoOS.setText(String.valueOf(os.getId()));
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        try {
            dataEntrada.setText(String.valueOf(new Timestamp(manterOSStatus.getAllByOS(os.getId()).get(0).getDatOcorrencia())).substring(0, 10));
        } catch (ExcecaoPersistencia ex) {
            Logger.getLogger(TelaManutencaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        descricaoEquipamento.setText(os.getEquipamento().getDesEquipto());
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
    
    public void recalcularPrecoServicos(){
        double total = 0.0;
        for (int i=0; i<servicosUsados.getItems().size(); i++){
            total+=Double.parseDouble(servicosUsados.getItems().get(i).getValor());//string???????
        }
        valorServicos.setText(String.valueOf(total));
        if (!precoPecas.getText().isEmpty()){
            valorTotal.setText(String.valueOf(Double.parseDouble(precoPecas.getText()) + total));
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
        recalcularPrecoPecas();
    }
    
    public void adicionarServicoUso(){
        if (servicosEstoque.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Servico> newServicoData = FXCollections.observableArrayList();
            for (int i = 0; i < servicosUsados.getItems().size(); i++) {
                Servico servicoExistente = servicosUsados.getItems().get(i);
                newServicoData.add(servicoExistente);
            }
            newServicoData.add(servicosEstoque.getSelectionModel().getSelectedItem());
            servicosUsados.setItems(newServicoData);
            recalcularPrecoServicos();
        }
    }
    
    public void removerServicoUso(){
        if (servicosUsados.getSelectionModel().getSelectedItem() != null) {
            servicosUsados.getItems().remove(servicosUsados.getSelectionModel().getSelectedItem());
            recalcularPrecoServicos();
        }
    }

    public void setIrrecuperavel() throws ExcecaoPersistencia, ExcecaoNegocio {
        //set próximo status da OS como irrecuperável
        ManterOSStatus manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
        manterOSStatus.cadastrarOSStatus(new OSStatus(os, new Date().getTime(), LoginController.getUsuarioLogado(), new Status(11)));
        //cadastra um novo status da OS atual, informando a data atual, o funcionário responsável, e o status 11 (irrecuperavel)
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../view/TelaListagemOSView.fxml"));
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
            manterOSItemPeca.cadastrarOSItemPeca(osItemPeca);
        }
        //cadastra os objetos de OSItemPeca, fazendo a relacao entre a OS
        //e as pecas utilizadas por ela.
        ManterServico manterServico = new ManterServicoImpl(ServicoDAOImpl.getInstance());
        ManterOSItemServico manterOSItemServico = new ManterOSItemServicoImpl(OSItemServicoDAOImpl.getInstance());
        Set<Servico> servicosUnicos = new HashSet<>(servicosUsados.getItems());
        for (Servico x1:servicosUnicos){
            OSItemServico osItemServico = new OSItemServico();
            osItemServico.setOs(os);
            osItemServico.setServico(x1);
            osItemServico.setQuantidadeServico(Collections.frequency(servicosUsados.getItems(), x1));
            osItemServico.setSituacao(true);
            osItemServico.setValorServico(Double.parseDouble(x1.getValor()));
            manterOSItemServico.cadastrarOSItemServico(osItemServico);
        }
        //cadastra os objetos de OSItemServico, fazendo a relacao entre a OS
        //e os servicos utilizadas por ela.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Run.class.getResource("../view/TelaListagemOSView.fxml"));
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
