package util;

import java.util.Iterator;

/**
 *
 * @author João B. Rocha Júnior
 */
public interface IGraph {
    public void addVertex(Object key);
    
    public Iterator vertices();
    
    public int numVertices();
    
    public void removeVertex(Object key);
    
    public void addEdge(Vertice u, Vertice v, Object data/*, int peso*/);
    
    public Aresta getEdge(Vertice u, Vertice v);
    
    public Iterator edges();
    
    public int numEdges();
    
    public void removeEdge(Aresta a);
    
    public Iterator outGoingEdges(Vertice v);
    
    public int outDegree(Vertice v);
    
    public Iterator incomingEdges(Vertice v);
    
    public int inDegree(Vertice v);
}
