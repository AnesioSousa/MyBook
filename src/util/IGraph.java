package util;

import java.util.Iterator;

/**
 * 
 * @author João B. Rocha Júnior
 */
public interface IGraph {
    
    public Object addVertex(Object key);
    
    public Iterator vertices();
    
    public int numVertices();
    
    public Object removeVertex(Object key);
    
    public void addEdge(Object u, Object v, Object data);
    
    public Object getEdge(Object u, Object v);
    
    public Iterator edges();
    
    public int numEdges();
    
    public void removeEdge(Object u, Object v);
    
    public Iterator edgesList(Object v);
}