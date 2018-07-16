package util;

import java.util.Arrays;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Aresta {
    //private int peso;
    private Object element;
    private Vertice[] extremidades;

    public Aresta(Vertice origem, Vertice destino, Object element/*, int peso*/) {
        extremidades = new Vertice[]{origem,destino};
        //this.peso = peso;
        this.element = element;
    }

    /*public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public int getPeso() {
        return peso;
    }*/

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
    
    public Vertice[] getExtremidades() {
        return extremidades;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    /* Tem que rever esse método equals, pois se o grafo não for direcionado, uma aresta
       que liga um vértice u à um vértice v é igual a uma aresta que liga v to u. Mas o
       mesmo não ocorre se o grafo for direcionado.
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Aresta) {
            Aresta a = (Aresta) obj;
            if(Arrays.equals(a.getExtremidades(), this.extremidades)){
                return true;
            }
        }
        
        return false;
    }
    
    

}
