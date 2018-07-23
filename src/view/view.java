package view;

import facade.Facade;
import model.Usuario;
import util.Grafo;

public class view {  
    public static void main(String[] args) {
        Facade f = Facade.getInstance();
        
        /*Usuario u = f.registrarUser("Anésio", "Mrsplinter00", "teste123", "anesios98@gmail.com", "masculino", "05/02/98", "Rua angico n11", "34885210", true);
        Usuario j = f.registrarUser("Anésio", "Mrsplinter00", "teste123", "anesios98@gmail.com", "masculino", "05/02/98", "Rua angico n11", "34885210", true);
        
        System.out.println(u);
        System.out.println(j);*/
        
        Grafo g = new Grafo();
        
        Usuario a = new Usuario("Anésio Sousa", "mrsplinter00", "123");
        Usuario b = new Usuario("Gustavo da Costa", "guCosta", "4321");
        
        //Usuario c = new Usuario("Marcus de Jesus");
        //Usuario d = new Usuario("Michael Jackson");
        
        System.out.println(g.addVertex(a));
        System.out.println(g.addVertex(b));
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
