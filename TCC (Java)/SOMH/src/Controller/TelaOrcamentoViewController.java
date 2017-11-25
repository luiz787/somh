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
import DAOImpl.StatusDAOImpl;
import Domain.Acessorio;
import Domain.Equipamento;
import Domain.OS;
import Domain.OSAcessorio;
import Domain.OSStatus;
import Domain.Status;
import Domain.Usuario;
import Exception.ExcecaoNegocio;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private Label faixa;
    @FXML
    private TextField totalServico;
    @FXML
    private Button Menu;

    private OS os;

    private List<OSStatus> osStatus;

    private Usuario usuario;

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

        usuario = LoginController.getUsuarioLogado();

        ManterOS manterOS;
        ManterOSAcessorio manterOSAcessorio;
        ManterOSStatus manterOSStatus;
        List<OSAcessorio> osAcessorios;
        String acessorios = "";

        try {

            manterOS = new ManterOSImpl(OSDAOImpl.getInstance());
            manterOSAcessorio = new ManterOSAcessorioImpl(OSAcessorioDAOImpl.getInstance());
            manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());

            osAcessorios = manterOSAcessorio.getAllByOS(os.getId());
            osStatus = manterOSStatus.getAllByOS(os.getId());

            System.out.println("Criou objetos");
            if (osAcessorios.size() == 0) {
                acessorios = "Sem acessórios registrados.";
            } else if (osAcessorios.size() == 1) {
                acessorios = osAcessorios.get(0).getAcessorio().getNomeAcessorio();
            } else if (osAcessorios.size() > 1) {
                for (int i = 0; i < osAcessorios.size(); i++) {
                    if (i == (osAcessorios.size() - 1)) {
                        acessorios += " e " + osAcessorios.get(i).getAcessorio().getNomeAcessorio() + ".";
                    } else if (i == (osAcessorios.size() - 2)) {
                        acessorios += osAcessorios.get(i).getAcessorio().getNomeAcessorio();
                    } else {
                        acessorios += osAcessorios.get(i).getAcessorio().getNomeAcessorio() + ", ";
                    }
                }
            }

            Date date = new Date(osStatus.get(0).getDatOcorrencia());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String dataEntrada = dateFormat.format(date);

            codOs.setText(String.valueOf(os.getId()));
            descEquip.setText(os.getEquipamento().getDesEquipto());
            nroSerie.setText(String.valueOf(os.getEquipamento().getNroSerie()));
            descAcessorios.setText(acessorios);
            txtObs.setText(os.getTxtObservacaoAcessorios());
            descDefeitos.setText(os.getTxtReclamacao());

            datEntrada.setText(dataEntrada);
        } catch (ExcecaoPersistencia ex) {
            System.err.println("Erro ao localizar os dados da OS requisitada.");
        }

    }

    @FXML
    private void redirecionaTelaFuncionario() {
        try {
            String telaUsuario = "";
            FXMLLoader loader;
            switch (Integer.parseInt(usuario.getPerfil().getId().toString())) {
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

    @FXML
    private void aguardaCliente() throws IOException {
        OSStatus osStatus = new OSStatus();
        ManterStatus manterStatus;
        ManterOSStatus manterOSStatus;
        Status status;

        try {
            manterStatus = new ManterStatusImpl(StatusDAOImpl.getInstance());
            manterOSStatus = new ManterOSStatusImpl(OSStatusDAOImpl.getInstance());
            status = manterStatus.getStatusById(3);

            System.out.println("ID OS: " + os.getId() + ". ID STATUS: " + status.getNome());
            osStatus.setOs(os);
            osStatus.setStatus(status);

            manterOSStatus.update(osStatus);
            System.out.println("Status alterado com sucesso. Novo status: " + osStatus.getStatus().getNome());
        } catch (ExcecaoPersistencia ex) {
            System.out.println("Erro ao mudar o status do orçamento: " + ex.getMessage());
        }

        FXMLLoader loader;
        loader = new FXMLLoader();

        loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
        AnchorPane TelaTecnico = (AnchorPane) loader.load();

        run.getRootLayout().setCenter(TelaTecnico);

        TelaListagemOSController controller = loader.getController();
        controller.setRun(run);
    }
}
