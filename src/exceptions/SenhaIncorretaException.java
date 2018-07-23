package exceptions;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("A senha digitada está incorreta!");
    }
}
