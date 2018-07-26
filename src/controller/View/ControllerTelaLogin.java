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
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;
import view.NewFXMain;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaLogin implements Initializable {
    Facade f = Facade.getInstance();
    @FXML private Label status;
    @FXML private TextField emailTxtField;
    @FXML private PasswordField senhaField;
    
    // Tem que tratar campos vazios!
    @FXML
    protected void fazerLogin(ActionEvent e){
        try {
            f.iniciarSessao(emailTxtField.getText(), senhaField.getText());
            status.setText("OK!");
            status.setVisible(true);
        } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
            System.out.println(ex);
            status.setText("NOT OK!");
            status.setVisible(true);
        }
    }
    
    @FXML
    protected void btNovoAction(MouseEvent e){
        NewFXMain.changeScreen("cadastro");
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
    @FXML
    protected void sublinharLabel(MouseEvent e){
        
    }
}
