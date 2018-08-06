package controller.View;

import java.util.*;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Pair;
import model.*;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;

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
        qtdDePaginas.set(-1);
        limparHistorico();
        limparPesquisa();
    }
        
    private void inicializarBotoes() { // Receber tamanho da lista e atualizar o avançar, caso não de certo.
        // Desativa o botão de voltar automaticamente caso não haja página atual ou a página atual seja a primeira.
        voltarBtn.disableProperty().bind(idPaginaAtual.lessThanOrEqualTo(0)); 
        // Desativa o botão de avançar automaticamente caso a página atual seja a última.
        avancarBtn.disableProperty().bind(qtdDePaginas.lessThanOrEqualTo(idPaginaAtual));
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
    public void deslogar(){
        mainController.deslogarUserAtual();
        limparNavegador();
        goToScreen1();
    }
        
    @FXML
    public void excluirConta(ActionEvent e){
        // Cria a caixa de diálogo customizada.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Confirme que é você!");
        dialog.setHeaderText("Digite os dados correspondentes a conta à ser excluída:");

        // Escolhe o ícone.
        dialog.setGraphic(new ImageView(this.getClass().getResource("/view/Imagens/login.png").toString()));

        // Escolhe os tipos dos botões.
        ButtonType deleteButtonType = new ButtonType("Excluir", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Cria os campos e as labels de user e senha
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField email = new TextField();
        email.setPromptText("Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Email:"), 0, 0);
        grid.add(email, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Habilita/Desabilita o botão de exclusão dependendo se o email e a senha foram digitados.
        Node deleteButton = dialog.getDialogPane().lookupButton(deleteButtonType);
        deleteButton.setDisable(true);

        // Faz a validação (usando a sintaxe lambda do Java 8).
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            deleteButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Pede foco no campo de email por padrão.
        Platform.runLater(() -> email.requestFocus());

        // Converte o resultado em um par email-senha quando o botão de exclusão é clicado.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return new Pair<>(email.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            try {
                mainController.excluirUsuario(usernamePassword.getKey(), usernamePassword.getValue());
                dialogoDeDadosInseridosCorretamente();
            } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
                dialogoDeDadosInseridosInvalidos();
                
            }
        });
    }
     
    private void dialogoDeDadosInseridosInvalidos(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Dados incorretos!");
        alert.setHeaderText("Por favor, faça login novamente, e tente fazer a exlusão!");
        alert.setContentText("Clique no botão OK ou feche essa janela para voltar à área de login...");

        alert.showAndWait();
        deslogar();
    }
    
    private void dialogoDeDadosInseridosCorretamente(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("É triste ver você partir...");
        alert.setHeaderText("Clique no botão OK ou feche essa janela para voltar à área de login:");
        alert.setContentText("Até uma próxima!");
        alert.setGraphic(new ImageView("/view/Imagens/cry.png"));
        alert.showAndWait();
        deslogar();
    }
    
    @FXML
    private void goToScreen1(){
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
