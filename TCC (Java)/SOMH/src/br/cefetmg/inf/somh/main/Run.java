package br.cefetmg.inf.somh.main;

import br.cefetmg.inf.somh.controller.CadastroClienteViewController;
import br.cefetmg.inf.somh.controller.CadastroUsuarioController;
import br.cefetmg.inf.somh.controller.LoginController;
import br.cefetmg.inf.somh.controller.TelaCadastroOSController;
import br.cefetmg.inf.somh.controller.TelaListagemClienteController;
import br.cefetmg.inf.somh.controller.TelaListagemOSController;
import br.cefetmg.inf.somh.controller.TelaManutencaoController;
import br.cefetmg.inf.somh.controller.TelaOSController;
import br.cefetmg.inf.somh.domain.Cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author andro
 */
public class Run extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Cliente cliente;

    public Run() {
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SOHM");
        initRootLayout();
        showLogin();
    }

    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Run.class.getResource("../View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showCadastroClienteView() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Run.class.getResource("../View/CadastroClienteView.fxml"));
            AnchorPane CadastroClienteView = (AnchorPane) loader.load();

            rootLayout.setCenter(CadastroClienteView);

            CadastroClienteViewController controller = loader.getController();
            controller.setRun(this);

        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Run.class.getResource("../View/Login.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            rootLayout.setCenter(Login);
            LoginController controller = loader.getController();
            controller.setRun(this);
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
