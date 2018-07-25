package model.exceptions;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class UsuarioNaoCadastradoException extends Exception{

    public UsuarioNaoCadastradoException() {
        super("Usuário não cadastrado!");
    }
    
}
