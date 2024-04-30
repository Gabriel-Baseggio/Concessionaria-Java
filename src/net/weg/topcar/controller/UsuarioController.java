package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.service.UsuarioService;

import java.util.List;

public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    protected void cadastrarUsuario(Cliente cliente) throws ObjetoExistenteException, UsuarioInvalidoException {
        if (cliente != null) {
            validarCPF(cliente.getCPF());
            usuarioService.adicionar(cliente);
        }
        throw new UsuarioInvalidoException("Usuário nulo!");
    }

    public void editarUsuario(Cliente cliente) throws PermissaoNegadaException, ObjetoNaoEncontradoException {
        usuarioService.alterar(cliente.getCPF(), cliente);
    }

    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioService.buscarUm(cpf);
    }

    public List<Veiculo> verMeusVeiculos() {
        return usuarioService.meusVeiculos();
    }

    public void validarCPF(Long cpf) throws UsuarioExistenteException {
        if (usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }

    private Vendedor isVendedor() throws PermissaoNegadaException {
        if (UsuarioAutenticadoBack.getUsuario() instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadaException("O usuário não é um vendedor.");
    }

    protected void isGerente() throws PermissaoNegadaException {
        if (!(UsuarioAutenticadoBack.getUsuario() instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

    public void remover(Long cpf) throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        isGerente();
        usuarioService.remover(cpf);
    }

    public void vender(Long cpfCliente, Long codigo) throws FalhaNaVendaException, PermissaoNegadaException,
            ObjetoNaoEncontradoException {
        isVendedor();
        usuarioService.vender(cpfCliente, codigo);
    }

    public String buscarPagamento(Long cpf) throws ObjetoNaoEncontradoException, TipoDeUsuarioInvalidoException,
            PermissaoNegadaException {
        isGerente();
        return usuarioService.buscarPagamento(cpf);
    }

    public String buscarPagamento() throws PermissaoNegadaException, ObjetoNaoEncontradoException {
        Vendedor vendedor = isVendedor();
        return usuarioService.buscarPagamento(vendedor.getCPF());
    }

    public List<Vendedor> buscarVendedores() throws PermissaoNegadaException {
        isGerente();
        return usuarioService.buscarVendedores();
    }

    public List<Cliente> buscarUsuarios() throws PermissaoNegadaException {
        isGerente();
        return usuarioService.buscarUsuarios();
    }

    public List<String> buscarPagamentoVendedores() throws PermissaoNegadaException {
        isGerente();
        return usuarioService.buscarPagamentoVendedores();
    }


}
