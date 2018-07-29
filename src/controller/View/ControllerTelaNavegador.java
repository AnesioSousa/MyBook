package controller.View;

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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Usuario;
import view.Principal;

/**
 * FXML Controller class
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaNavegador implements TelaControlada{
    private Usuario usuarioAtual;
    private ControllerPalco meuControlador;
    private MainController mainController = MainController.getInstance();
    
    @FXML private Button perfilBtn;
    @FXML private Button botaoBusca;
    @FXML private Button solicitacoesBtn;
    @FXML private Button notificacoesBtn;
    @FXML private MenuButton contaMenu;
    @FXML private Button voltarBtn;
    @FXML private Button avancarBtn;
    @FXML private AnchorPane contentPanel;
    @FXML private ListView listaDeResultados;
    @FXML private TextField pesquisaTxtField;
    
    @FXML private Label label;
        
    private Map<Usuario, Parent> perfis;
    private List<Parent> historico = new ArrayList();
    
    private final IntegerProperty idDePaginaAtual = new SimpleIntegerProperty(-1);
    
    // Esse método recebe um usuario para inicializar os dados do usuário logado.
    public void initialize(Usuario user, HashMap perfis){
        this.perfis = perfis;
        usuarioAtual = user;
        label.setText(usuarioAtual.getNome());
        
        // CARREGOU AS FUNCIONALIDADES DO NAVEGADOR COM AS INFORMAÇÕES DO USER ATUAL
        // configurou botão home
        // solicitações
        // notificações
        
        carregarPerfilUserLogado(usuarioAtual); // AI DEPOIS FAZER ISSO!
        inicializarBotoes();
    }
    // TESTAR ISSO TUDO QUANDO A CARALHA DE AMIZADES ESTIVER FUNCIONAL!
    
    private Parent atualizarActualContent(Usuario user){
        Parent perfil = perfis.get(user);
        contentPanel.getChildren().add(perfil);
        historico.addAll(Arrays.asList(perfil)); // Ver depois se o all é necessário!
        idDePaginaAtual.set(idDePaginaAtual.get()+1); // HISTORICO
        
        return perfil;
    }
    
    private void carregarPerfilUserLogado(Usuario userAtual){
        atualizarActualContent(userAtual);
    }

    public void carregarPerfil(Usuario user){ 
        contentPanel.getChildren().remove(historico.get(idDePaginaAtual.get()));
        atualizarActualContent(user);
    }
    
    private void limparNavegador(){
        usuarioAtual = null;
        contentPanel.getChildren().clear();
        historico.clear();
        idDePaginaAtual.set(-1);
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
            //conteudoAtual = contentPanel;
        }
    }
    
    @FXML
    public void paginaAnterior() { // Vai percorrer páginas já abertas (HISTÓRICO).
        if(idDePaginaAtual.get() > 0){
            contentPanel.getChildren().remove(historico.get(idDePaginaAtual.get()));
            idDePaginaAtual.set(idDePaginaAtual.get() -1);
            contentPanel.getChildren().add(historico.get(idDePaginaAtual.get()));
            //conteudoAtual = contentPanel;
        }
    }
    
    @FXML
    public void fazerPesquisa(){
        List aux = mainController.pesquisar(pesquisaTxtField.getText());
        
        if(aux.isEmpty()){
            listaDeResultados.getItems().add("Nenhum resultado encontrado!");
        }else{
            for (Object u : aux) {
                listaDeResultados.getItems().add(u);
            }
        }
        trancarBotaoPesquisa();
    }
    
    @FXML
    public void limparPesquisa(){
        pesquisaTxtField.setText("");
        listaDeResultados.getItems().clear();
    }
    
    @FXML
    public void deslogar(ActionEvent e){
        mainController.deslogarUserAtual();
        limparNavegador();
        goToScreen1(e);
    }
    
    @FXML
    public void trancarBotaoPesquisa(){
        botaoBusca.setDisable(true);
    }
    
    @FXML
    public void destrancarBotaoPesquisa(){
        botaoBusca.setDisable(false);
    }
    
    @FXML
    public void excluirConta(ActionEvent e){
        //f.excluirUser(); REVER ISSO!
        goToScreen1(e);
    }
    
    @FXML
    private void goToScreen1(ActionEvent e){
        meuControlador.setScreen(Principal.screen1ID);
    }
        
    @Override
    public void setControlador(ControllerPalco master){
        meuControlador = master;
    }
}
