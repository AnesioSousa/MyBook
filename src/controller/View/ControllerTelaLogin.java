package controller.View;

import facade.Facade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;
import view.ControladorDeTelas;
import view.Principal;
import view.TelaControlada;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaLogin implements Initializable, TelaControlada{
    ControladorDeTelas meuControlador;
    
    Facade f = Facade.getInstance();
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
      
    @Override
    public void setarTelaPai(ControladorDeTelas telaPai) {
        meuControlador = telaPai;
    }
    
    // Tem que tratar campos vazios!
    @FXML
    public Usuario fazerLogin(ActionEvent e){
        Usuario u = null;
        try {
            u = f.iniciarSessao(emailTxtField.getText(), senhaField.getText());
            status.setText("OK!");
            status.setVisible(true);
            goToScreen3(e);

        } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
            System.out.println(ex);
            status.setText("NOT OK!");
            status.setVisible(true);
        }
        return u;
    }
        
    @FXML
    private void goToScreen2(MouseEvent event){
       meuControlador.setScreen(Principal.screen2ID);
    }
    
    @FXML
    private void goToScreen3(ActionEvent event){
       meuControlador.setScreen(Principal.screen3ID);
    }

}
