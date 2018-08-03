package controller.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

/**
 * FXML Controller class
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaNavegador implements TelaControlada{
    private Usuario usuarioAtual;
    private ControllerPalco meuControlador;
    private MainController mainController;
    
    @FXML private Button perfilBtn;
    @FXML private Button botaoBusca;
    @FXML private Button solicitacoesBtn;
    @FXML private Button notificacoesBtn;
    @FXML private MenuButton contaMenu;
    @FXML private Button voltarBtn;
    @FXML private Button avancarBtn;
    @FXML private AnchorPane contentPanel;
    @FXML private ListView<Usuario> listaDeResultados;
    @FXML private TextField pesquisaTxtField;
    @FXML private Label lblLimpar;
    @FXML private Button btnAbrirPerfil;
    
    @FXML private Label label;
    
    private Usuario usuarioSelecionado;
    
    private Map<Usuario, Parent> perfis;
    private List<Parent> historico = new ArrayList();
    
    private final IntegerProperty idDePaginaAtual = new SimpleIntegerProperty(-1);
    
    // Esse método recebe um usuario para inicializar os dados do usuário logado.
    public void initialize(Usuario user, HashMap perfis){
        this.perfis = perfis;
        usuarioAtual = user;
        label.setText(usuarioAtual.getEmail());
        
        // CARREGOU AS FUNCIONALIDADES DO NAVEGADOR COM AS INFORMAÇÕES DO USER ATUAL
        // configurou botão home
        // solicitações
        // notificações
        
        carregarPerfilUserLogado(usuarioAtual); // AI DEPOIS FAZER ISSO!
        inicializarBotoes();
    }
    // TESTAR ISSO TUDO QUANDO A CARALHA DE AMIZADES ESTIVER FUNCIONAL!
        
    // (Armazeno previamente os perfis dos users, justamente pra não ter que pegar todos os atributos dele pra formar o perfil.)
    /* CARA COMO TU É BUUUUUUUUUUUUUUUUUUUUUUURRROOOOOOOOOOO
        Tu antes tinha um campo de perfil, certo? beleza, ele era um objeto
        perfil que tu criou. Só que tu deletou ele pq não viu mais sentido em ele existir, 
        ja que não faria nada com ele dai era só montar um perfil na interface como parent 
        e mostrar ele, certo? beleza, a parada aqui é que tu se debateu com o fato de que
        os perfis estão salvos NA MEMÓOOORIAAA, é o mesmo problema do obter user. Mas se 
        preocupa não, que eu te dou a solução:
            Trás de volta o campo perfil do usuário, mas salva ali um Parent que é o perfil dele.
        dai depois tu só exibe esse parent quando for solicitado para abrir o perfil desse cara.
       *(mas cai no problema de se eu tiver uma view que não seja javafx, não teria como usar, já que Parent é do javafx)
    */
    
    private Parent atualizarActualContent(Usuario user){ 
        Parent perfil = perfis.get(user); // Erro está aqui. Mesmo erro do obterUser.
        contentPanel.getChildren().add(perfil); // Aqui o java está avisando que o que eu to tentando botar no contentePanel, é null.
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
        //contentPanel.getChildren().clear();
        historico.clear();
        idDePaginaAtual.set(-1);
        contentPanel.getChildren().add(label); //AAQUUIIIIIIIIIIIIIIIIIIIIIIIIIIIII
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
    public void fazerPesquisa(){ // Tentar fazer com matcher depois!
        ObservableList<Usuario> data = FXCollections.observableArrayList();
        List<Usuario> aux = mainController.pesquisar(pesquisaTxtField.getText());
        
        if(aux.isEmpty()){
            //listaDeResultados.getItems().add("Nenhum resultado encontrado!"); Criar Label de aviso!
        }else{
            for (Usuario u : aux) {
                data.add(u);
            }
            listaDeResultados.setItems(data);
            listaDeResultados.getSelectionModel().selectedItemProperty().addListener(
                            (observable, oldValue, newValue) -> setUsuarioSelecionado(newValue));
        }
        
        /*listaDeResultados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println(listaDeResultados.getSelectionModel().getSelectedItem());*/
        
        btnAbrirPerfil.setVisible(true);
        btnAbrirPerfil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.abrirPerfil(usuarioSelecionado);
            }
        });
    }
    
    public void teste(){
        System.out.println("A");
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
    public void excluirConta(ActionEvent e){
        //f.excluirUser(); REVER ISSO!
        goToScreen1(e);
    }
     
    @FXML
    private void goToScreen1(ActionEvent e){
        meuControlador.setScreen("login");
    }
        
    @Override
    public void setControladorDeTelas(ControllerPalco master){
        meuControlador = master;
    }
    
    @FXML
    private void sublinharLabel(){
        lblLimpar.setUnderline(true);
    }
    
    @FXML
    private void desSublinharLabel(){
        lblLimpar.setUnderline(false);
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    @Override
    public void setControlador(MainController master) {
        mainController = master;
    }
}
