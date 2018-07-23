package view;

import java.util.HashMap;
import model.Usuario;
import util.Grafo;

public class view {  
    public static void main(String[] args) {
        
        /*Grafo g = new Grafo();
        
        Usuario a = new Usuario("Anésio Sousa", "anesios98@gmail.com", "123");
        Usuario b = new Usuario("Gustavo da Costa", "anesios98@gmail.com", "123");
        
        System.out.println(g.addVertex(a));
        System.out.println(g.addVertex(b));*/
        
        //Usuario c = new Usuario("Marcus de Jesus");
        //Usuario d = new Usuario("Michael Jackson");
        

        //g.addVertex(c);
        //g.addVertex(d);
        
        HashMap <String, Integer> map = new HashMap<>();
        
        map.put("Morango", 1);
        System.out.println("Tamanho : " + map.size());
        System.out.println(map.get("Morango"));
        
        map.put("Morango", 2);
        System.out.println("Tamanho : " + map.size());
        System.out.println(map.get("Morango"));
        map.put("Laranja", 2);
        System.out.println("Tamanho : " + map.size());
        
        
        
        
        /*g.addEdge(a, b, "Cu");
        g.addEdge(b, d, "Teste1");
        g.addEdge(a, c, "Teste2");*/
        
        
        /*System.out.println(g.numEdges());
        System.out.println(g.numVertices());
        
        System.out.println(g.getEdge(a, b);
        
        g.dfs(a);*/
        
    }
}
