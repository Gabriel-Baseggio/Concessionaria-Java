package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;

import java.util.Collections;
import java.util.List;

public class BancoUsuario implements IBanco<Cliente, String> {

    private List<Cliente> clientes;

    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(clientes);
    }

    public Cliente buscarUm(String cpf) throws ObjetoNaoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        throw new ObjetoNaoEncontradoException();
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public void remover(String cpf) throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        clientes.remove(cliente);
    }

    public void alterar(String cpf, Cliente novoCliente) throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        clientes.set(clientes.indexOf(cliente), novoCliente);
    }

}
