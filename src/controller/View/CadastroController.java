package controller.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.NewFXMain;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class CadastroController implements Initializable {

    @FXML
    protected void btnVoltar(ActionEvent e){
        NewFXMain.changeScreen("login");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
