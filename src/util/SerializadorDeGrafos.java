package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe para serializar os dados.
 * Classe baseada na solução proposta pelo livro: User a cabeça! Java.
 * @author Anésio Sousa dos Santos Neto
 */
public class SerializadorDeGrafos {

    private Grafo grafo;

    public SerializadorDeGrafos(Grafo grafo) {
        this.grafo = grafo;
    }

    /**
     * Grava os dados em um arquivo .serial
     *
     * @param grafo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void gravar(Grafo grafo) throws FileNotFoundException, IOException {
        FileOutputStream fileoutput = new FileOutputStream("grafo.serial");
        ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);

        objectoutput.writeObject(grafo);
        objectoutput.close();
    }

    /**
     * Recupera os dados de um arquivo .serial
     *
     * @return @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Grafo recuperar() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileinput = new FileInputStream("grafo.serial");
        ObjectInputStream objectinput = new ObjectInputStream(fileinput);

        grafo = (Grafo) objectinput.readObject();
        objectinput.close();
        
        return grafo;
    }

}