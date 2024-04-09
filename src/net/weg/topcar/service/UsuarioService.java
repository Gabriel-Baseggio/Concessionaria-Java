package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;

public class UsuarioService {

    private IBanco<Cliente, Long> usuarioBanco;

    public Cliente adicionar(Cliente novoCliente) throws ObjetoExistenteException {
        return usuarioBanco.adicionar(novoCliente);
    }

    public boolean existe(Long cpf) {
        return usuarioBanco.existe(cpf);
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        usuarioBanco.alterar(clienteEditado.getCPF(), clienteEditado);
    }
}
