package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.UsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.service.UsuarioService;

public class VendedorController extends UsuarioController {
    public VendedorController(UsuarioService usuarioService) {
        super(usuarioService);
    }

    public void cadastrarVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoExistenteException, UsuarioInvalidoException, PermissaoNegadaException {
        isGerente();
        Vendedor novoVendedor = new Vendedor(nome, cpf, senha, salario);
        cadastrarUsuario(novoVendedor);
    }

    public void editarVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        isGerente();
        Vendedor novoVendedor = new Vendedor(nome, cpf, senha, salario);
        editarUsuario(novoVendedor);
    }
}
