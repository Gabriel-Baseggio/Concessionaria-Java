package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.LoginInvalidoException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.service.AutenticacaoService;

public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    public Cliente login(Long cpf, String senha) throws ObjetoNaoEncontradoException, LoginInvalidoException {
        return autenticacaoService.login(cpf, senha);
    }

    public void logout(){
        autenticacaoService.logout();
    }

}
