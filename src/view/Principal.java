package view;

import controller.View.ControllerTelaCadastro;
import controller.View.ControllerTelaLogin;
import controller.View.MainController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Principal extends Application {

    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");

        MainController controladorPrincipal = new MainController();
        
        controladorPrincipal.getControllerPalco().setScreen("login");
        
        Group root = new Group();
        root.getChildren().addAll(controladorPrincipal.getControllerPalco());
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void resizeScreen(){
	stage.sizeToScene();
	stage.centerOnScreen();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
