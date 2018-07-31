package controller.System;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioJaCadastradoException;
import model.exceptions.UsuarioNaoCadastradoException;
import java.util.Map;
import model.Usuario;
import util.Grafo;
import model.GrafoDataBase;

/**
 * Classe responsável por criar objetos capazes de manipular grafos e administrar usuários.
 * @author Anésio Sousa dos Santos Neto
 */
public class ControllerUser {
    private Grafo grafo;
    private Map<String, Usuario> emailMap;
    private GrafoDataBase dados; // Tentar deixar esse cara estático.

    /**
     * Construtor da classe que controla os usuários.
     * Nele são inicializados o grafo e uma hashmap que facilita a procura de usuários pelo email.
     */
    public ControllerUser() {
        dados = new GrafoDataBase();
        grafo =  dados.getData();
        emailMap = new HashMap<>();
        atualizarEmailMap();
    }
    
    /**
     * Método que recebe dados de usuários, cria usuários com esses dados e insere esses usuários no grafo.
     * Retorna o usuário cadastrado.
     * @param nome nome do usuário.
     * @param email email do usuário.
     * @param password senha do usuário.
     * @param genero genero do usuário.
     * @param nascimento data de nascimento.
     * @param endereco endereco do usuário.
     * @param telefone telefone do usuário.
     * @param perfilEhPublico identifica se o usuário escolheu deixar o perfil público ou privado.
     * @return o usuário cadastrado caso já não exista um usuário com tal email na coleção.
     * @throws UsuarioJaCadastradoException lança essa exceção caso já não exista um usuário com tal email na coleção.
     */
    public Usuario cadastrarUser(String nome, String email, String password, String genero, String nascimento, String endereco, String telefone, boolean perfilEhPublico) throws UsuarioJaCadastradoException{
        if(obterUser(email) == null){
            Usuario user = new Usuario(nome, email, password, genero, nascimento, endereco, telefone, perfilEhPublico);
            emailMap.put(email, user);
            grafo.addVertex(user);
            dados.gravar();
            return user;
        }else{
            throw new UsuarioJaCadastradoException();
        }
    }

    /**
     * Método que remove um usuário da coleção de usuários.
     * Recebe um email e senha para verificação de integridade.
     * @param email
     * @param senha
     * @return o usuário removido.
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     */
    public Usuario removerUser(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        Usuario user = checarDados(email, senha);
        grafo.removeVertex(user);
        emailMap.remove(user.getEmail(), user);
        
        return user;
    }

    /*public void adicionarAmizade(Usuario userA, Usuario userB){
        grafo.addEdge(userA, userB, null);
    }
    
    public void desfazerAmizade(Usuario userA, Usuario userB){
        grafo.removeEdge(userA, userB);
    }
    
    public void modificarVinculo(Usuario userA, Usuario userB){
        
    }*/

    /**
     * Checa se existe na coleção algum usuário que contenha tal email, e se a senha desse cadastro bate com a senha recebida.
     * @param email email a ser verificado.
     * @param senha senha a ser verficada desse email.
     * @return o usuario com os dados caso tudo ocorra bem.
     * @throws UsuarioNaoCadastradoException
     * @throws SenhaIncorretaException
     */

    public Usuario checarDados(String email, String senha) throws UsuarioNaoCadastradoException, SenhaIncorretaException{
        Usuario user = obterUser(email);
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
    
    /**
     * Retorna uma lista de usuários amigos desse usuário recebido.
     * @param user usuário recebido.
     * @return lista de amizades.
     */
    public List listarAmizades(Usuario user){ 
        return grafo.getAdjacentsData(user);
    }
    
    /**
     * Retorna um iterador da lista de todos os usuários do sistema.
     * @return iterador.
     */
    public Iterator getListaDeUsuarios(){
        return emailMap.values().iterator();
    }
    
    /**
     * Retorna um inteiro que contém a informação de quantos usuários tem na coleção.
     * @return inteiro.
     */
    public int getQuantidadeUsers() {
        return grafo.numVertices();
    }
        
    /**
     * Recebe um email e retorna o usuário dono desse email caso exista.
     * @param email 
     * @return usuário
     */
    public Usuario obterUser(String email){ 
        return emailMap.get(email);
    }
    
    private void atualizarEmailMap(){
        Iterator<Usuario> itr = grafo.getKeySet();
        
        while(itr.hasNext()){
            Usuario user = itr.next();
            emailMap.put(user.getEmail(), user);
        }
    }
}
