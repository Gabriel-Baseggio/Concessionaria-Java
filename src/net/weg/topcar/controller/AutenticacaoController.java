package net.weg.topcar.controller;

import net.weg.topcar.model.usuarios.Autenticacao;
import net.weg.topcar.model.usuarios.Cliente;

public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    public Cliente login(Long cpf, String senha){
        autenticacaoService.login(cpf, senha);
    }

    public void logout(){
        autenticacaoService.logout();
    }

}
