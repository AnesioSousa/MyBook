package controller.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import view.NewFXMain;

/**
 * FXML Controller class
 *
 * @author anesio
 */
public class ControllerTelaLogin implements Initializable {
    @FXML
    private Label cadastro;
    
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
