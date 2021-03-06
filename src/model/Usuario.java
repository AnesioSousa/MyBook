package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Usuario implements Serializable{
    private String nome, password, email, genero, nascimento, endereco, telefone, urlImagemPerfil;
    private LinkedList<String> solicitacoes;
    private LinkedList<String> postagens;
    private LinkedList<Usuario> amigos;
    private boolean perfilPrivado;

    public Usuario(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean perfilEhPublico) {
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        solicitacoes = new LinkedList<>();
        postagens = new LinkedList<>();
        amigos = new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrlImagemPerfil() {
        return urlImagemPerfil;
    }

    public void setUrlImagemPerfil(String urlImagemPerfil) {
        this.urlImagemPerfil = urlImagemPerfil;
    }

    public LinkedList<String> getSolicitacoes() {
        return solicitacoes;
    }

    public boolean isPerfilPrivado() {
        return perfilPrivado;
    }

    public void setPerfilPrivado(boolean perfilPrivado) {
        this.perfilPrivado = perfilPrivado;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Usuario) {
            Usuario other = (Usuario) obj;
            if(this.email.equals(other.getEmail())){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return nome + " | " + email;
    }
    
}
