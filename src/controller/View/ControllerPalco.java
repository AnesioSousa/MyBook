package controller.View;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.Principal;

/**
 *
 * @author Neto
 */
public class ControllerPalco extends StackPane{
    private HashMap<String, Node> telas = new HashMap<>(); // Tá node, mas acho que Parent tb funfa

    public ControllerPalco() {
        super();
        
    }

    // Adiciona uma tela à coleção
    private void adicionarTela(String nome, Node tela){
        telas.put(nome, tela);
    }
    
    // Retorna o nó com o nome dado
    public Node getScreen(String name) {
        return telas.get(name);
    }
    
        //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public TelaControlada armazenarTela(String nome, String caminhoFXML) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent loadScreen = (Parent) myLoader.load();
            TelaControlada myScreenControler = ((TelaControlada) myLoader.getController());
            myScreenControler.setControlador(this); // Rever isso!
            adicionarTela(nome, loadScreen);
            return myScreenControler;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    
    public boolean setScreen(final String name) {
        if (telas.get(name) != null) {   //screen loaded
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                getChildren().remove(0);                    //remove the displayed screen
                getChildren().add(0, telas.get(name));     //add the screen
            } else {
                getChildren().add(telas.get(name));       //no one else been displayed, then just show
            }
            Principal.resizeScreen();	//Size the stage to the current screen
            return true;
        } else {
            System.out.println("Não existe uma tela salva com o nome especificado!\n");
            return false;
        }
    }
    
    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (telas.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean containsScreen(String name){
        return telas.containsKey(name);
    }
}
