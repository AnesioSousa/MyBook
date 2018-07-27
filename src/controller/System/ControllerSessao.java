package controller.System;

import model.*;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;

/**
 *
 * @author Anésio Sousa dos Santos Neto
 */
public final class ControllerSessao {
    private Usuario usuarioAtual;
    private long start;
    private long finish;
    
    public Usuario login(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        usuarioAtual = autenticar(email, senha);
        start = System.currentTimeMillis();
        
        return usuarioAtual;
    }
    
    public void logout(){
        finish = System.currentTimeMillis();
        //usuarioAtual.incrementTempoTotalDeUso(tempoEmSessao());
        usuarioAtual = null;
    }

    private Usuario autenticar(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        Usuario user = ControllerUser.obterUser(email);  // TEM QUE VER SE ISSO ESTÁ CORRETO!!!
        if(user != null){
            if(user.getPassword().equals(senha)){
                return user;
            }else{
                throw new SenhaIncorretaException();
            }
        }else{
            throw new UsuarioNaoCadastradoException();
        }
    }
    
    private long tempoEmSessao(){
        //long ms = ;
        //String time = String.format("%03d:%02d:%02d", ms / 3600000, ( ms / 60000 ) % 60,  ( ms / 1000 ) % 60);
        return finish - start;
    }
    
    public Usuario getActualUser(){
        return usuarioAtual;
    }
    
}
