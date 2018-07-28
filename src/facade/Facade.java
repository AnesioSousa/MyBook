package facade;

import controller.System.ControllerSessao;
import controller.System.ControllerUser;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import util.trie.MapTrie;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public final class Facade {
    private MapTrie search;
    private ControllerUser ctrlUser;
    private ControllerSessao ctrlSession;
    private static Facade INSTANCE = null;

    private Facade() {
        this.ctrlUser = new ControllerUser();
        this.ctrlSession = new ControllerSessao();
        this.search = new MapTrie();
    }
    
    public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }
      
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        Usuario user = ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        search.insert(user.getNome(), user);
        
        return user;
    }
    
    // REVER ISSOOOO!!!
    public Usuario excluirUser(){
        encerrarSessão(); 
        Usuario user = ctrlUser.removerUser(ctrlSession.getActualUser());
        search.deleteKey(user.getNome());
        
        return user;
    }
    
    public Usuario iniciarSessao(String email, String password) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        return  ctrlSession.login(email, password);
    }
    
    // Fazer exceção para se não tiver usar logado!
    public void encerrarSessão(){
        ctrlSession.logout();
    }
    
    public boolean checkEmail(String email){
        return ControllerUser.obterUser(email) != null;
    }

    public ControllerUser getCtrlUser() {
        return ctrlUser;
    }

    public ControllerSessao getCtrlSession() {
        return ctrlSession;
    }
}
