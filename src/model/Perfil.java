package model;

import java.util.LinkedList;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Perfil {
    private Usuario user; // <<Ver questão do owner
    private int arquivos; // <<Rever isso daqui
    private LinkedList<Post> postagens;
    private LinkedList<Usuario> amigos;
    private boolean privado;

    public Perfil(Usuario user, boolean privado) {
        this.user = user; // <<Ver questão do owner
        this.postagens = new LinkedList<>();
        this.amigos = new LinkedList<>();
        this.privado = privado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LinkedList<Post> getPostagens() {
        return postagens;
    }

    public LinkedList<Usuario> getAmigos() {
        return amigos;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    @Override
    public String toString() {
        return "Perfil{" + "user=" + user + ", estado=" + privado + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Perfil) {
            Perfil other = (Perfil) obj;
            if(this.user.equals(other.getUser())){
                return true;
            }
        }
        
        return false;
    }
}
