package model;

import java.util.LinkedList;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Perfil {
    private Usuario owner;
    private int arquivos; // <<Rever isso daqui
    private LinkedList<Post> postagens;
    private LinkedList<Usuario> amigos;
    private boolean privado;

    public Perfil(Usuario user, boolean privado) {
        this.owner = user;
        this.postagens = new LinkedList<>();
        this.amigos = new LinkedList<>();
        this.privado = privado;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
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
        return "Perfil{" + "user=" + owner + ", estado=" + privado + '}';
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
            if(this.owner.equals(other.getOwner())){
                return true;
            }
        }
        
        return false;
    }
}
