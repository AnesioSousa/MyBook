package model;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public abstract class Post {
    protected String date;
    protected String texto;
    
    public void postar(Usuario user){
        
    }    
    
    public void salvarPostagemEmDisco(){
        
    }
    
    public abstract void exibir();
}
