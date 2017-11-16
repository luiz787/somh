/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Domain.Acessorio;
import Controller.TelaCadastroOSController;
import Controller.CadastroUsuarioController;
import Controller.LoginController;
import Controller.TelaListagemOSController;
import Controller.TelaManutencaoController;
import Controller.TelaOSController;
import Domain.Cliente;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
        //showCadastroOSView();
        //showLogin();
        showCadastroUsuario();
        //showListagemOS();
        //showTelaManutencao();
        //showOS();
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
    
    public void showCadastroOSView() {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaCadastroOSView.fxml"));
            AnchorPane CadastroOSView = (AnchorPane) loader.load();
            
            rootLayout.setCenter(CadastroOSView);
            
            TelaCadastroOSController controller = loader.getController();
            controller.setRun(this);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showListagemOS() {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaListagemOSView.fxml"));
            AnchorPane TelaListagemOS = (AnchorPane) loader.load();
            
            rootLayout.setCenter(TelaListagemOS);
            
            TelaListagemOSController controller = loader.getController();
            controller.setRun(this);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showTelaManutencao() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Run.class.getResource("../View/TelaManutencao.fxml"));
            AnchorPane telaManutencao = (AnchorPane) loader.load();
            rootLayout.setCenter(telaManutencao);
            TelaManutencaoController controller = loader.getController();
            controller.setRun(this);
            primaryStage.setWidth(1000);
            primaryStage.setHeight(600);
        } catch (IOException ex){
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showOS() {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Run.class.getResource("../View/TelaOSView.fxml"));
            AnchorPane TelaOS = (AnchorPane) loader.load();
            
            rootLayout.setCenter(TelaOS);
            
            TelaOSController controller = loader.getController();
            controller.setRun(this);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Run.class.getResource("../View/Login.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            rootLayout.setCenter(Login);
            LoginController controller = loader.getController();
            controller.setRun(this);
        } catch (IOException ex){
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showCadastroUsuario(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Run.class.getResource("../View/CadastroUsuario.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            rootLayout.setCenter(Login);
            CadastroUsuarioController controller = loader.getController();
            controller.setRun(this);
        } catch (IOException ex){
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
