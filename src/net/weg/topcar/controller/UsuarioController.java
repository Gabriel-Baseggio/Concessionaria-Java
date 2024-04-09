package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.service.UsuarioService;

import java.util.List;

public class UsuarioController {
    private UsuarioService usuarioService;

    public Cliente adicionar(Cliente novoCliente) throws ObjetoExistenteException {
        if (novoCliente != null) {
            validarCPF(novoCliente.getCPF());
            return usuarioService.adicionar(novoCliente);
        }
        throw new UsuarioInvalidoException("Usuário nulo!");
    }

    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioService.buscarUm(cpf);
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        isGerente();
        usuarioService.alterar(clienteEditado);
    }

    //Cliente
    public List<Veiculo> verMeusVeiculos() {
        return usuarioService.meusVeiculos();
    }

    public void validarCPF(Long cpf) throws UsuarioExistenteException {
        if (usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }

    private Vendedor isVendedor() throws PermissaoNegadaException {
        if (usuarioLogado instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadaException("O usuário não é um vendedor.");
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

    public void remover(Long cpf) throws ObjetoNaoEncontradoException {

    }

    public void vender(Long cpf, Long codigo) throws FalhaNaVendaException {

    }

    public String buscarPagamento(Long cpf) throws ObjetoNaoEncontradoException,
            TipoDeUsuarioInvalidoException {
    }
}
