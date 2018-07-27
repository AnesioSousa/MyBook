package view;

import facade.Facade;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Principal extends Application {

    private Facade facade = Facade.getInstance();
    
    private static Stage stage;

    public static String screen1ID = "tela1";
    public static String screen1File = "Login.fxml";

    public static String screen2ID = "tela2";
    public static String screen2File = "Cadastro.fxml";

    public static String screen3ID = "tela3";
    public static String screen3File = "Navegador.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Bem vindo ao MyBook!");

        ControladorDeTelas containerPrincipal = new ControladorDeTelas();

        containerPrincipal.guardarTela(screen1ID, screen1File);
        containerPrincipal.guardarTela(screen2ID, screen2File);
        containerPrincipal.guardarTela(screen3ID, screen3File);

        containerPrincipal.setScreen(screen1ID);

        Group root = new Group();
        root.getChildren().addAll(containerPrincipal);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Tem que esperar a tela de login retornar o usuário logado.
    }  

    
    public static void resizeScreen() {
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
