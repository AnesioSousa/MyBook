/* Referências:
   Expected Exceptions Disponível em: <https://github.com/junit-team/junit4/wiki/exception-testing> acesso em : 23/07/18
 */
package controller.System;

import exceptions.SenhaIncorretaException;
import exceptions.UsuarioJaCadastradoException;
import exceptions.UsuarioNaoCadastradoException;
import model.Usuario;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUserTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Teste do método cadastrarUser, da classe ControllerUser.
     *
     * @throws exceptions.UsuarioJaCadastradoException
     */
    @Test(expected = UsuarioJaCadastradoException.class)

    public void testCadastrarUser() throws UsuarioJaCadastradoException {
        String nome = "Anésio Sousa";
        String email = "anesios98@gmail.com";
        String password = "123";
        String genero = "Masculino";
        String nascimento = "00/00/00";
        String endereco = "Rua dos bobos número 0";
        String telefone = "(75) 90000-0000";
        boolean perfilEhPublico = false;

        ControllerUser ctrl = new ControllerUser();
        Usuario u = ctrl.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);

        String expResult = "Anésio Sousa";
        String result = u.getNome();
        assertEquals(expResult, result);

        int esperado = 1;
        int resultado = ctrl.getUsers().size();
        assertSame(esperado, resultado);

        nome = "Gustavo Lima";
        email = "anesios98@gmail.com";
        password = "e voce, tchêrere";
        genero = "Masculino";
        nascimento = "05/12/95";
        endereco = "Rua Miguel calmon número 42";
        telefone = "(75) 98451-7741";
        perfilEhPublico = true;

        Usuario b = ctrl.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);

        esperado = 1;
        resultado = ctrl.getUsers().size();
        assertSame(esperado, resultado);
    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de um email não cadastrado.
     *
     * @throws exceptions.UsuarioNaoCadastradoException
     * @throws exceptions.SenhaIncorretaException
     * @throws exceptions.UsuarioJaCadastradoException
     */
    @Test(expected = UsuarioNaoCadastradoException.class)
    public void testRemoverUser1() throws UsuarioNaoCadastradoException, SenhaIncorretaException, UsuarioJaCadastradoException {
        ControllerUser ctrl = new ControllerUser();
        ctrl.cadastrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);

        ctrl.removerUser("toinhoEhMaineiro@blogspot.com", "123");

    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de uma senha errada para um email cadastrado.
     *
     * @throws exceptions.UsuarioNaoCadastradoException
     * @throws exceptions.SenhaIncorretaException
     * @throws exceptions.UsuarioJaCadastradoException
     */
    @Test(expected = SenhaIncorretaException.class)
    public void testRemoverUser2() throws SenhaIncorretaException, UsuarioNaoCadastradoException, UsuarioJaCadastradoException {
        ControllerUser ctrl = new ControllerUser();
        ctrl.cadastrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);

        ctrl.removerUser("anesios98@gmail.com", "44587");
    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de uma senha correta para um email cadastrado.
     *
     * @throws exceptions.UsuarioJaCadastradoException
     * @throws exceptions.UsuarioNaoCadastradoException
     * @throws exceptions.SenhaIncorretaException
     */
    @Test
    public void testRemoverUser3() throws UsuarioJaCadastradoException, UsuarioNaoCadastradoException, SenhaIncorretaException {
        ControllerUser ctrl = new ControllerUser();
        ctrl.cadastrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);
        
        int esperado = 1;
        int resultado = ctrl.getUsers().size();
        assertSame(esperado, resultado);

        ctrl.removerUser("anesios98@gmail.com", "123");

        esperado = 0;
        resultado = ctrl.getUsers().size();
        assertSame(esperado, resultado);
        
    }

    /**
     * Teste do método obterUser, da classe ControllerUser.
     * @throws exceptions.UsuarioJaCadastradoException
     */
    @Test
    public void testObterUser() throws UsuarioJaCadastradoException {
        ControllerUser ctrl = new ControllerUser();
        Usuario u = ctrl.cadastrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);
        
        Usuario esperado = u;
        Usuario resultado = ctrl.obterUser("anesios98@gmail.com");
        assertEquals(esperado, resultado);
        
    }

}
