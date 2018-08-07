package model.exceptions;

/**
 *
 * @author anesio
 */
public class CampoVazioException extends Exception{
    
    public CampoVazioException() {
        super("Algum dos campos inseridos está vazio!");
    }
}
