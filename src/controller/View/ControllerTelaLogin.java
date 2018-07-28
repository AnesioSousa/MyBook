package controller.View;

import facade.Facade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaLogin implements Initializable{
            
    Facade facade = Facade.getInstance();
    @FXML private Label status;
    @FXML private TextField emailTxtField;
    @FXML private PasswordField senhaField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    // Tem que tratar campos vazios!
    @FXML
    public Usuario fazerLogin(ActionEvent e) throws IOException{
        Usuario usuario = null;
        try {
            usuario = facade.iniciarSessao(emailTxtField.getText(), senhaField.getText());
            
            FXMLLoader navegadorLoader = new FXMLLoader(getClass().getResource("/view/Navegador.fxml"));
            Parent navegador = navegadorLoader.load();
            Scene navegadorScene = new Scene(navegador);
            
            ControllerTelaNavegador controller = navegadorLoader.getController();
            
            controller.initData(usuario);
            
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            
            window.setScene(navegadorScene);
            window.centerOnScreen();
            window.sizeToScene();
            
            
            status.setText("OK!");
            status.setVisible(true);

        } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
            System.out.println(ex);
            status.setText("NOT OK!");
            status.setVisible(true);
        }
        return usuario;
    }
        
    @FXML
    private void goToScreen2(MouseEvent event){
       Principal.changeScreen("cadastro");
    }

    public TextField getEmailTxtField() {
        return emailTxtField;
    }
}