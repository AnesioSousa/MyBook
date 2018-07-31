package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.Grafo;

/**
 * Classe para serializar os dados.
 * Classe baseada na solução proposta pelo livro: User a cabeça! Java.
 * @author Anésio Sousa dos Santos Neto
 */
public final class GrafoDataBase {

    private Grafo grafo;
    private final String FILE_NAME = "database/grafo.serial";

    public GrafoDataBase() {
        checkFile();
    }
        
    // Tem que ser a primeira coisa a executar! Tem que verificar a existencia.
    // Se não, criar arquivo, criar grafo, grava grafo no arquivo, se existe, só manda recuperar.
    
    private void checkFile(){ // Esse cara tem que criar o grafo e o arquivo se o arquivo não existir. 
        File f =  new File(FILE_NAME);
        if(!f.exists()){
            try {
                System.out.println("Primeira execução do sistema... Criando arquivo de data base...");
                f.createNewFile();
                grafo = new Grafo();
                gravar();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }else{
            recuperar();
        }
    }
    
    private void recuperar() { 
        try {
            FileInputStream fileInput = new FileInputStream(FILE_NAME);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            grafo = (Grafo) objectInput.readObject();
            objectInput.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
   
    public boolean gravar(){
        try {
            FileOutputStream fileOutput = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(grafo);
            objectOutput.close();
            
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public Grafo getData() {
        return grafo;
    }
}