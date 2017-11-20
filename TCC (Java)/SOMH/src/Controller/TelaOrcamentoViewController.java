/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OSAcessorioDAO;
import DAO.OSDAO;
import DAOImpl.OSAcessorioDAOImpl;
import DAOImpl.OSDAOImpl;
import Domain.Acessorio;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSAcessorio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOS;
import Service.ManterOSAcessorio;
import ServiceImpl.ManterOSAcessorioImpl;
import ServiceImpl.ManterOSImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TelaOrcamentoViewController implements Initializable {

    private Run run;
    @FXML
    private Pane painel;
    @FXML
    private Button criar;
    @FXML
    private Label codOs;
    @FXML
    private Label descEquip;
    @FXML 
    private Label nroSerie;
    @FXML
    private Label descAcessorios;
    @FXML
    private Label txtObs;
    @FXML
    private Label descDefeitos;
    @FXML
    private Label datEntrada;
    
    private OS os;
    
    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public TelaOrcamentoViewController(OS os) {
        this.os = os;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Iniciando tela de orçamento...");

        ManterOS manterOS;
        ManterOSAcessorio manterOSAcessorio;
        OS os;
        List<OSAcessorio> osAcessorios;
        String acessorios = "";
        
        try {
            manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            os = manterOS.getOSById(TelaListagemOSController.getIdOsSelecionada());
            manterOSAcessorio = new ManterOSAcessorioImpl (OSAcessorioDAOImpl.getInstance());
            osAcessorios = manterOSAcessorio.getAllByOS(TelaListagemOSController.getIdOsSelecionada());
            
            for (int i = 0; i < osAcessorios.size(); i++){
                acessorios += osAcessorios.get(i).getAcessorio().getNomeAcessorio() + ", ";                        
            }
            
            codOs.setText(String.valueOf(os.getId()));
            descEquip.setText(os.getEquipamento().getDesEquipto());
            nroSerie.setText(String.valueOf(os.getEquipamento().getNroSerie()));
            descAcessorios.setText(acessorios);
            txtObs.setText(os.getTxtObservacaoAcessorios());
            descDefeitos.setText(os.getTxtReclamacao());
            //datEntrada.setText();
        } catch (ExcecaoPersistencia ex) {
            System.err.println("Erro ao localizar os dados da OS requisitada.");
        }

    }
}
