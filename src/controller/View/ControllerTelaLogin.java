package controller.View;

import facade.Facade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class ControllerTelaLogin implements Initializable, TelaControlada{
    private MainController mainController = MainController.getInstance();
    private ControllerPalco meuControlador;
    
    Facade facade = Facade.getInstance();
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
    
    // Tem que tratar campos vazios!
    @FXML
    public void fazerLogin(ActionEvent e) throws IOException{
        try {
            mainController.logarUser(emailTxtField.getText(), senhaField.getText());               
        } catch (UsuarioNaoCadastradoException ex) {
            showContaErro(); // Tentar ver se é possivel deixar por um tempo visível, e depois deixar ele invisível novamente.
        } catch (SenhaIncorretaException ex) {
            showSenhaErro(); // Tentar ver se é possivel deixar por um tempo visível, e depois deixar ele invisível novamente.
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
    private void goToScreen2(MouseEvent event){
      meuControlador.setScreen(Principal.screen2ID);
    }

    public TextField getEmailTxtField() {
        return emailTxtField;
    }
}
