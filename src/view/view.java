package view;

import model.User;
import util.Grafo;

public class view {  
    public static void main(String[] args) {
        Grafo g = new Grafo();
        User a = new User("Anésio Sousa");
        User b = new User("Gustavo da Costa");
        User c = new User("Marcus de Jesus");
        User d = new User("Michael Jackson");
        
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        
        g.addEdge(a, b, "Cu");
        g.addEdge(b, d, "Teste1");
        g.addEdge(a, c, "Teste2");
        
        
        /*System.out.println(g.numEdges());
        System.out.println(g.numVertices());
        
        System.out.println(g.getEdge(a, b);*/
        
        //g.dfs(a);
    }
}
