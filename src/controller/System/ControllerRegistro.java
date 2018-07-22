package controller.System;

import model.Usuario;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerRegistro {
    
    public Usuario cadastrarUser(String nome, String login, String password, String email, String genero, String nascimento, String endereco, String telefone, boolean estadoPerfil){
        if(Validar.login(login)){
           return new Usuario(nome, login, password, email, genero, nascimento, endereco, telefone, estadoPerfil);
        }
        
        return null;
    }
    
}
