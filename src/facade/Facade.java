package facade;

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
    private Usuario usuarioAtual;
    private MapTrie pesquisa;
    private ControllerUser ctrlUser;
    private static Facade INSTANCE = null;

    private Facade() {
        this.ctrlUser = new ControllerUser();
        this.pesquisa = new MapTrie();
    }
    
    public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }
      
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        Usuario user = ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        pesquisa.insert(user.getNome(), user);
        
        return user;
    }
    
    // REVER ISSOOOO!!!
    public Usuario excluirUser(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        //encerrarSessão(); 
        Usuario user = ctrlUser.removerUser(email, senha);
        pesquisa.deleteKey(user.getNome());
        
        return user;
    }
    
    public Usuario iniciarSessao(String email, String password) throws UsuarioNaoCadastradoException, SenhaIncorretaException{  
        usuarioAtual = ctrlUser.checarDados(email, password);
        return usuarioAtual;
    }
    
    /* Fazer exceção para se não tiver usar logado!
    public void encerrarSessão(){
        ctrlSession.logout();
    }*/
    
    public boolean checkEmail(String email){
        return ctrlUser.obterUser(email) != null;
    }

    public ControllerUser getCtrlUser() {
        return ctrlUser;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }    
}
