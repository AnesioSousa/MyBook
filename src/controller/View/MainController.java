package controller.View;

import facade.Facade;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class MainController { // Tentar criar uns métodos estáticos aqui só pra ajudar

    private HashMap<Usuario, Parent> perfis;
    private ControllerPalco controllerPalco;
    private ControllerTelaNavegador controlNavegador;
    private ControllerTelaLogin controlLogin;
    private Facade facade = new Facade();


    public MainController() {
        controllerPalco = new ControllerPalco();
        controllerPalco.setControlador(this);
        perfis = new HashMap<>();
        initPrimaryScenes();
        updateProfiles();
    }
  
    public Usuario criarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException {
        Usuario user = facade.registrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        criarPerfilUser(user);

        return user;
    }
    
    private void initPrimaryScenes(){
        controllerPalco.armazenarTela("login", "/view/Login.fxml");
        controllerPalco.armazenarTela("cadastro", "/view/Cadastro.fxml");
    }

    public void criarPerfilUser(Usuario user) {
        FXMLLoader perfilLoader = new FXMLLoader(getClass().getResource("/view/Perfil.fxml"));
        Parent perfil = null;
        try {
            perfil = perfilLoader.load();
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo fxml de perfil!");
        }
        ControllerTelaPerfil controller = perfilLoader.getController();
        controller.setControladorDeTelas(controllerPalco);
        controller.setControlador(this);
        controller.initialize(user);
        perfis.put(user, perfil);
    }

    public Usuario logarUser(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException {
        Usuario usuario = facade.iniciarSessao(email, senha);

        facade.setUsuarioAtual(usuario);
        abrirNavegacao(usuario);

        return usuario;
    }

    public void deslogarUserAtual() {
        facade.encerrarSessaoAtual();
    }

    public void abrirNavegacao(Usuario user) {
        if (!controllerPalco.containsScreen("navegador")) { // SE NINGUEM LOGOU NENHUMA VEZ, SALVA A PÁGINA DE NAVEGAÇÃO E INICIALIZA O NAVEGADOR!
            controlNavegador = (ControllerTelaNavegador) controllerPalco.armazenarTela("navegador", "/view/Navegador.fxml");
        }
        controlNavegador.initialize(user, perfis);
        controllerPalco.setScreen("navegador");
    }

    public void abrirPerfil(Usuario user) {  // SE FOR O MESMO USER LOGADO!
        controlNavegador.carregarPerfil(user);
    }
    
    public List pesquisar(String nome){
        return facade.buscarUser(nome);
    }

    public final void updateProfiles(){
        Iterator<Usuario> users = facade.listarUsuarios();
        
        while(users.hasNext()){
            criarPerfilUser(users.next());
        }
    }
    public ControllerPalco getControllerPalco() {
        return controllerPalco;
    }
    
    public boolean setScreen(String name){
        return controllerPalco.setScreen(name);
    }
    
    public final void updateDataBase(){
        facade.salvarAlteracoesGerais();
    }
}
