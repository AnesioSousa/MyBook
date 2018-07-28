package controller.View;

import facade.Facade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Usuario;
import view.Principal;

/**
 * FXML Controller class
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaNavegador {
    private Usuario user;
    
    Facade facade = Facade.getInstance();
    @FXML private TextField pesquisaTxtField;
    @FXML private Button perfilBtn;
    @FXML private Button solicitacoesBtn;
    @FXML private Button notificacoesBtn;
    @FXML private MenuButton contaMenu;
    @FXML private Button voltarBtn;
    @FXML private Button avancarBtn;
    @FXML private VBox contentPanel;
    @FXML private Label babel;
    
    private List<Parent> paginas = new ArrayList<>();   
    private final IntegerProperty idDePaginaAtual = new SimpleIntegerProperty(-1);
    
    // Esse método recebe um usuario para inicializar os dados do usuário logado.
    public void initData(Usuario usuario){
        user = usuario;
        babel.setText(user.getNome());
        // solicitações
        // notificações
    }
    
    /*@FXML
    public void loadProfile(ActionEvent e) throws IOException{
        
        Parent perfil = FXMLLoader.load(ControllerTelaNavegador.class.getClassLoader().getResource("view/Perfil.fxml"));
        paginas.addAll(Arrays.asList(perfil));
        System.out.println(paginas.size());
        
        /*FXMLLoader perfilLoader = new FXMLLoader(getClass().getResource("view/Perfil.fxml"));
        Parent perfil = perfilLoader.load();            
        ControllerTelaPerfil controller = perfilLoader.getController();
        controller.loadProfile(user);
        paginas.addAll(Arrays.asList(perfil));
        proximaPagina();
    }*/
    
    public void initialize() throws Exception{
        construirPaginas();
        inicializarBotoes();
        setConteudoInicial();
        System.out.println(facade.getUsuarioAtual());
    }
    
    private void inicializarBotoes() {
        // Desativa o botão de voltar automaticamente caso não haja página atual ou a página atual seja a primeira.
        voltarBtn.disableProperty().bind(idDePaginaAtual.lessThanOrEqualTo(0)); 
        
        // Desativa o botão de avançar automaticamente caso a página atual seja a última.
        avancarBtn.disableProperty().bind(idDePaginaAtual.greaterThanOrEqualTo(paginas.size()-1)); 
    }
        
    private void setConteudoInicial() {
        idDePaginaAtual.set(0);
        contentPanel.getChildren().add(paginas.get(idDePaginaAtual.get()));
    }
    
    private void construirPaginas() throws IOException {
        Parent perfil1 = FXMLLoader.load(ControllerTelaNavegador.class.getClassLoader().getResource("view/Perfil.fxml"));
        //Parent perfilteste1 = FXMLLoader.load(ControllerTelaNavegador.class.getClassLoader().getResource("view/PerfilTeste1.fxml"));
        
        paginas.addAll(Arrays.asList(perfil1/*, perfilteste1*/));
    }
    
    @FXML
    public void proximaPagina() {
        if(idDePaginaAtual.get() < (paginas.size()-1)){
            contentPanel.getChildren().remove(paginas.get(idDePaginaAtual.get())); // Remove da visualização a página atual.
            idDePaginaAtual.set(idDePaginaAtual.get()+1); // Move o indicador de página atual para a proxima página.
            contentPanel.getChildren().add(paginas.get(idDePaginaAtual.get())); // Adiciona a visualização a página que o indicador de página atual marca.
        }
    }
    
    @FXML
    public void paginaAnterior() {
        if(idDePaginaAtual.get() > 0){
            contentPanel.getChildren().remove(paginas.get(idDePaginaAtual.get()));
            idDePaginaAtual.set(idDePaginaAtual.get() -1);
            contentPanel.getChildren().add(paginas.get(idDePaginaAtual.get()));
        }
    }
    
    @FXML
    public void deslogar(ActionEvent e){
        facade.encerrarSessaoAtual();
        goToScreen1(e);
    }
    
    @FXML
    public void excluirConta(ActionEvent e){
        //f.excluirUser(); REVER ISSO!
        goToScreen1(e);
    }
    
    @FXML
    private void goToScreen1(ActionEvent event){
       Principal.changeScreen("login");
    }
}
