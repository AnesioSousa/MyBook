package controller.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML private Label nome;
    @FXML private Button btnEdit;
    
    private PrincipalController mainController;
    private ControllerPalco meuControlador;
    
    /**
     * Initializes the controller class.
     * @param user
     */

    public void initialize(Usuario user) {
        meuUsuario = user;
        caminhoFoto = meuUsuario.getUrlImagemPerfil();
        nome.setText(meuUsuario.getNome());
        if(caminhoFoto != null)
            loadImage(caminhoFoto);
    }
        
    @FXML
    protected void setImage(MouseEvent e) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha uma foto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        foto.setImage(image);
        
        caminhoFoto = file.getCanonicalPath();
        saveImage(caminhoFoto);
    }
    
    private void saveImage(String caminho){
        meuUsuario.setUrlImagemPerfil(caminho);
    }
    
    private void loadImage(String caminho){
        foto.setImage(new Image(new File(caminho).toURI().toString()));
    }
    
    private void showEditBtn(){
        btnEdit.setVisible(true);
    }
    
    private void hideEditBtn(){
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
