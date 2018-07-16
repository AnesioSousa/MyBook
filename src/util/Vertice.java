package util;

import java.util.HashMap;

/**
 * Classe responsável por criar objetos que são vértices de um grafo. Cada objeto vértice armazena um elemento e tem um mapa de todos os vertices adjacentes a ele.
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Vertice {

    private Object elemento;
    private HashMap<Vertice, Aresta> arestasSaindo, arestasEntrando;

    /**
     * Construtor da classe vértices. Método que inicia os atributos da classe. Recebe um elemento por parametro e guarda esse elemento como seu.
     *
     * @param elem dado que o vértice irá guardar ao ser criado.
     * @param oGrafoEhDirecionado
     */
    public Vertice(Object elem, boolean oGrafoEhDirecionado) {
        elemento = elem;
        arestasSaindo = new HashMap<>();
        if (oGrafoEhDirecionado) {
            arestasEntrando = new HashMap<>();
        } else {
            arestasEntrando = arestasSaindo;
        }
    }

    /**
     * Retorna o elemento que esse vértice guarda.
     *
     * @return elemento
     */
    public Object getElemento() {
        return elemento;
    }

    /**
     * Dita qual elemento esse vértice deve guardar.
     *
     * @param elemento novo elemento
     */
    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    /**
     * Retorna o mapa de vértices que se tornaram adjacentes por receberem arestas que saem deste vértice.
     *
     * @return mapa
     */
    public HashMap<Vertice, Aresta> getArestasSaindo() {
        return arestasSaindo;
    }

    /**
     * Retorna o mapa de vértices que se tornaram adjacentes por terem arestas que saem dos mesmos e incidem neste vértice.
     *
     * @return
     */
    public HashMap<Vertice, Aresta> getArestasEntrando() {
        return arestasEntrando;
    }

}
