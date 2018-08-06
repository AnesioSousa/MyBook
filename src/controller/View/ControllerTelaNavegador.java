package controller.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.layout.VBox;
import model.Usuario;

/**
 * FXML Controller class
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerTelaNavegador implements TelaControlada{
    private Usuario usuarioAtual;
    private ControllerPalco meuControlador;
    private PrincipalController mainController;
    
    @FXML private Button perfilBtn;
    @FXML private Button botaoBusca;
    @FXML private Button solicitacoesBtn;
    @FXML private Button notificacoesBtn;
    @FXML private MenuButton contaMenu;
    @FXML private Button voltarBtn;
    @FXML private Button avancarBtn;
    @FXML private VBox contentPanel;
    @FXML private ListView<Usuario> listaDeResultados;
    @FXML private TextField pesquisaTxtField;
    @FXML private Label lblLimpar;
    @FXML private Button btnAbrirPerfil;
    
    @FXML private Label label;
    
    private Usuario usuarioSelecionado;
    
    private HashMap<Usuario, Parent> perfis;
    private List<Parent> historico = new ArrayList();
    
    private final IntegerProperty idPaginaAtual = new SimpleIntegerProperty(-1);
    private final IntegerProperty qtdDePaginas = new SimpleIntegerProperty(-1);
    
    // Esse método recebe um usuario para inicializar os dados do usuário logado.
    public void initialize(Usuario user, HashMap conteudos){
        this.perfis = conteudos;
        usuarioAtual = user;
        label.setText(usuarioAtual.getEmail());
        
        // CARREGOU AS FUNCIONALIDADES DO NAVEGADOR COM AS INFORMAÇÕES DO USER ATUAL
        // configurou botão home
        // solicitações
        // notificações
        
        inicializarConteudo(usuarioAtual); // AI DEPOIS FAZER ISSO!
        //construirTelasTeste();
        inicializarBotoes();        
    }
    // TESTAR ISSO TUDO QUANDO A CARALHA DE AMIZADES ESTIVER FUNCIONAL!
         
    private void inicializarConteudo(Usuario user){
        idPaginaAtual.set(0);
        Parent perfil = perfis.get(user);
        historico.add(perfil);
        qtdDePaginas.set(historico.size()-1);
        contentPanel.getChildren().add(historico.get(idPaginaAtual.get()));
    }
    
    private void construirTelasTeste(){
        Iterator<Parent> itr = perfis.values().iterator();
        while(itr.hasNext()){
            historico.addAll(Arrays.asList(itr.next()));
        }
        qtdDePaginas.set(historico.size());
    }
    
    public void carregarPerfil(Usuario user){ 
        Parent perfil = perfis.get(user); 
        historico.add(perfil);
        qtdDePaginas.set(historico.size()-1);
        proximaPagina();
    }
    
    private void limparHistorico(){
        historico.clear();
    }
    
    private void limparNavegador(){
        usuarioAtual = null;
        contentPanel.getChildren().clear();
        idPaginaAtual.set(-1);
        limparHistorico();
        limparPesquisa();
    }
        
    private void inicializarBotoes() { // Receber tamanho da lista e atualizar o avançar, caso não de certo.
        // Desativa o botão de voltar automaticamente caso não haja página atual ou a página atual seja a primeira.
        voltarBtn.disableProperty().bind(idPaginaAtual.lessThanOrEqualTo(0)); 
        // Desativa o botão de avançar automaticamente caso a página atual seja a última.
        avancarBtn.disableProperty().bind(qtdDePaginas.lessThanOrEqualTo(idPaginaAtual));//idPaginaAtual.greaterThanOrEqualTo(qtdDePaginas.get()-1));
    }

    @FXML
    public void proximaPagina() { // Vai percorrer páginas já abertas (HISTÓRICO).
        if(idPaginaAtual.get() < (historico.size()-1)){
            contentPanel.getChildren().remove(historico.get(idPaginaAtual.get())); // Remove da visualização a página atual.
            idPaginaAtual.set(idPaginaAtual.get()+1); // Move o indicador de página atual para a proxima página.
            contentPanel.getChildren().add(historico.get(idPaginaAtual.get())); // Adiciona a visualização a página que o indicador de página atual marca.
        }
    }
    
    @FXML
    public void paginaAnterior() { // Vai percorrer páginas já abertas (HISTÓRICO).
        if(idPaginaAtual.get() > 0){
            contentPanel.getChildren().remove(historico.get(idPaginaAtual.get()));
            idPaginaAtual.set(idPaginaAtual.get() -1);
            contentPanel.getChildren().add(historico.get(idPaginaAtual.get()));
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
                //mainController.abrirPerfil(usuarioSelecionado);
                carregarPerfil(usuarioSelecionado);
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
    public void setControlador(PrincipalController master) {
        mainController = master;
    }
}
