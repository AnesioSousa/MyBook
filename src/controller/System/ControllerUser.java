package controller.System;

import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import java.util.Iterator;
import java.util.LinkedList;
import model.Usuario;
import util.Grafo;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    LinkedList<Usuario> users; // Talvez nem precise usar isso.
    Grafo grafo;

    public ControllerUser() {
        users = new LinkedList<>();
        grafo =  new Grafo();
    }
    
    public Usuario cadastrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean perfilEhPublico) throws UsuarioJaCadastradoException{
        if(obterUser(email) == null){
            Usuario user = new Usuario(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);
            users.add(user);
            grafo.addVertex(user);
            return user;
        }else{
            throw new UsuarioJaCadastradoException();
        }
    }
    
    public Usuario removerUser(String email, String password) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        Usuario u = obterUser(email);
        if(u != null){
            if(!u.getPassword().equals(password)){
                throw new SenhaIncorretaException();
            }else{
                grafo.removeVertex(u);
                users.remove(u);
                return u;
            }
        }else{
            throw new UsuarioNaoCadastradoException();
        }
    }
    
    public Usuario obterUser(String email){
        Iterator<Usuario> itr = users.iterator();
        
        while(itr.hasNext()){
            Usuario u = itr.next();
            if(u.getEmail().equals(email)){ 
                return u;
            }
        }
        return null;
    }
    
    /*public void adicionarAmizade(Usuario userA, Usuario userB){
        grafo.addEdge(userA, userB, null);
    }
    
    public void desfazerAmizade(Usuario userA, Usuario userB){
        grafo.removeEdge(userA, userB);
    }
    
    public void modificarVinculo(Usuario userA, Usuario userB){
        
    }*/

    public LinkedList<Usuario> getUsers() {
        return users;
    }
    
}
