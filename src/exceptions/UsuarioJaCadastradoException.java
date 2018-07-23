package exceptions;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class UsuarioJaCadastradoException extends Exception{

    public UsuarioJaCadastradoException() {
        super("O usuário já está cadastrado!");
    }
 
}
