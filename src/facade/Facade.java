package facade;

import controller.System.ControllerUser;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import util.SerializadorDeGrafos;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public final class Facade {
    
    private Usuario usuarioAtual;
    private ControllerUser ctrlUser;
    private SerializadorDeGrafos serializer;
    private static Facade INSTANCE = null;

    public Facade(){
        try {
            this.ctrlUser = new ControllerUser();
            this.serializer = new SerializadorDeGrafos(ctrlUser.getGrafo());
            this.ctrlUser.setGrafo(serializer.recuperar());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    /*public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }*/
      
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        Usuario user = ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        /*
        try {
            //pesquisa.insert(user.getNome(), user);
            serializer.gravar(ctrlUser.getGrafo());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        */
        return user;
        
    }
    
    // REVER ISSOOOO!!!
    public Usuario excluirUser(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        //encerrarSessão(); 
        Usuario user = ctrlUser.removerUser(email, senha);
        /*
        try {
            //pesquisa.deleteKey(user.getNome());
           // serializer.gravar(ctrlUser.getGrafo());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        */
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
        List<Usuario> ret = new LinkedList();
        Iterator<Usuario> users = ctrlUser.getListaDeUsuarios();
        while(users.hasNext()){
            Usuario u = users.next();
            if(u.getNome().equalsIgnoreCase(nome)){
                ret.add(u);
            }
        }
        return ret;
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
