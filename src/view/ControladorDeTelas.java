package view;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Angie
 */
public class ControladorDeTelas extends StackPane {

    // Guarda a coleção de telas a serem exibidas
    private HashMap<String, Node> telas = new HashMap<>();

    public ControladorDeTelas() {
        super();
    }

    // Adiciona uma tela à coleção de telas
    private void adicionarTela(String nome, Node tela) {
        telas.put(nome, tela);
    }

    // Retorna o nó que contém esse nome
    public Node getTela(String name) {
        return telas.get(name);
    }

    // Carrega o arquivo fxml, adiciona a tela à coleção de telas e finalmente injeta o
    // painel da tela ao controller 
    //finally injects the screenPane to the controller.
    public boolean guardarTela(String nome, String caminhoDoFXML) {
        try {
            FXMLLoader meuCarregador = new FXMLLoader(getClass().getResource(caminhoDoFXML));
            Parent telaCarregada = (Parent) meuCarregador.load();
            TelaControlada myScreenControler = ((TelaControlada) meuCarregador.getController());
            myScreenControler.setarTelaPai(this);
            adicionarTela(nome, telaCarregada);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
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
                Principal.resizeScreen();	//Size the stage to the current screen
            } else {
                getChildren().add(telas.get(name));       //no one else been displayed, then just show 
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
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
}
