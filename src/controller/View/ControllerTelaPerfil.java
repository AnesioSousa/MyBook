package controller.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class ControllerTelaPerfil implements TelaControlada{
    private Usuario meuUsuario;
    private String caminhoFoto;
    @FXML private ImageView foto;
    @FXML private Button btnEdit;
    @FXML private ListView sobreView;
    
    private PrincipalController mainController;
    private ControllerPalco meuControlador;
    
    /**
     * Initializes the controller class.
     * @param user
     */

    public void initialize(Usuario user) {
        meuUsuario = user;
        caminhoFoto = meuUsuario.getUrlImagemPerfil();
        loadImage(caminhoFoto);
        loadInfo(meuUsuario);
    }
        
    @FXML
    public void setImage(ActionEvent e) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha uma foto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(null);
        
        if(file != null){
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            foto.setImage(image);
            caminhoFoto = file.getCanonicalPath();
            saveImage(meuUsuario, caminhoFoto);
        }
        
    }
    
    private void loadInfo(Usuario user){
        sobreView.getItems().addAll("Nome: "+user.getNome(),"Email: "+user.getEmail(),"Gênero: "+user.getGenero(), 
        "Data de nascimento: "+user.getNascimento(),"Endereço: "+user.getEndereco(), "Telefone: "+user.getTelefone());
    }
    
    private void saveImage(Usuario user, String caminho){
        user.setUrlImagemPerfil(caminho);
    }
    
    private void loadImage(String caminho){
        if(caminho != null){
            foto.setImage(new Image(new File(caminho).toURI().toString()));
        }
    }
    
    @FXML
    public void showEditBtn(){
        btnEdit.setVisible(true);
    }
    
    @FXML
    public void hideEditBtn(){
        btnEdit.setVisible(false);
    }
    
    public Usuario getMeuUsuario() {
        return meuUsuario;
    }

    @Override
    public void setControlador(PrincipalController master) {
        mainController = master;
    }
  
    @Override
    public void setControladorDeTelas(ControllerPalco master){
        meuControlador = master;
    }
    
}
