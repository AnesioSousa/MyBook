/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.Facade;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author anesio
 */
public class NewFXMain extends Application {
    private Facade facade = Facade.getInstance();
    private static Stage stage;
    
    private static Scene loginScene;
    private static Scene cadastroScene;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        
        primaryStage.setTitle("Bem vindo ao MyBook!");
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Parent cadastro = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        
        loginScene = new Scene(login);
        cadastroScene = new Scene(cadastro);
        
        primaryStage.setScene(loginScene);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void changeScreen(String scr){ // É possível receber Enum invés de String
        switch(scr){
            case "login":
                stage.setScene(loginScene);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                break;     
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
