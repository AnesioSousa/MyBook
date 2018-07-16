package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Grafo implements IGraph{
    private boolean ehDirecionado;
    private Set<Vertice> vertices;
    private List<Aresta> arestas; // Acho que não precisa disso. Só se quiser saber todas as amizades dos usuários.
    
    public Grafo(boolean isDirected){
        ehDirecionado = isDirected;
        vertices = new HashSet();
        arestas = new ArrayList<>();
    }

    @Override
    public void addVertex(Object key) { // Criar Exception pra se o item à inserir já estiver inserido.
        Vertice v = new Vertice(key, ehDirecionado);
        if(!vertices.add(v)){
            throw new RuntimeException("Já existe um vértice que contém o dado!");
        }
    }

    @Override
    public Iterator vertices() {
        return vertices.iterator();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public void removeVertex(Object key) {
        Vertice aux = new Vertice(key, ehDirecionado);
        if(!vertices.remove(aux)){
            throw new RuntimeException("Não existe vértice que contenha o dado!");
        }
    }

    @Override
    public void addEdge(Vertice u, Vertice v, Object data/*, int peso*/) {  // Tem que testar isso!
        if(vertices.contains(u) && vertices.contains(v)){
            Aresta a = new Aresta(u, v, data);
            arestas.add(a);
            u.getArestasSaindo().put(v, a);
            v.getArestasEntrando().put(u, a);
        }else{
            throw new RuntimeException("Algumas das duas arestas não existe!");
        }
    }

    @Override
    public Aresta getEdge(Vertice u, Vertice v) {
        Aresta a = new Aresta(u,v, null);
        int pos = arestas.indexOf(a);
        
        if(arestas.indexOf(a) != -1){
            return arestas.get(pos);
        }
        // Botar uma exception aqui!
        return null;
    }

    @Override
    public Iterator edges() {
        return arestas.iterator();
    }

    @Override
    public int numEdges() {
        return arestas.size();
    }

    @Override
    public void removeEdge(Aresta a) { // Testar isso! e fazer pra Grafos Direcionados! Só está pra Grafos não direcionados!
        if(arestas.contains(a)){
            Vertice[] aux = a.getExtremidades();
            
            aux[0].getArestasSaindo().remove(aux[0]);
            aux[1].getArestasSaindo().remove(aux[0]);

            arestas.remove(a);
        }else{
            throw new RuntimeException("Não existe tal aresta!");
        }
    }

    @Override
    public Iterator outGoingEdges(Vertice v) {
        return v.getArestasSaindo().entrySet().iterator();
    }

    @Override
    public Iterator incomingEdges(Vertice v) {
        return v.getArestasEntrando().entrySet().iterator();
    }

    @Override
    public int outDegree(Vertice v) {
        return v.getArestasSaindo().size();
    }

    @Override
    public int inDegree(Vertice v) {
        return v.getArestasEntrando().size();
    }
    
}
