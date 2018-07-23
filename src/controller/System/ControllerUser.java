package controller.System;

import java.util.Iterator;
import model.Usuario;
import util.Grafo;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    Grafo g = new Grafo();
    
    public Usuario cadastrarUser(String nome, String login, String password, String email, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil){
        Usuario user = new Usuario(nome, password, email, genero, nascimento, endereco, telefone, estadoPerfil);
        
        if(!g.containsVertex(user)){
            g.addVertex(user);
            return user;
        }

        return null;
    }
    
    public Usuario removerUser(Usuario user){
        return (Usuario) g.removeVertex(user);
    }
    
    public Usuario obterUser(String email){
        Iterator<Usuario> itr = g.keySet();
        
        while(itr.hasNext()){
            if(itr.next().getEmail().equals(email)){
                return itr.next();
            }
        }
        
        return null;
    }
    
    public void adicionarAmizade(Usuario userA, Usuario userB){
        g.addEdge(userA, userB, null);
    }
    
    public void desfazerAmizade(Usuario userA, Usuario userB){
        g.removeEdge(userA, userB);
    }
    
    public void modificarVinculo(Usuario userA, Usuario userB){
        
    }
}
