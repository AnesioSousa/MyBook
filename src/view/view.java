package view;

import facade.Facade;
import model.exceptions.UsuarioJaCadastradoException;
import model.Usuario;
import model.exceptions.SenhaIncorretaException;
import model.exceptions.UsuarioNaoCadastradoException;
import util.trie.MapTrie;

public class view {  
    public static void main(String[] args) {
        Facade f = Facade.getInstance();
        
        MapTrie map = new MapTrie();
        Usuario a = null;
        try {
            a = f.registrarUser("Anésio Sousa", "anesios98@gmail.com", "123", "Masculino", "17/11/2015", "Rua angico n11", "(75) 99191-6636", true);
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
            f.iniciarSessao("anesios98@gmail.comm", "123");
            System.out.println("EEEEHhh");
        } catch (UsuarioNaoCadastradoException | SenhaIncorretaException ex) {
            System.out.println(ex);
        }
        
        map.insert(a.getNome(), a);
        map.insert("André", "AndréTest");
        map.insert("Antônio", "AntônioTest");
        map.insert("Anterior", "AnteriorTest");
        
        System.out.println(map.contains("Anésio Sousa"));
        System.out.println(map.getKeySuggestions("An"));
    }
}
