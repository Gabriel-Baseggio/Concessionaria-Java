package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.UsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.service.UsuarioService;

public class ClienteController extends UsuarioController {
    public ClienteController(UsuarioService usuarioService) {
        super(usuarioService);
    }

    public void cadastrarCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException, UsuarioInvalidoException {
        Cliente novoCliente = new Cliente(nome, cpf, senha);
        cadastrarUsuario(novoCliente);
    }

    public void editarCliente(String nome, Long cpf, String senha) throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        isGerente();
        Cliente novoCliente = new Cliente(nome, cpf, senha);
        editarUsuario(novoCliente);
    }

}
