package Controller;

import Domain.Perfil;
import Domain.Usuario;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Main.Run;
import Service.ManterUsuario;
import ServiceImpl.ManterUsuarioImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Victor Cesar
 */
public class CadastroUsuarioController implements Initializable {

    @FXML
    private Button btnCadastrarCliente;
    @FXML
    private TextField nom_usuario;
    @FXML
    private TextField txt_senha;
    @FXML
    private TextField confirmaSenha;
    @FXML
    private ChoiceBox<String> des_perfil;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnSair;
    private Run run;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaTipos = FXCollections.observableArrayList("Técnico", "Atendente", "Telefonista");
        des_perfil.setItems(listaTipos);
    }

    public void setRun(Run run) {
        this.run = run;
    }

    @FXML
    private void cadastraUsuario() {
        if (!validacao() && !txt_senha.getText().equals(confirmaSenha.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de Usuario");
            alert.setHeaderText("Erro de cadastro");
            alert.setContentText("As senhas nao sao compativeis!!!");
            alert.showAndWait();
        }

        if (!validacao() && txt_senha.getText().equals(confirmaSenha.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de Usuario");
            alert.setHeaderText("Erro de cadastro");
            alert.setContentText("Preencha todos os campos!!!");
            alert.showAndWait();
        }

        if (validacao()) {
            try {
                ManterUsuario manterUser = new ManterUsuarioImpl();
                Usuario user = new Usuario();
                Perfil perfil = new Perfil();

                user.setNome(nom_usuario.getText());
                user.setSenha(txt_senha.getText());

                perfil.setDescricao(des_perfil.getValue());

                switch (des_perfil.getValue()) {
                    case "Técnico": perfil.setId((long) 1); break;
                    case "Atendente": perfil.setId((long) 2); break;
                    case "Telefonista": perfil.setId((long) 3); break;
                }

                user.setPerfil(perfil);
                user.setId(manterUser.cadastrar(user));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cadastro de Usuario");
                alert.setHeaderText("Sucesso");
                alert.setContentText("Funcionário cadastrado com sucesso!");

            } catch (ExcecaoPersistencia ex) {
                System.out.println("Excecao de persistencia!");
            } catch (ExcecaoNegocio ex) {
                System.out.println("Excecao de negocio!");
            }
        }
    }

    private boolean validacao() {
        boolean validacao = true;

        if (nom_usuario.getText().isEmpty()) {
            validacao = false;
        }

        if (txt_senha.getText().isEmpty()) {
            validacao = false;
        }

        if (confirmaSenha.getText().isEmpty()) {
            validacao = false;
        }

        if (des_perfil.getValue() == null) {
            validacao = false;
        }

        if (!txt_senha.getText().equals(confirmaSenha.getText())) {
            validacao = false;
        }

        return validacao;
    }
}
