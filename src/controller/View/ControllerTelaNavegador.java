package controller.View;

import facade.Facade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import model.Usuario;
import view.Principal;

/**
 * FXML Controller class
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaNavegador {
    private Usuario usuario;
    private MasterController meuControlador;
    
    Facade facade = Facade.getInstance();
    @FXML private Button perfilBtn;
    @FXML private Button botaoBusca;
    @FXML private Button solicitacoesBtn;
    @FXML private Button notificacoesBtn;
    @FXML private MenuButton contaMenu;
    @FXML private Button voltarBtn;
    @FXML private Button avancarBtn;
    @FXML private AnchorPane contentPanel;
    private Parent conteudoAtual;
    
    @FXML private Label label;
    
    // private List<Parent> paginas;  Tem que ser uma HashTable. Se não, quando eu quiser abrir o usuário "Juninho", eu vou ter pesquisar
    // nessa lista onde que está o perfil dele.
    
    private Map<Usuario, Parent> perfis;
    private List<Parent> historico = new ArrayList();
    
    private final IntegerProperty idDePaginaAtual = new SimpleIntegerProperty(-1);
    
    // Esse método recebe um usuario para inicializar os dados do usuário logado.
    public void initialize(Usuario user, HashMap perfis){
        this.perfis = perfis;
        usuario = user;
        label.setText(usuario.getNome());
        
        // CARREGOU AS FUNCIONALIDADES DO NAVEGADOR COM AS INFORMAÇÕES DO USER ATUAL
        // configurou botão home
        // solicitações
        // notificações
        
        carregarPerfil(usuario); // AI DEPOIS FAZER ISSO!
        inicializarBotoes();
    }

    
    public void carregarPerfil(Usuario user){
        Parent perfil = perfis.get(user);
        contentPanel.getChildren().add(perfil);
        historico.addAll(Arrays.asList(perfil)); // Ver depois se o all é necessário!
        idDePaginaAtual.set(idDePaginaAtual.get()+1); // HISTORICO
        conteudoAtual = contentPanel;
    }
    
    public void limparHistorico(){
        historico.clear();
    }
    
    private void inicializarBotoes() {
        // Desativa o botão de voltar automaticamente caso não haja página atual ou a página atual seja a primeira.
        voltarBtn.disableProperty().bind(idDePaginaAtual.lessThanOrEqualTo(0)); 
        // Desativa o botão de avançar automaticamente caso a página atual seja a última.
        avancarBtn.disableProperty().bind(idDePaginaAtual.greaterThanOrEqualTo(historico.size()-1)); 
    }

    @FXML
    public void proximaPagina() { // Vai percorrer páginas já abertas (HISTÓRICO).
        if(idDePaginaAtual.get() < (historico.size()-1)){
            contentPanel.getChildren().remove(historico.get(idDePaginaAtual.get())); // Remove da visualização a página atual.
            idDePaginaAtual.set(idDePaginaAtual.get()+1); // Move o indicador de página atual para a proxima página.
            contentPanel.getChildren().add(historico.get(idDePaginaAtual.get())); // Adiciona a visualização a página que o indicador de página atual marca.
            conteudoAtual = contentPanel;
        }
    }
    
    @FXML
    public void paginaAnterior() { // Vai percorrer páginas já abertas (HISTÓRICO).
        if(idDePaginaAtual.get() > 0){
            contentPanel.getChildren().remove(historico.get(idDePaginaAtual.get()));
            idDePaginaAtual.set(idDePaginaAtual.get() -1);
            contentPanel.getChildren().add(historico.get(idDePaginaAtual.get()));
            conteudoAtual = contentPanel;
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
    private void goToScreen1(ActionEvent e){
       Principal.changeScreen("login");
    }
    
    // CHAMA A PÁGINA DE BUSCA
    @FXML
    private void irParaPaginaDeBusca(ActionEvent e){ 
        //meuControlador.
    }
        
    public void setControlador(MasterController master){
        meuControlador = master;
    }
}
