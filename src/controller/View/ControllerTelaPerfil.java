package controller.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ControllerTelaPerfil {
    private Usuario meuUsuario;
    private String caminhoFoto;
    private ImageView foto;
    
    /**
     * Initializes the controller class.
     */

    public void initialize(Usuario user) {
        meuUsuario = user;
        caminhoFoto = meuUsuario.getUrlImagemPerfil();
        
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
        
        System.out.println(file.getAbsolutePath());
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        foto.setImage(image);
        
        caminhoFoto = file.getAbsolutePath();
    }

    public Usuario getMeuUsuario() {
        return meuUsuario;
    }
    
}
