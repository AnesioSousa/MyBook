package facade;

import controller.System.ControllerSessao;
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
    private ControllerSessao ctrlSession;
    private static Facade INSTANCE = null;

    private Facade() {
        this.ctrlUser = new ControllerUser();
        this.ctrlSession = new ControllerSessao();
    }
    
    public static Facade getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Facade();
        }
        return INSTANCE;
    }
      
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil) throws UsuarioJaCadastradoException{
        return ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
    }
    /* REVER ISSOO!!!!
    public Usuario excluirUser() throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        return ctrlUser.removerUser(ctrlSession.getActualUser());
    }*/
    
    public Usuario iniciarSessao(String email, String password) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        return  ctrlSession.login(email, password);
    }
    
    // Fazer exceção para se não tiver usar logado!
    public void encerrarSessão(){
        ctrlSession.logout();
    }
    
    public boolean checkEmail(String email){
        return ControllerUser.obterUser(email) != null;
    }

    public ControllerUser getCtrlUser() {
        return ctrlUser;
    }

    public ControllerSessao getCtrlSession() {
        return ctrlSession;
    }

}
