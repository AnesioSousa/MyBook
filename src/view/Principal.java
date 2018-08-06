package view;

import controller.View.PrincipalController;
import facade.Facade;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Principal extends Application {

    private static Stage stage;
    private static PrincipalController controladorPrincipal;
    private static Facade facade = new Facade();
    
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");

        controladorPrincipal= new PrincipalController(facade);   
        controladorPrincipal.getControllerPalco().setScreen("login");
        
        Group root = new Group();
        root.getChildren().addAll(controladorPrincipal.getControllerPalco());
        
        stage.setOnCloseRequest(e ->{
            e.consume();
            close();
        });
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void resizeScreen(){
	stage.sizeToScene();
	stage.centerOnScreen();
    }
    
    private static void close(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Mas já? Fique mais um pouco...");
        alert.setGraphic(new ImageView("/view/Imagens/exit.png"));
        alert.setContentText("Deseja mesmo sair?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            facade.atualizarBaseDeDados();
            Platform.exit();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
