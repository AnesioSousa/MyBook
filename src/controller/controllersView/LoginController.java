package controller.controllersView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.NewFXMain;

/**
 * FXML Controller class
 *
 * @author anesio
 */
public class LoginController implements Initializable {

    @FXML
    protected void btNovoAction(ActionEvent e){
        System.out.println("Testando!");
        NewFXMain.changeScreen("cadastro");
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
