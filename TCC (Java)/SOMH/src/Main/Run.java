/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Domain.Acessorio;
import Controller.CadastroOSViewController;
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
    private ObservableList<Acessorio> acessorioData = FXCollections.observableArrayList();
    
    
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

    
    
    public ObservableList<Acessorio> getAcessorioData() {
        return acessorioData;
    }

    public void setAcessorioData(ObservableList<Acessorio> acessorioData) {
        this.acessorioData = acessorioData;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SOHM");
        initRootLayout();
        showCadastroOSView();
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
            
            loader.setLocation(Run.class.getResource("../View/CadastroOSView.fxml"));
            AnchorPane CadastroOSView = (AnchorPane) loader.load();
            
            rootLayout.setCenter(CadastroOSView);
            
            CadastroOSViewController controller = loader.getController();
            controller.setRun(this);
        
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
