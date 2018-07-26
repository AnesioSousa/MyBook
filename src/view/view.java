package view;

import controller.System.ControllerSessao;
import controller.System.ControllerUser;
import facade.Facade;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.exceptions.UsuarioJaCadastradoException;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;
import util.Grafo;

public class view {  
    public static void main(String[] args) {
        Facade f = Facade.getInstance();
        
        
        try {
            Usuario a = f.registrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "17/11/2015", "Rua angico n11", "(75) 99191-6636", true);
            System.out.println(a.getEmail() +" "+a.getEndereco() );
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex);
        }

        
        Usuario b = null;
        try {
            b = f.registrarUser("Gustavo da Costa", "anesios98@gmail.com", "321", "Bixa loca", "12/01/2014", "Rua pau brasil n431", "(75) 98205-5469", false);
        } catch (UsuarioJaCadastradoException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        try {
            f.iniciarSessao("anesios98@gmail.com", "123");
            System.out.println("EEEEHhh");
        } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
            System.out.println(ex);
        }
    }
}
