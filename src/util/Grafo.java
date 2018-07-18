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
    private Set<Vertice> vertices;
    private List<Aresta> arestas; // Acho que não precisa disso. Só se quiser saber todas as amizades dos usuários.
    
    public Grafo(){
        vertices = new HashSet();
        arestas = new ArrayList<>();
    }

    @Override
    public void addVertex(Object key) { // Criar Exception pra se o item à inserir já estiver inserido.
        Vertice v = new Vertice(key);
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
    public void removeVertex(Object key) { // Recebe um User e remove ele da rede social
        Vertice vert = new Vertice(key);
        
        if(vertices.contains(vert)){
            for(Aresta e: vert.getAdjacencias().values()){
                removeEdge(e);
            }
            vertices.remove(vert);
        }else{
            throw new RuntimeException("Não existe vértice que contenha o dado!");
        }
    }
    
    // DEVE SER OBJECT INVÉS DE VÉRTICE. TIPO, CRIAR UMA AMIZADE ENTRE VÉRTICE USUÁRIO U E V.
    @Override
    public void addEdge(Vertice u, Vertice v, Object data) {
        if(getEdge(u,v) == null){
            Aresta a = new Aresta(u, v, data);
            arestas.add(a);
            u.getAdjacencias().put(v, a);
            v.getAdjacencias().put(u, a);  
        }else{
            throw new RuntimeException("Já existe uma aresta que liga os vértices dados!");
        }
    }

    @Override
    public Aresta getEdge(Vertice u, Vertice v) {
        return u.getAdjacencias().get(v);
    }

    @Override
    public Iterator edges() {
        return arestas.iterator();
    }

    @Override
    public int numEdges() {
        return arestas.size();
    }
    
    // CRIAR UMA REMOVE EDGE ENTRE DOIS VÉRTICES
    
    // tentar deixar isso privado!
    @Override
    public void removeEdge(Aresta a) { // Testar isso! e fazer pra Grafos Direcionados! Só está pra Grafos não direcionados!
        if(arestas.contains(a)){
            Vertice aux = a.getOrigem();
            Vertice aux2 = a.getDestino();
            if(aux != null && aux2 != null){
                aux.getAdjacencias().remove(aux2);
                aux2.getAdjacencias().remove(aux);
            }

            arestas.remove(a);
        }else{
            throw new RuntimeException("Não existe tal aresta!");
        }
    }

    @Override
    public Iterator edgesList(Vertice v) {
        return v.getAdjacencias().entrySet().iterator();
    }
    
}
