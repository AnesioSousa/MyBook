package controller.System;

import model.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anesio
 */
public class ControllerUserTest {
    
    public ControllerUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarUser method, of class ControllerUser.
     */
    @Test
    public void testCadastrarUser() throws Exception {
        System.out.println("cadastrarUser");
        String nome = "";
        String email = "";
        String password = "";
        String genero = "";
        String nascimento = "";
        String endereco = "";
        String telefone = "";
        boolean estadoPerfil = false;
        ControllerUser instance = new ControllerUser();
        Usuario expResult = null;
        Usuario result = instance.cadastrarUser(nome, email, password, genero, nascimento, endereco, telefone, estadoPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerUser method, of class ControllerUser.
     */
    @Test
    public void testRemoverUser() throws Exception {
        System.out.println("removerUser");
        String email = "";
        String password = "";
        ControllerUser instance = new ControllerUser();
        Usuario expResult = null;
        Usuario result = instance.removerUser(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obterUser method, of class ControllerUser.
     */
    @Test
    public void testObterUser() {
        System.out.println("obterUser");
        String email = "";
        ControllerUser instance = new ControllerUser();
        Usuario expResult = null;
        Usuario result = instance.obterUser(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
