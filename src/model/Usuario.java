package model;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class Usuario {
    private String nome;
    private String password;
    private String email; // Tentar fazer uma forma de validação depois (usando Pattern) se possível.
    private String genero;
    private Perfil perfil;
    private String nascimento;
    private String endereco;
    private String telefone;

    public Usuario(String email) {
        this.email = email;
    }
    
    public Usuario(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) {
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.perfil = new Perfil(this, estadoPerfil);
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.telefone = telefone;
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
        return "Usuario{" + "nome=" + nome + ", email=" + email + '}';
    }
    
}
