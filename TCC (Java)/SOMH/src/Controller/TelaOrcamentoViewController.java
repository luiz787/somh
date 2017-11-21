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
import DAOImpl.OSStatusDAOImpl;
import Domain.Acessorio;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSAcessorio;
import Domain.OSStatus;
import Domain.Usuario;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterOS;
import Service.ManterOSAcessorio;
import Service.ManterOSStatus;
import ServiceImpl.ManterOSAcessorioImpl;
import ServiceImpl.ManterOSImpl;
import ServiceImpl.ManterOSStatusImpl;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

    private List<OSStatus> osStatus;

    private Usuario usuarioLogado;

    public List<OSStatus> getOsStatus() {
        return osStatus;
    }

    public void setOsStatus(List<OSStatus> osStatus) {
        this.osStatus = osStatus;
    }

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
        ManterOSStatus manterOSStatus;
        List<OSAcessorio> osAcessorios;
        String acessorios = "";

        try {
            manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            manterOSStatus = new ManterOSStatusImpl (OSStatusDAOImpl.getInstance());
            osAcessorios = manterOSAcessorio.getAllByOS(os.getId());
            osStatus = manterOSStatus.getAllByOS(os.getId());

            for (int i = 0; i < osAcessorios.size(); i++) {
                acessorios += osAcessorios.get(i).getAcessorio().getNomeAcessorio() + ", ";
            }

            codOs.setText(String.valueOf(os.getId()));
            descEquip.setText(os.getEquipamento().getDesEquipto());
            nroSerie.setText(String.valueOf(os.getEquipamento().getNroSerie()));
            descAcessorios.setText(acessorios);
            txtObs.setText(os.getTxtObservacaoAcessorios());
            descDefeitos.setText(os.getTxtReclamacao());
            datEntrada.setText(osStatus.get(0).getDatOcorrencia().toString());
        } catch (ExcecaoPersistencia ex) {
            System.err.println("Erro ao localizar os dados da OS requisitada.");
        }

    }

    @FXML
    private void redirecionaTelaFuncionario() {
        try {
            String telaUsuario = "";
            FXMLLoader loader;
            switch (Integer.parseInt(usuarioLogado.getPerfil().getId().toString())) {
                case 1:
                    telaUsuario = "TelaAdministradorView.fxml";

                    loader = new FXMLLoader();

                    loader.setLocation(Run.class.getResource("../View/" + telaUsuario));
                    AnchorPane TelaAdministrador = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAdministrador);

                    TelaAdministradorViewController controllerAdministrador = loader.getController();
                    controllerAdministrador.setRun(run);
                    break;
                case 2:
                    telaUsuario = "TelaAtendenteView.fxml";

                    loader = new FXMLLoader();

                    loader.setLocation(Run.class.getResource("../View/" + telaUsuario));
                    AnchorPane TelaAtendente = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaAtendente);

                    TelaAtendenteViewController controllerAtendente = loader.getController();
                    controllerAtendente.setRun(run);
                    break;
                case 3:
                    telaUsuario = "TelaTelefonistaView.fxml";

                    loader = new FXMLLoader();

                    loader.setLocation(Run.class.getResource("../View/" + telaUsuario));
                    AnchorPane TelaTelefonista = (AnchorPane) loader.load();

                    run.getRootLayout().setCenter(TelaTelefonista);

                    TelaTelefonistaViewController controllerTelefonista = loader.getController();
                    controllerTelefonista.setRun(run);
                    break;
                case 4:
                    telaUsuario = "TelaTecnicoView.fxml";

                    loader = new FXMLLoader();

                    loader.setLocation(Run.class.getResource("../View/" + telaUsuario));
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
