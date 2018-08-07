package controller.View;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;
import model.exceptions.CampoVazioException;
import model.exceptions.UsuarioJaCadastradoException;

/**
 * FXML Controller class
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaCadastro implements Initializable, TelaControlada{
    private PrincipalController mainController;
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
    private TextField telTxtField;
    @FXML
    private RadioButton perfilSelect;
    @FXML
    private Button cadastroBtn;
    
    @FXML private Label lblPreenchidoNome;
    @FXML private Label lblPreenchidoEmail;
    @FXML private Label lblPreenchidoSenha;
    @FXML private Label lblMarcadoGenero;
    @FXML private Label lblEscolhidaData;
    @FXML private Label lblPreenchidoEnd;
    @FXML private Label lblPreenchidoTel;
    
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
        if (genMasculino.isSelected() || genFeminino.isSelected()) {
            if (genMasculino.isSelected()) {
                genero = "Masculino";
            } else {
                genero = "Feminino";
            }
        }

        try {
            mainController.criarUsuario(nomeTxtField.getText(), emailTxtField.getText(), senhaField.getText(), genero, formatter.format(aniversarioField.getValue()), endTxtField.getText(), telTxtField.getText(), perfilSelect.isSelected());
        } catch (CampoVazioException | NullPointerException ex) {
            alertaCampoVazio();
        } catch (UsuarioJaCadastradoException ex) {
            alertaUserJaCadastrado();
        }
        
    }

    private void alertaUserJaCadastrado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO!");
        alert.setHeaderText("Foi encontrado um registro que utiliza o email inserido!");
        alert.setContentText("Por favor, tente fazer login...");
        alert.showAndWait();
        limparCampos();
        goToScreen1();
    }
    
    private void alertaCampoVazio(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERRO!");
        alert.setHeaderText("Algum campo foi deixado em branco!");
        alert.setContentText("Por favor, insira todos os dados obrigatórios...");
        alert.showAndWait();
    }
    
    @FXML
    private void alterarAsteriscoNome(){
        nomeTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblPreenchidoNome.setVisible(true);
           }else{
               lblPreenchidoNome.setVisible(false);
           }
        });
    }
    
    @FXML
    private void alterarAsteriscoEmail(){
        emailTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblPreenchidoEmail.setVisible(true);
           }else{
               lblPreenchidoEmail.setVisible(false);
           }
        });
    }
    
    @FXML
    private void alterarAsteriscoSenha(){
        senhaField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblPreenchidoSenha.setVisible(true);
           }else{
               lblPreenchidoSenha.setVisible(false);
           }
        });
    }
    
    @FXML
    private void alterarAsteriscoGeneroMasculino(){
        genMasculino.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(isNowSelected){
                    lblMarcadoGenero.setVisible(false);
                }else{
                    lblMarcadoGenero.setVisible(true);
                }
            }
        });
    }
    
    @FXML
    private void alterarAsteriscoGeneroFeminino(){
        genFeminino.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                lblMarcadoGenero.setVisible(false);
            }else{
                lblMarcadoGenero.setVisible(true);
            }
        });
    }
    
    @FXML
    private void alterarAsteriscoNascimento(){
        aniversarioField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblEscolhidaData.setVisible(true);
           }else{
               lblEscolhidaData.setVisible(false);
           }
        });
    }
    
    @FXML
    private void alterarAsteriscoEndereco(){
        endTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblPreenchidoEnd.setVisible(true);
           }else{
               lblPreenchidoEnd.setVisible(false);
           }
        });
    }
    
    @FXML
    private void alterarAsteriscoTelefone(){
        telTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.isEmpty()){
               lblPreenchidoTel.setVisible(true);
           }else{
               lblPreenchidoTel.setVisible(false);
           }
        });
    }
    
    @FXML
    private void limparCampos() {
        nomeTxtField.setText("");
        emailTxtField.setText("");
        senhaField.setText("");
        genMasculino.setSelected(false);
        genFeminino.setSelected(false);
        aniversarioField.getEditor().setText("");
        endTxtField.setText("");
        telTxtField.setText("");
        perfilSelect.setSelected(false);
    }

    @FXML
    private void goToScreen1() {
       meuControlador.setScreen("login");
    }

    public TextField getNomeTxtField() {
        return nomeTxtField;
    }

    @Override
    public void setControlador(PrincipalController master) {
        mainController = master;
    }
}
