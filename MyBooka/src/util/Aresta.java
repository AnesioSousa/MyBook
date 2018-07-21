package util;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Aresta {

    private Object dado;
    private Vertice origem;
    private Vertice destino;

    public Aresta(Vertice origem, Vertice destino, Object element) {
        this.origem = origem;
        this.destino = destino;
        this.dado = element;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }
    
    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

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
            if (a.getOrigem().equals(this.origem) && a.getDestino().equals(this.destino) ||
                a.getOrigem().equals(this.destino) && a.getDestino().equals(this.origem)) {
                return true;
            }
        }

        return false;
    }

}
