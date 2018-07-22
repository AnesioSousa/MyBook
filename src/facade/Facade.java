package facade;

import controller.System.ControllerRegistro;
import controller.System.ControllerUser;
import model.Usuario;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public final class Facade {
    private ControllerRegistro ctrlReg;
    private ControllerUser ctrlUser;
    private static Facade INSTANCE = null;

    private Facade() {
        this.ctrlReg = new ControllerRegistro();
        this.ctrlUser = new ControllerUser();
    }
    
      public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }
    
    public boolean registrarUser(String nome, String login, String password, String email, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil){
        Usuario a = ctrlReg.cadastrarUser(nome, login, password, email, genero, nascimento, endereco, telefone, estadoPerfil);
        
        if(a != null){
            ctrlUser.inserirUserNoGrafo(a);
            return true;
        }

        return false;
    }
    
    public Usuario excluirUser(String login, String password){
        
        return null;
    }
}
