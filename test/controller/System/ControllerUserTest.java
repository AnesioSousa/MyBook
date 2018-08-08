/* Referências:
   How do you assert that a certain exception is thrown in JUnit 4 tests?
   Disponível em: <https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests> acesso em : 23/07/18
 */
package controller.System;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import model.Usuario;
import model.exceptions.CampoVazioException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUserTest {
    ControllerUser ctrl;
    
    @Before
    public void setUp() throws SenhaIncorretaException, UsuarioJaCadastradoException, CampoVazioException {
        ctrl = new ControllerUser();
        try {
            Usuario u = ctrl.checarDados("teste.com","123");
            if( u != null){
                ctrl.removerUser("teste.com", "123");
            }
        } catch (UsuarioNaoCadastradoException ex) {
            ctrl.cadastrarUser("Anésio Sousa", "teste.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", true);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Teste do método cadastrarUser, da classe ControllerUser.
     *
     * @throws UsuarioJaCadastradoException
     * @throws CampoVazioException
     */
    @Test(expected = UsuarioJaCadastradoException.class)

    public void testCadastrarUser() throws UsuarioJaCadastradoException, CampoVazioException, UsuarioNaoCadastradoException, SenhaIncorretaException {
        String nome = "Anésio Sousa";
        String email = "teste.com";
        String password = "123";
        String genero = "Masculino";
        String nascimento = "00/00/00";
        String endereco = "Rua dos bobos número 0";
        String telefone = "(75) 90000-0000";
        boolean perfilEhPublico = false;

        Usuario u = ctrl.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);

        String expResult = "Anésio Sousa";
        String result = u.getNome();
        assertEquals(expResult, result);
        
        assertNotNull(ctrl.checarDados("teste.com", "123"));

        nome = "Gustavo Lima";
        email = "teste.com";
        password = "e voce, tchêrere";
        genero = "Masculino";
        nascimento = "05/12/95";
        endereco = "Rua Miguel calmon número 42";
        telefone = "(75) 98451-7741";
        perfilEhPublico = true;

        Usuario b = ctrl.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);
    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de um email não cadastrado.
     *
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     * @throws UsuarioJaCadastradoException
     * @throws CampoVazioException
     */
    @Test(expected = UsuarioNaoCadastradoException.class)
    public void testRemoverUser1() throws UsuarioNaoCadastradoException, SenhaIncorretaException, UsuarioJaCadastradoException, CampoVazioException {
        ctrl.cadastrarUser("Anésio Sousa", "teste.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);

        ctrl.removerUser("toinhoEhMaineiro@blogspot.com", "123");

    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de uma senha errada para um email cadastrado.
     *
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     * @throws UsuarioJaCadastradoException
     * @throws CampoVazioException
     */
    @Test(expected = SenhaIncorretaException.class)
    public void testRemoverUser2() throws SenhaIncorretaException, UsuarioNaoCadastradoException, UsuarioJaCadastradoException, CampoVazioException {
        ctrl.cadastrarUser("Anésio Sousa", "teste.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);

        ctrl.removerUser("teste.com", "44587");
    }

    /**
     * Teste do método removerUser, da classe ControllerUser. Testa o recebimento de uma senha correta para um email cadastrado.
     *
     * @throws UsuarioJaCadastradoException
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     * @throws CampoVazioException
     */
    @Test(expected = UsuarioNaoCadastradoException.class)
    public void testRemoverUser3() throws UsuarioJaCadastradoException, UsuarioNaoCadastradoException, SenhaIncorretaException, CampoVazioException {
        ctrl.cadastrarUser("Anésio Sousa", "teste.com", "123", "Masculino", "00/00/00", "Rua dos bobos número 0", "(75) 90000-0000", false);
        
        assertNotNull(ctrl.checarDados("teste.com", "123"));

        ctrl.removerUser("teste.com", "123");

        assertNull(ctrl.checarDados("teste.com", "123"));
                
    }

    /**
     * Teste do método obterUser, da classe ControllerUser.
     * @throws UsuarioJaCadastradoException
     * @throws CampoVazioException
     */
    @Test
    public void testObterUser() throws UsuarioJaCadastradoException, CampoVazioException {        
        assertNull(ctrl.obterUser("teste.com"));        
    }

}
