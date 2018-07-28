package controller.System;

import java.util.HashMap;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import java.util.Map;
import model.Usuario;
import util.Grafo;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    private Grafo grafo;
    private static Map<String, Usuario> emailMap;

    public ControllerUser() {
        grafo =  new Grafo();
        emailMap = new HashMap<>();
    }
    
    public Usuario cadastrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean perfilEhPublico) throws UsuarioJaCadastradoException{
        if(obterUser(email) == null){
            Usuario user = new Usuario(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);
            emailMap.put(email, user);
            grafo.addVertex(user);
            return user;
        }else{
            throw new UsuarioJaCadastradoException();
        }
    }

    // Tem que rever isso! esse método é basicamente o "autenticação" do facade!!
    public Usuario removerUser(Usuario user){
        grafo.removeVertex(user);
        emailMap.remove(user.getEmail(), user);
        
        return user;
    }

    /*public void adicionarAmizade(Usuario userA, Usuario userB){
        grafo.addEdge(userA, userB, null);
    }
    
    public void desfazerAmizade(Usuario userA, Usuario userB){
        grafo.removeEdge(userA, userB);
    }
    
    public void modificarVinculo(Usuario userA, Usuario userB){
        
    }*/

    public int getQuantidadeUsers() {
        return grafo.numVertices();
    }
        
    public static Usuario obterUser(String email){ 
        return emailMap.get(email);
    }
}
