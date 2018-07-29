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
    
    public static String screen1ID = "login";
    public static String screen1File = "/view/Login.fxml";                          // Talvez nem precise que isso daqui fique aqui.
    public static String screen2ID = "cadastro";                                    // Acho que dá pra carregar tudo no mainController.
    public static String screen2File = "/view/Cadastro.fxml";
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");

        MainController controladorPrincipal = MainController.getInstance();
        controladorPrincipal.getControllerPalco().armazenarTela(Principal.screen1ID, Principal.screen1File);
        controladorPrincipal.getControllerPalco().armazenarTela(Principal.screen2ID, Principal.screen2File);
        
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
