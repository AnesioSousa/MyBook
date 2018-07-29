package controller.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class MasterController { // Tentar criar uns métodos estáticos aqui só pra ajudar
    HashMap<Usuario, Parent> perfis;

    // é melhor criar um stage pro navegador e esse cara controlar.
    // pq ai o login só pede pro master logar tal conta, e o master passa pro navegador 
    
    public MasterController() throws IOException {
        perfis = new HashMap<>();  
    }

    public void criarPerfilUser(Usuario user) throws IOException{
        FXMLLoader perfilLoader = new FXMLLoader(getClass().getResource("/view/Perfil.fxml"));
        Parent perfil = perfilLoader.load();            
        ControllerTelaPerfil controller = perfilLoader.getController();
        controller.initialize(user);
        perfis.put(user, perfil);
    }
    
    public void abrirNavegador(Usuario user){
        
    }
        
    // Aqui eu não quero ficar criando novos navegadores toda vez que for pedido pra logar não.
    // quero que ele só abra (inicialize) o navegador pegando as informações desse user.
    // Se não poder ficar "inicializando" um navegador que já foi iniciado previamente, fudeo. Pq foi ter que criar algum método pra 
    // poder atualizar o navegador.
    public void iniciarNavegador(Usuario user, Stage stage){ 
        try {
            FXMLLoader navegadorLoader = new FXMLLoader(getClass().getResource("/view/Navegador.fxml"));
            Parent navegador = navegadorLoader.load();
            Scene navegadorScene = new Scene(navegador);
            
            ControllerTelaNavegador controller = navegadorLoader.getController();
            controller.initialize(user, perfis);

            stage.setScene(navegadorScene);
            stage.centerOnScreen();
            stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
