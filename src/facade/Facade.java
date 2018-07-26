package facade;

import controller.System.ControllerUser;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;

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
    public boolean checkEmail(String email){
        return ctrlUser.obterUser(email) != null;
    }
      
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        return ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
    }
    
    public Usuario excluirUser(String email, String password) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        return ctrlUser.removerUser(email, password);
    }
}
