package facade;

import controller.System.ControllerUser;
import model.Usuario;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public final class Facade {
    private ControllerUser ctrlUser;
    private static Facade INSTANCE = null;

    private Facade() {
        this.ctrlUser = new ControllerUser();
    }
    
      public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }
    
   /* public Usuario registrarUser(String nome, String login, String password, String email, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil){
        return ctrlUser.cadastrarUser(nome, password, email, genero, nascimento, endereco, telefone, estadoPerfil);
    }*/
    
    /*public Usuario excluirUser(String login, String password){
        // <<<<< Tem que passar pela verificação dos dados primeiro, e depois passa pro controller remover >>>>>>.
        Usuario aux = ctrlUser.obterUser(login);
        ctrlUser.removerUser(aux);
        
        return null;
    }*/
}
