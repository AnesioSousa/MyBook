package controller.System;

import exceptions.SenhaIncorretaException;
import exceptions.UsuarioJaCadastradoException;
import exceptions.UsuarioNaoCadastradoException;
import java.util.Iterator;
import java.util.LinkedList;
import model.Usuario;
import util.Grafo;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    LinkedList<Usuario> users;
    Grafo grafo;

    public ControllerUser() {
        users = new LinkedList<>();
        grafo =  new Grafo();
    }
    
    public Usuario cadastrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        if(obterUser(email) == null){
            Usuario user = new Usuario(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
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
    
    private Usuario obterUser(String email){
        Iterator<Usuario> itr = users.iterator();
        
        while(itr.hasNext()){
            if(itr.next().getEmail().equals(email)){
                return itr.next();
            }
        }
        return null;
    }
    
    public void adicionarAmizade(Usuario userA, Usuario userB){
        grafo.addEdge(userA, userB, null);
    }
    
    public void desfazerAmizade(Usuario userA, Usuario userB){
        grafo.removeEdge(userA, userB);
    }
    
    public void modificarVinculo(Usuario userA, Usuario userB){
        
    }
}
