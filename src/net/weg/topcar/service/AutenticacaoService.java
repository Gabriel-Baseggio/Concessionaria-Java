package net.weg.topcar.service;

import net.weg.topcar.model.exceptions.SenhaIncorretaException;
import net.weg.topcar.model.usuarios.Cliente;

public class AutenticacaoService {

    private UsuarioService usuarioService;

    public Cliente login(Long cpf, String senha){
        Cliente cliente = usuarioService.buscarUm(cpf);
        if(cliente.getSenha().equals(senha)){
            return cliente;
        }
        throw new SenhaIncorretaException();
    }

    public void logout(){

    }

}
