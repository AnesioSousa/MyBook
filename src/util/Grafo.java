package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por criar objetos que são capazes de armazenar referencias de outros objetos e criar relações entre eles.
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public final class Grafo implements Serializable{
    
    private Map<Object, Vertice> vertices;
    private List<Aresta> arestas; 
    
    /**
     * Construtor da classe Grafo.
     * Aqui são inicializados os vértices e as arestas.
     */
    public Grafo(){
        vertices = new HashMap<>();
        arestas = new ArrayList<>();
    }
    
    /**
     * Recebe um objeto, o envelopa em um vértice e adiciona esse vértice à coleção.
     * @param key objeto à ser guardado.
     * @return objeto que foi envelopado.
     */
    public Object addVertex(Object key) {
        vertices.put(key, new Vertice(key));
        return key;
    }

    /**
     * Recebe um objeto, pega o vértice que envelopa esse objeto, remove todas as arestas que apontam para ele, 
     * e finalmente o remove da coleção.
     * @param key objeto à ser removido.
     * @return objeto que foi removido da coleção.
     */
    public Object removeVertex(Object key) { // Rever isso!
        Vertice v = vertices.get(key);
        if(v.getAdjacencias() != null || !v.getAdjacencias().isEmpty()){
            for (Aresta e : v.getAdjacencias().values()) {
                removeEdge(e);
            }
            vertices.remove(key);
        }
        return key;
    }

    /**
     * Retorna um iterador para a lista de vértices desse grafo.
     * @return iterador
     */
    public Iterator vertices() {
        return vertices.values().iterator();
    }

    /**
     * Retorna uma variavel int contendo a quantidade de vértices armazenados na coleção.
     * @return inteiro
     */
    public int numVertices() {
        return vertices.size();
    }
    
    /**
     * Retorna um iterador para o conjunto de objetos que esse grafo guarda em seus vértices.
     * @return iterador
     */
    public Iterator getKeySet(){
        return vertices.keySet().iterator();
    }

    /**
     * Remove todos os vértices desse grafo.
     */
    public void removeAllVertex(){
        vertices.clear();
    }

    /**
     * Adiciona uma aresta que liga dois vértices.
     * Recebe dois vértices e um objeto. Então pega os vértices que guardam
     * esses objetos e verifica se já existe uma aresta que os liga. Se não
     * existe, então cria uma aresta que liga os vértices, adiciona essa aresta
     * a lista de arestas do grafo e cria a relação nos mapas de adjacencias de
     * cada vértice.
     * @param u objeto que é envelopado por um vértice.
     * @param v objeto que é envelopado por um vértice.
     * @param data dado que ficara armazenado na aresta criada caso não exista uma. 
     */
    public void addEdge(Object u, Object v, Object data){ 
        if(vertices.containsKey(u) && vertices.containsKey(v)){
           Vertice aux1 = vertices.get(u);
           Vertice aux2 = vertices.get(v);
           
           if(getEdge(aux1,aux2)== null){
               Aresta a = new Aresta(aux1, aux2, data);
               arestas.add(a);
               aux1.getAdjacencias().put(aux2, a);
               aux2.getAdjacencias().put(aux1, a);
            }else{
                throw new RuntimeException("Já existe uma aresta que liga os vértices dados!");
            }
        }else{
            throw new RuntimeException("Algum dos objetos não pertence à coleção de vértices!");
        }
    }
    
    // Esse método retorna o dado que armazena a aresta que liga os vértices de u e v

    /**
     * Retorna o dado que armazena a aresta que liga os vértices u e v recebidos.
     * @param u objeto que é envelopado por um vértice.
     * @param v objeto que é envelopado por um vértice.
     * @return dado que a aresta entre u e v armazena.
     */
    public Object getEdge(Object u, Object v){
        return vertices.get(u).getAdjacencias().get(vertices.get(v)).getDado();
    }
    
    /**
     * Método privado que auxilia nas funções do grafo.
     * Retorna uma aresta que liga dois vértices.
     * @param u um vertice qualquer
     * @param v um vertice qualquer
     * @return aresta
     */
    private Aresta getEdge(Vertice u, Vertice v) {
        return u.getAdjacencias().get(v);
    }
    
    /**
     * Retorna o iterador para a lista de arestas desse grafo.
     * @return iterador
     */
    public Iterator edges() {
        return arestas.iterator();
    }

    /**
     * Retorna um inteiro com a quantidade de arestas que esse grafo possui.
     * @return inteiro
     */
    public int numEdges() {
        return arestas.size();
    }
    
    /**
     * Recebe um objeto, pega o vértice que envelopa esse objeto e retorna uma lista de objetos que os vértices adjacentes à este guarda. 
     * @param u objeto que é envelopado por um vértice.
     * @return lista de dados
     */
    public List getAdjacentsData(Object u){
        Vertice v = vertices.get(u);
        return v.getDadosDeAdjacencias();
    }
    
    /**
     * Método de remoção de arestas.
     * Recebe dois objetos, pega os vértices que envelopam esses objetos e 
     * por fim retira da coleção de arestas a aresta que liga ambos vértices.
     * @param u objeto que é envelopado por um vértice.
     * @param v objeto que é envelopado por um vértice.
     */
    public void removeEdge(Object u, Object v){
        Vertice aux1 = vertices.get(u);
        Vertice aux2 = vertices.get(v);
        
        if(getEdge(aux1,aux2) != null){
            Aresta a = vertices.get(u).getAdjacencias().get(vertices.get(v));
            removeEdge(a);
        }else{
            throw new RuntimeException("Não existe tal aresta!");
        }
    }
    
    /**
     * Método privado que auxilia na remoção de arestas.
     * Pega o vertice origem e o vértice destino dessa aresta, e
     * remove as ligações entre esses dois vértices.
     * @param a aresta à ser removida.
     */
    private void removeEdge(Aresta a) {  // Remove a aresta recebida dos vértices adjacentes
        a.getOrigem().getAdjacencias().remove(a.getDestino());
        a.getDestino().getAdjacencias().remove(a.getOrigem());

        // Remove a aresta da lista de arestas
        arestas.remove(a);
    }
    
    /**
     * Remove todas as arestas do grafo.
     */
    public void removeAllEdges(){
        arestas.clear();
    }
    
    /**
     * Retorna um iterador da lista de vertices que saem/entram no vértice que envelopa o objeto recebido.
     * @param key objeto que é envelopado por um vértice.
     * @return iterador
     */
    public Iterator edgesList(Object key) {
        return vertices.get(key).getAdjacencias().values().iterator();
    }
    
    /**
     * Método que percorre todo o grafo em profundidade.
     * @param v objeto que é envelopado por um vértice. (ponto inicial)
     */
    public void dfs(Object v){
        dfs(vertices.get(v), new HashSet());
    }
    
    private void dfs(Vertice v, HashSet<Vertice> visited){
        visited.add(v);
        
        for(Vertice u: v.getAdjacencias().keySet()){
            if(!visited.contains(u)){
                System.out.println("("+v+","+u+")\n");
                dfs(u, visited);
            }
        }
    }
    
}
