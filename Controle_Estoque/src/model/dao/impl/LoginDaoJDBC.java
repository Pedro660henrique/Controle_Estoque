package model.dao.impl;

import model.dao.DaoFactory;
import model.entities.Usuario;

public class LoginDaoJDBC {

    public static boolean autenticar(Integer idUsuario, String senha) {
    	
        UsuarioDaoJDBC usuarioDao = (UsuarioDaoJDBC) DaoFactory.createUsuarioDao(); 

        Usuario usuario = usuarioDao.findById(idUsuario);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login realizado com sucesso!");
            return true; 
        } else {
            System.out.println("Credenciais inválidas.");
            return false; 
        }
    }
}