package controller.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
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
    
    private PrincipalController mainController;
    private ControllerPalco meuControlador;
    
    /**
     * Initializes the controller class.
     */

    public void initialize(Usuario user) {
        meuUsuario = user;
        caminhoFoto = meuUsuario.getUrlImagemPerfil();
        nome.setText(meuUsuario.getNome());
    }
    
    @Override
    public void setControladorDeTelas(ControllerPalco master){
        meuControlador = master;
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

    @Override
    public void setControlador(PrincipalController master) {
        mainController = master;
    }
    
}
