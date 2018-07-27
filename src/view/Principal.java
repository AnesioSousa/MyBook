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
 * @author Anésio Sousa dos Santos Neto
 */
public class Principal extends Application {
    private Facade facade = Facade.getInstance();
    
    private static Stage stage;

    private static Scene loginScene;
    private static Scene cadastroScene;
    private static Scene navegadorScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");
        
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent login = loginLoader.load();
        loginScene = new Scene(login);
        
        FXMLLoader cadastroLoader = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
        Parent cadastro = cadastroLoader.load();
        cadastroScene = new Scene(cadastro);
        
        FXMLLoader navegadorLoader = new FXMLLoader(getClass().getResource("Navegador.fxml"));
        Parent navegador = navegadorLoader.load();
        navegadorScene = new Scene(navegador);
        
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        // Tem que esperar a tela de login retornar o usuário logado.
    }  

    public static void changeScreen(String scr){ // É possível receber Enum invés de String
        switch(scr){
            case "login":
                stage.setScene(loginScene);
                stage.sizeToScene();
		stage.centerOnScreen();
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                stage.sizeToScene();
		stage.centerOnScreen();
                break;  
            case "navegador":
                stage.setScene(navegadorScene);
                stage.sizeToScene();
		stage.centerOnScreen();
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
