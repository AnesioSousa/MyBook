package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsável por criar objetos que são vértices de um grafo. 
 * Cada objeto vértice armazena um elemento e tem um mapa de todos os vertices adjacentes a ele.
 * @author Anésio Sousa dos Santos Neto
 */
public class Vertice implements Serializable{

    private Object elemento;
    private HashMap<Vertice, Aresta> adjacencias;

    /**
     * Construtor da classe vértices.
     * Método que inicia os atributos da classe. Recebe um elemento por 
     * parametro e guarda esse elemento como seu.
     * @param elem dado que o vértice irá guardar ao ser criado.
     */
    public Vertice(Object elem) {
        elemento = elem;
        adjacencias = new HashMap<>();
    }

    /**
     * Retorna o elemento que esse vértice guarda.
     * @return elemento
     */
    public Object getElemento() {
        return elemento;
    }

    /**
     * Dita qual elemento esse vértice deve guardar.
     * @param elemento novo elemento
     */
    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    /**
     * Retorna o mapa de adjacencias que esse vértice possui.
     * @return mapa
     */
    public HashMap<Vertice, Aresta> getAdjacencias() {
        return adjacencias;
    }

    /**
     * Retorna a lista de dados dos vértices adjacentes a este.
     * @return lista
     */
    public List getDadosDeAdjacencias() {
        List dados = new LinkedList();
        Iterator<Vertice> itr = adjacencias.keySet().iterator();
        while(itr.hasNext()){
            dados.add(itr.next().getElemento());
        }
        return dados;
    }
    
    @Override
    public String toString() {
        return "Vertice{" + "elemento=" + elemento + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Vertice) {
            Vertice other = (Vertice) obj;
            if(this.elemento.equals(other.getElemento())){
                return true;
            }
        }
        return false;
    }
    
    
}
