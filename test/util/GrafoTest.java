package util;

import java.util.Iterator;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class GrafoTest {
    Object u, v, z;
    Grafo g;
    
    @Before
    public void setUp() {
        g = new Grafo();
        u = null;
        v = null;
        z = null;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste do método addVertex, da classe Grafo.
     */
    @Test
    public void testAddVertex() {
        v = "Teste";
        g.addVertex(v);
        
        int esperado = 1;
        int resultado = g.numVertices();
        assertSame(esperado, resultado);
        
        Iterator<Vertice> itr = g.vertices();
        assertEquals("Teste", itr.next().getElemento());
    }
    
    /**
     * Teste do método removeVertex, da classe Grafo.
     */
    @Test
    public void testRemoveVertex() { // Problema: Tem que ver a questão das adjacencias tambem! -> como, se não existe acesso direto aos vértices?
        u = "Teste1";
        g.addVertex(u);
        
        g.removeVertex(u);
        int esperado = 0;
        int resultado = g.numVertices();
        assertSame(esperado, resultado);
        
        Iterator itr = g.vertices();
        assertFalse(itr.hasNext());
        
    }
    
    /**
     * Teste do método vertices, da classe Grafo.
     */
    @Test
    public void testVertices() {
        u = "Teste1";
        v = "Teste2";

        g.addVertex(u);
        g.addVertex(v);
        

        Iterator<Vertice> itr = g.vertices();
              
        assertEquals(u,itr.next().getElemento());
        assertEquals(v,itr.next().getElemento());
        
        assertFalse(itr.hasNext());
    }
    
    /**
     * Teste do método numVertices, da classe Grafo.
     */
    @Test
    public void testNumVertices() {
        u = "Teste";
        int esperado = 0;
        int resultado = g.numVertices();
        assertSame(esperado, resultado);
        
        g.addVertex(u);
        esperado = 1;
        resultado = g.numVertices();
        assertSame(esperado, resultado);
        
        g.removeVertex(u);
        esperado = 0;
        resultado = g.numVertices();
        assertSame(esperado, resultado);
    }

    /**
     * Teste do método addEdge, da classe Grafo.
     */
    @Test
    public void testAddEdge() {
        u = "Teste1";
        v = "Teste2";
        Object data = "Amizade";

        g.addVertex(u);
        g.addVertex(v);
        g.addEdge(u, v, data);
        
        assertEquals("Amizade",g.getEdge(u, v));
        
        int esperado = 1;
        int resultado = g.numEdges();
        assertSame(esperado, resultado);  
    }
    
    /**
     * Teste do método removeEdge, da classe Grafo.
     */
    @Test
    public void testRemoveEdge() {
        u = "Teste1";
        v = "Teste2";
        
        g.addVertex(u);
        g.addVertex(v);
        
        g.addEdge(u, v, "Configurações");
        int esperado = 1;
        int resultado = g.numEdges();
        assertSame(esperado, resultado);
        
        g.removeEdge(u, v);
        esperado = 0;
        resultado = g.numEdges();
        assertSame(esperado, resultado);
    }

    /**
     * Teste do método getEdge, da classe Grafo.
     */
    @Test
    public void testGetEdge() {
        u = 1;
        v = 2;
        Object data = "Tempo de Amizade";
        
        g.addVertex(u);
        g.addVertex(v);
        
        g.addEdge(u, v, data);
        
        Object esperado = "Tempo de Amizade";
        Object resultado = g.getEdge(u, v);

        assertEquals(esperado, resultado);
    }

    /**
     * Teste do método edges, da classe Grafo.
     */
    @Test
    public void testEdges() {
        u = 2.51;
        v = 3.22;
        z = 9.91;

        g.addVertex(u);
        g.addVertex(v);
        g.addVertex(z);
        
        g.addEdge(u, v, "2 curtidas");
        g.addEdge(v, z, "52 curtidas");
        
        Iterator<Aresta> itr = g.edges();
        assertTrue(itr.hasNext());
        
        Object esperado = "2 curtidas";
        Object resultado = itr.next().getDado();
        assertEquals(esperado, resultado);
        
        esperado = "52 curtidas";
        resultado = itr.next().getDado();
        assertEquals(esperado, resultado);
        
        assertFalse(itr.hasNext());
    }

    /**
     * Teste do método numEdges, da classe Grafo.
     */
    @Test
    public void testNumEdges() {
        u = true;
        v = false;
        z = "Magnéticos";
        
        g.addVertex(u);
        g.addVertex(v);
        g.addVertex(z);
        
        int esperado = 0;
        int resultado = g.numEdges();
        assertSame(esperado, resultado);
        
        g.addEdge(u, v, 1);
        esperado = 1;
        resultado = g.numEdges();
        assertSame(esperado, resultado);
        
        g.addEdge(v, z, 3);
        g.addEdge(u, z, 9);
        esperado = 3;
        resultado = g.numEdges();
        assertSame(esperado, resultado);
        
        g.removeEdge(u, v);
        esperado = 2;
        resultado = g.numEdges();
        assertSame(esperado, resultado);
        
        g.removeEdge(v, z);
        g.removeEdge(u, z);
        esperado = 0;
        resultado = g.numEdges();
        assertSame(esperado, resultado);
        
    }

    /**
     * Teste do método edgesList, da classe Grafo.
     */
    @Test
    public void testEdgesList() {
        u = 4;
        v = 6;
        z = 7;
        
        g.addVertex(u);
        g.addVertex(v);
        g.addVertex(z);
        
        // Gera Par?
        g.addEdge(u, v, true);
        g.addEdge(u, z, false);
        
        Iterator<Aresta> itr = g.edges();
        assertEquals(true, itr.next().getDado());
        assertEquals(false, itr.next().getDado());
        
        assertFalse(itr.hasNext());
        
    }
    
}
