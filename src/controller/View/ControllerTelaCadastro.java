package controller.View;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.exceptions.UsuarioJaCadastradoException;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaCadastro implements Initializable, TelaControlada{
    private MainController mainController;
    private ControllerPalco meuControlador;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @FXML
    private TextField nomeTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private RadioButton genMasculino;
    @FXML
    private RadioButton genFeminino;
    @FXML
    private DatePicker aniversarioField;
    @FXML
    private TextField endTxtField;
    @FXML
    private TextField telTextField;
    @FXML
    private RadioButton perfilSelect;
    @FXML
    private Button cadastroBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void setControladorDeTelas(ControllerPalco master){
        meuControlador = master;
    }
    
    // ESTA TRATANDO ERRO DE USUÁRIO JÁ CADASTRADO?
    
    @FXML
    public void cadastrar(ActionEvent e) throws IOException {
        aniversarioField.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
                if (t != null) {
                    return formatter.format(t);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });
        String genero = null;
        if (genMasculino.isSelected() || genFeminino.isSelected()) { // Tem que ver isso aqui direito depois
            if (genMasculino.isSelected()) {
                genero = "Masculino";
            } else {
                genero = "Feminino";
            }
        }

        try {
            mainController.criarUser(nomeTxtField.getText(), emailTxtField.getText(), senhaField.getText(), genero, formatter.format(aniversarioField.getValue()), endTxtField.getText(), telTextField.getText(), perfilSelect.isSelected());
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex);
        }
        System.out.println("Voltando à área de login!");
        limparCampos(e);
        goToScreen1(e);
    }

    @FXML
    protected void limparCampos(ActionEvent e) {
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
    private void goToScreen1(ActionEvent event) {
       meuControlador.setScreen("login");
    }

    public TextField getNomeTxtField() {
        return nomeTxtField;
    }

    @Override
    public void setControlador(MainController master) {
        mainController = master;
    }
}
