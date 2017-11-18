/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOImpl.PecaDAOImpl;
import Domain.OS;
import Domain.Peca;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterPeca;
import ServiceImpl.ManterPecaImpl;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

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
    private ListView<String> pecasEstoque;

    @FXML
    private ListView<String> pecasUsadas;

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

    private ObservableList<String> pecasEstoqueString;
    private ObservableList<String> pecasUsadasString;
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
        System.out.println("test");
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
        pecasEstoqueString = FXCollections.observableArrayList();
        for (Peca peca : pecas) {
            pecasEstoqueString.add(peca.getDescricao());
        }
        pecasEstoque.setItems(pecasEstoqueString);
        pecasUsadasString = FXCollections.observableArrayList();
        pecasUsadas.setItems(pecasUsadasString);
        //inicializar demais campos

    }

    public void adicionarPecaUso() {
        if (pecasEstoque.getSelectionModel().getSelectedItem() != null) {
            pecasUsadasString.add(pecasEstoque.getSelectionModel().getSelectedItem());
            pecasEstoqueString.remove(pecasEstoque.getSelectionModel().getSelectedItem());
            recalcularPrecoPecas();
        }
    }

    public void removerPecaUso() {
        if (pecasUsadas.getSelectionModel().getSelectedItem() != null) {
            pecasEstoqueString.add(pecasUsadas.getSelectionModel().getSelectedItem());
            pecasUsadasString.remove(pecasUsadas.getSelectionModel().getSelectedItem());
            recalcularPrecoPecas();
        }
    }
    
    public void recalcularPrecoPecas(){
        double total=0.0;
        for (int i=0; i<pecasUsadasString.size(); i++){
            //procurar no array
            for (Peca peca : pecas) {
                if (peca.getDescricao().equals(pecasUsadasString.get(i))){
                    total+=peca.getPrecoVenda();
                }
            }
        }
        precoPecas.setText("R$"+String.valueOf(total));
    }
    
    public void adicionarPecaEstoque() throws ExcecaoNegocio, ExcecaoPersistencia{
        Peca p = new Peca();
        p.setDescricao(nomePeca.getText());
        p.setPrecoVenda(Double.parseDouble(valorPeca.getText()));
        p.setMarca(marcaPeca.getText());
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        manterPeca.insert(p);
        pecasEstoqueString.add(p.getDescricao());
        refreshPecasEstoqueString();
        recalcularPrecoPecas();
    }
    
    public void refreshPecasEstoqueString() throws ExcecaoPersistencia{
        ManterPeca manterPeca = new ManterPecaImpl(new PecaDAOImpl());
        pecas = (ArrayList<Peca>) manterPeca.listAll();
        for (Peca x1 : pecas) {
            if (!pecasEstoqueString.contains(x1.getDescricao()) && !pecasUsadasString.contains(x1.getDescricao())){
                pecasEstoqueString.add(x1.getDescricao());
            }
        }
    }
    
    

}
