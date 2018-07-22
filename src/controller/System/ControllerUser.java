package controller.System;

import model.Usuario;
import util.Grafo;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    Grafo g = Grafo.getInstance();
    
    public void inserirUserNoGrafo(Usuario user){
        g.addVertex(user);
    }
    
    public void removerUserDoGrafo(Usuario user){
        g.removeVertex(user);
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
