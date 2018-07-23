package exceptions;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class UsuarioNaoCadastradoException extends Exception{

    public UsuarioNaoCadastradoException() {
        super("O usuário não cadastrado!");
    }
    
}
