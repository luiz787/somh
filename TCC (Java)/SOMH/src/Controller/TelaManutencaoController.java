/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Run;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Luiz
 */
public class TelaManutencaoController implements Initializable {

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

    private Run run;

    public void setRun(Run run) {
        this.run = run;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        if (LoginController.getUsuarioLogado()==null){
            nomeTecnico.setText("-");
        } else {
            nomeTecnico.setText(LoginController.getUsuarioLogado().getNome());
        }
        
    }

}
