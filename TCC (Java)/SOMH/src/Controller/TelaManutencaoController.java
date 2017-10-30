/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Luiz
 */

public class TelaManutencaoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ListView<?> pecasEstoque;

    @FXML
    private ListView<?> pecasUsadas;

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


    @FXML
    void initialize() {
        assert addPeca != null : "fx:id=\"addPeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert adicionarPeca != null : "fx:id=\"adicionarPeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert botaoAprovacao != null : "fx:id=\"botaoAprovacao\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert botaoIrrecuperavel != null : "fx:id=\"botaoIrrecuperavel\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert codigoOS != null : "fx:id=\"codigoOS\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert dataEntrada != null : "fx:id=\"dataEntrada\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert descricaoEquipamento != null : "fx:id=\"descricaoEquipamento\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert marcaPeca != null : "fx:id=\"marcaPeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert nomePeca != null : "fx:id=\"nomePeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert nomeTecnico != null : "fx:id=\"nomeTecnico\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert observacoes != null : "fx:id=\"observacoes\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert pecasEstoque != null : "fx:id=\"pecasEstoque\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert pecasUsadas != null : "fx:id=\"pecasUsadas\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert precoPecas != null : "fx:id=\"precoPecas\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert removerPeca != null : "fx:id=\"removerPeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert servicos != null : "fx:id=\"servicos\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert valorPeca != null : "fx:id=\"valorPeca\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert valorServicos != null : "fx:id=\"valorServicos\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
        assert valorTotal != null : "fx:id=\"valorTotal\" was not injected: check your FXML file 'TelaManutencao.fxml'.";
    }
}

