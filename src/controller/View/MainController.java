package controller.View;

import facade.Facade;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import view.Principal;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class MainController { // Tentar criar uns métodos estáticos aqui só pra ajudar
    private HashMap<Usuario, Parent> perfis;
    private ControllerPalco controllerPalco;
    private ControllerTelaNavegador controlNavegador;
    private ControllerTelaLogin controlLogin;
    private Facade facade = Facade.getInstance();
    
    private static MainController INSTANCE = null;

    private MainController() {
        controllerPalco = new ControllerPalco();
        perfis = new HashMap<>(); 
    }

    public static MainController getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MainController();
        }
        return INSTANCE;
    }
    
    // é melhor criar um stage pro navegador e esse cara controlar.
    // pq ai o login só pede pro master logar tal conta, e o master passa pro navegador  
    
    public Usuario criarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        Usuario user = facade.registrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        criarPerfilUser(user);
        
        return user;
    }
    
    public void criarPerfilUser(Usuario user){
        FXMLLoader perfilLoader = new FXMLLoader(getClass().getResource("/view/Perfil.fxml"));
        Parent perfil = null;            
        try {
            perfil = perfilLoader.load();
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo!");
        }
        ControllerTelaPerfil controller = perfilLoader.getController();
        controller.setControlador(controllerPalco);
        controller.initialize(user);
        perfis.put(user, perfil);
    }
    
    // A VERIFICAÇÃO PARTE DAQUIII!!!
    public Usuario logarUser(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        Usuario usuario = facade.iniciarSessao(email, senha);
       
        facade.setUsuarioAtual(usuario);
        abrirNavegacao(usuario);
        
        return usuario;
    }  
    
    public void abrirNavegacao(Usuario user){ 
        if(!controllerPalco.containsScreen("navegador")){ // SE NINGUEM LOGOU NENHUMA VEZ, SALVA A PÁGINA DE NAVEGAÇÃO E INICIALIZA O NAVEGADOR!
            controlNavegador = (ControllerTelaNavegador) controllerPalco.armazenarTela("navegador", "/view/Navegador.fxml");
        }
        controlNavegador.initialize(user, perfis);
        controllerPalco.setScreen("navegador");
    }
    

    public void abrirPerfil(Usuario user){  // SE FOR O MESMO USER LOGADO!
        controlNavegador.carregarPerfil(user);
    }

    
    /*public void iniciarPesquisa() throws IOException{
        FXMLLoader navegadorLoader = new FXMLLoader(getClass().getResource("/view/Navegador.fxml"));
        Parent navegador = navegadorLoader.load();
        //controlNavegador
    }*/

    public ControllerPalco getControllerPalco() {
        return controllerPalco;
    }

}
