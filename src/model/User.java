package model;

/**
 *
 * @author anesio
 */
public class User {
    private String nome;

    public User(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  nome;
    }
    
}
