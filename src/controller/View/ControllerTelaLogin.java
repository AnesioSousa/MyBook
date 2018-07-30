package controller.View;

import java.io.IOException;
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
import view.Principal;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaLogin implements Initializable, TelaControlada{
    private MainController mainController = MainController.getInstance();
    private ControllerPalco meuControlador;
    
    @FXML private Label status;
    @FXML private TextField emailTxtField;
    @FXML private PasswordField senhaField;
    @FXML private Label cadastroLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void fazerLogin(ActionEvent e) throws IOException{
        try {
            mainController.logarUser(emailTxtField.getText(), senhaField.getText()); 
            limparCampos();
        } catch (UsuarioNaoCadastradoException ex) {
            showContaErro(); 
        } catch (SenhaIncorretaException ex) {
            showSenhaErro();
        }
    }
    
    @Override
    public void setControlador(ControllerPalco master){
        meuControlador = master;
    }
    
    private void showContaErro(){
        status.setText("Não existe registro com esse email!");
        status.setVisible(true);
    }
    
    private void showSenhaErro(){
        status.setText("Senha incorreta!");
        status.setVisible(true);
    }
    
    @FXML
    private void sublinharLabel(){
        cadastroLabel.setUnderline(true);
    }
    
    @FXML
    private void desSublinharLabel(){
        cadastroLabel.setUnderline(false);
    }
    
    @FXML
    private void esconderStatus(){
        status.setVisible(false);
    }
        
    @FXML
    private void goToScreen2(MouseEvent event){
        limparCampos();
        meuControlador.setScreen(Principal.screen2ID);
    }
    
    private void limparCampos(){
        emailTxtField.setText("");
        senhaField.setText("");
    }
    public TextField getEmailTxtField() {
        return emailTxtField;
    }
}
