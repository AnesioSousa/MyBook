package facade;

import controller.System.ControllerUser;
import java.util.List;
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

    public void encerrarSessaoAtual(){
        usuarioAtual = null;
    }
    
    public List buscarUser(String nome){
        return pesquisa.getKeySuggestions(nome);
    }
    
    public boolean checkEmail(String email){
        return ctrlUser.obterUser(email) != null;
    }

    public ControllerUser getCtrlUser() {
        return ctrlUser;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }    
}
