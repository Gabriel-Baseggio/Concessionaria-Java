package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoUsuarios implements IBanco<Cliente, Long> {

    private List<Cliente> clientes;

    public BancoUsuarios() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(clientes);
    }

    @Override
    public Cliente buscarUm(Long cpf) throws UsuarioNaoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        throw new UsuarioNaoEncontradoException(cpf);
    }

    @Override
    public void adicionar(Cliente cliente) throws UsuarioExistenteException {
        try {
            this.buscarUm(cliente.getCPF());
            throw new UsuarioExistenteException(cliente.getCPF());
        } catch (ObjetoNaoEncontradoException exception){
            clientes.add(cliente);
        }
    }

    @Override
    public void remover(Long cpf) throws UsuarioNaoEncontradoException, PermissaoNegadaException {
        try {
            Cliente cliente = this.buscarUm(cpf);
            if(cliente instanceof Gerente){
                throw new PermissaoNegadaException("O usuário é um gerente!");
            }
            clientes.remove(cliente);
        } catch (ObjetoNaoEncontradoException exception) {
            throw new UsuarioNaoEncontradoException(cpf);
        }
    }

    @Override
    public Boolean existe(Long cpf) {
        try {
            buscarUm(cpf);
            return true;
        } catch (UsuarioNaoEncontradoException exception) {
            return false;
        }
    }

    @Override
    public void alterar(Long cpf, Cliente novoCliente) throws UsuarioNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        clientes.set(clientes.indexOf(cliente), novoCliente);
    }

}
