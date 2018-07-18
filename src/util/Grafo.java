package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Grafo implements IGraph{
    private Map<Object, Vertice> vertices;
    private List<Aresta> arestas; // Acho que não precisa disso. Só se quiser saber todas as amizades dos usuários.
    
    public Grafo(){
        vertices = new HashMap();
        arestas = new ArrayList<>();
    }

    @Override
    public void addVertex(Object key) { // Criar Exception pra se o item à inserir já estiver inserido.
        if(!vertices.containsKey(key)){
            vertices.put(key, new Vertice(key));
        }else{
            throw new RuntimeException("Já existe um vértice que contém o dado!");
        }
    }

    @Override
    public Iterator vertices() {
        return vertices.values().iterator();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public void removeVertex(Object key) { // Recebe um User e remove ele da rede social
        
        if(vertices.containsKey(key)){
            for(Aresta e: vertices.get(key).getAdjacencias().values()){
                removeEdge(e);
            }
        }else{
            throw new RuntimeException("Não existe vértice que contenha o dado!");
        }
    }
    
    @Override
    public void addEdge(Object u, Object v, Object data){ 
        if(vertices.containsKey(u) && vertices.containsKey(v)){
           Vertice aux1 = vertices.get(u);
           Vertice aux2 = vertices.get(v);
           
           if(getEdge(aux1,aux2)== null){
               Aresta a = new Aresta(aux1, aux2, data);
               arestas.add(a);
               vertices.get(aux1).getAdjacencias().put(aux2, a);
               vertices.get(aux2).getAdjacencias().put(aux1, a);
            }else{
                throw new RuntimeException("Já existe uma aresta que liga os vértices dados!");
            }
        }
        // Se não?
        
    }
    
    @Override
    public Object getEdge(Object u, Object v){
        // Esse método deve retornar o dado que armazena a aresta que liga os vértices de u e v
        return null;
    }
    
    private Aresta getEdge(Vertice u, Vertice v) {
        return vertices.get(u).getAdjacencias().get(v);
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
    @Override
    public void removeEdge(Object u, Object v){
        // Verificar antes a existência de u e v no mapa de vértices!
        Vertice aux1 = vertices.get(u);
        Vertice aux2 = vertices.get(v);
        
        if(getEdge(aux1,aux2) != null){
            Aresta a = vertices.get(u).getAdjacencias().get(vertices.get(v));
            removeEdge(a);
        }else{
            throw new RuntimeException("Não existe tal aresta!");
        }
    }
    
    private void removeEdge(Aresta a) {  // Remove a aresta recebida dos vértices adjacentes
        vertices.get(a.getOrigem()).getAdjacencias().remove(a.getDestino());
        vertices.get(a.getDestino()).getAdjacencias().remove(a.getOrigem());

        // Remove a aresta da lista de arestas
        arestas.remove(a);
    }

    @Override
    public Iterator edgesList(Object key) {
        return vertices.get(key).getAdjacencias().values().iterator();
    }
    
}
