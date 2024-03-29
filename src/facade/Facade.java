package facade;

import controller.System.ControllerUser;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.Usuario;
import model.exceptions.CampoVazioException;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;

/**
 * Classe responsável por facilitar o acesso de interfaces ao sistema.
 * 
 * @author Anésio Sousa dos Santos Neto
 */
public final class Facade {

    private Usuario usuarioAtual;
    private ControllerUser ctrlUser;

    /**
     * Construtor da classe Facade.
     * Nele são inicializadas as grandezas do sistema.
     */
    public Facade() {
        ctrlUser = new ControllerUser();
    }

    /**
     * Método que registra um usuário.
     * Chama o método de registro do controller de usuários.
     * 
     * @param nome
     * @param email
     * @param password
     * @param genero
     * @param nascimento
     * @param endereco
     * @param telefone
     * @param estadoPerfil
     * @return
     * @throws UsuarioJaCadastradoException
     * @throws CampoVazioException
     */
    public Usuario registrarUser(String nome, String email, String password, String genero, String nascimento,
            String endereco, String telefone, boolean estadoPerfil)
            throws UsuarioJaCadastradoException, CampoVazioException {
        Usuario user = ctrlUser.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone,
                estadoPerfil);
        return user;
    }

    /**
     * Método que exclui um usuário da coleção.
     * Chama o método de exclusão do controller de usuários.
     * 
     * @param email
     * @param senha
     * @return
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     */
    public Usuario excluirUser(String email, String senha)
            throws UsuarioNaoCadastradoException, SenhaIncorretaException {
        encerrarSessaoAtual();
        Usuario user = ctrlUser.removerUser(email, senha);
        return user;
    }

    /**
     * Método que inicia a sessão de um usuário.
     * Recebe o email e a senha de tal usuário para a verificação.
     * 
     * @param email
     * @param password
     * @return
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     */
    public Usuario iniciarSessao(String email, String password)
            throws UsuarioNaoCadastradoException, SenhaIncorretaException {
        usuarioAtual = ctrlUser.checarDados(email, password);
        return usuarioAtual;
    }

    /**
     * Encerra a sessão do usuário atual.
     */
    public void encerrarSessaoAtual() {
        usuarioAtual = null;
    }

    /* CLARAMENTE SUBSTITUÍVEL POR CONSULTA À BANCO! */
    /**
     * Recebe um nome e procura na coleção de usuários, usários que contenham tal
     * nome.
     * 
     * @param nome nome do usuário a ser procurado.
     * @return retorna uma lista de usuários com o nome recebido.
     */
    public List buscarUser(String nome) {
        List<Usuario> ret = new LinkedList();
        Iterator<Usuario> users = ctrlUser.getListaDeUsuarios();
        while (users.hasNext()) {
            Usuario u = users.next();
            if (u.getNome().equalsIgnoreCase(nome)) {
                ret.add(u);
            }
        }
        return ret;
    }

    /**
     * Recebe um email e verifica se existe algum cadastro que contenha tal email.
     * 
     * @param email email a ser procurado.
     * @return true ou false, depende da existencia ou não de tal usuário.
     */
    public boolean checkEmail(String email) {
        return ctrlUser.obterUser(email) != null;
    }

    /**
     * Retorna o controlador de usuários.
     * 
     * @return Controller
     */
    public ControllerUser getCtrlUser() {
        return ctrlUser;
    }

    public Iterator listarUsuarios() {
        return ctrlUser.getListaDeUsuarios();
    }

    /**
     * Dita qual o usuário logado atualmente.
     * 
     * @param usuarioAtual
     */
    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    /**
     * Retorna o usuário logado atualmente.
     * 
     * @return
     */
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void atualizarBaseDeDados() {
        ctrlUser.saveDatabase();
    }
}
