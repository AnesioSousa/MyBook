package controller.View;

import facade.Facade;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.exceptions.UsuarioJaCadastradoException;
import view.ControladorDeTelas;
import view.Principal;
import view.TelaControlada;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaCadastro implements Initializable, TelaControlada{
    ControladorDeTelas meuControlador;
    
    Facade f = Facade.getInstance();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @FXML private TextField nomeTxtField;
    @FXML private TextField emailTxtField;
    @FXML private PasswordField senhaField;
    @FXML private RadioButton genMasculino;
    @FXML private RadioButton genFeminino;
    @FXML private DatePicker aniversarioField;
    @FXML private TextField endTxtField;
    @FXML private TextField telTextField;
    @FXML private RadioButton perfilSelect;
    
    /* 
    1-Não deixar cadastrar com itens em branco!
    2-Ver parada de setar o botao OK? visivel e botar uma imagem de certo ou errado nele se o email estiver sem cadastro ou com cadastro.
    
    */ 
    
    
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
    
    @FXML
    protected void cadastrar(ActionEvent e){
        aniversarioField.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
                if(t != null){
                    return formatter.format(t);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()){
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });
        String genero = null;
        if(genMasculino.isSelected() || genFeminino.isSelected()){ // Tem que ver isso aqui direito depois
            if(genMasculino.isSelected()){
                genero = "Masculino";
            }else{
                genero = "Feminino";
            }
        }
            
        
        try {
            f.registrarUser(nomeTxtField.getText(), emailTxtField.getText(), senhaField.getText(), genero, formatter.format(aniversarioField.getValue()), endTxtField.getText(), telTextField.getText(), perfilSelect.isSelected());
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex);
        }
        System.out.println("Voltando à área de login!");
        limparCampos(e);
        goToScreen1(e);
    }
    
    @FXML
    protected void limparCampos(ActionEvent e){
        nomeTxtField.setText("");
        emailTxtField.setText("");
        senhaField.setText("");
        genMasculino.setSelected(false);
        genFeminino.setSelected(false);
        aniversarioField.getEditor().setText("");
        endTxtField.setText("");
        telTextField.setText("");
        perfilSelect.setSelected(false);
    }

    @FXML
    private void goToScreen1(ActionEvent event){
       meuControlador.setScreen(Principal.screen1ID);
    }
    
}
