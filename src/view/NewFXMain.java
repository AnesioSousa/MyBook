package view;

import facade.Facade;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    private static Scene navegadorScene;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");
        
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Parent cadastro = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        Parent navegador = FXMLLoader.load(getClass().getResource("Navegador.fxml"));
        
        loginScene = new Scene(login);
        cadastroScene = new Scene(cadastro);
        navegadorScene = new Scene(navegador);
        
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
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
            case "navegador":
                stage.setScene(navegadorScene);
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