package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Neto
 */
public class NavegadorMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent navegador = FXMLLoader.load(getClass().getResource("Navegador.fxml"));
        Scene navegadorScene = new Scene(navegador);
        
        primaryStage.setScene(navegadorScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
