package view;

import controller.System.ControllerUser;
import model.exceptions.UsuarioJaCadastradoException;
import model.Usuario;
import util.Grafo;

public class view {  
    public static void main(String[] args) {
        ControllerUser ctrl = new ControllerUser();
        
        Grafo g = new Grafo();
        
        Usuario a = null;
        try {
            a = ctrl.cadastrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "17/11/2015", "Rua angico n11", "(75) 99191-6636", true);
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex);
        }
        Usuario b = null;
        try {
            b = ctrl.cadastrarUser("Gustavo da Costa", "anesios98@gmail.com", "321", "Bixa loca", "12/01/2014", "Rua pau brasil n431", "(75) 98205-5469", false);
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println(a);
        //System.out.println(b);
        
        //Usuario c = new Usuario("Marcus de Jesus");
        //Usuario d = new Usuario("Michael Jackson");
        

        //g.addVertex(c);
        //g.addVertex(d);
        
        /*g.addEdge(a, b, "Cu");
        g.addEdge(b, d, "Teste1");
        g.addEdge(a, c, "Teste2");*/
        
        
        /*System.out.println(g.numEdges());
        System.out.println(g.numVertices());
        
        System.out.println(g.getEdge(a, b);
        
        g.dfs(a);*/
        
    }
}
