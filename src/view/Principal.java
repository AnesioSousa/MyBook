package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Principal extends Application {

    private static Stage stage;
    private static Scene loginScene;
    private static Scene cadastroScene;
    private static EventHandler<ActionEvent> buttonHandler;

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

        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
        
    public static void changeScreen(String scr) { // É possível receber Enum invés de String
        switch (scr) {
            case "login":
                stage.setScene(loginScene);
                stage.centerOnScreen();
                stage.sizeToScene();
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                stage.centerOnScreen();
                stage.sizeToScene();
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
