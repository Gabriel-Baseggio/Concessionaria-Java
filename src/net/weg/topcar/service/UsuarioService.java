package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.FalhaNaVendaException;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private IBanco<Cliente, Long> usuarioBanco;
    private VeiculoService veiculoService;

    public UsuarioService(IBanco<Cliente, Long> usuarioBanco, VeiculoService veiculoService) {
        this.usuarioBanco = usuarioBanco;
        this.veiculoService = veiculoService;
    }

    public Cliente adicionar(Cliente novoCliente) throws ObjetoExistenteException {
        return usuarioBanco.adicionar(novoCliente);
    }

    public boolean existe(Long cpf) {
        return usuarioBanco.existe(cpf);
    }

    public void alterar(Long cpf, Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        usuarioBanco.alterar(cpf, clienteEditado);
    }

    public Cliente buscarUm(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioBanco.buscarUm(cpf);
    }

    public List<Veiculo> meusVeiculos() {
        return UsuarioAutenticadoBack.getUsuario().verMeusVeiculos();
    }

    public void vender(Long cpfCliente, Long codigo) throws ObjetoNaoEncontradoException, FalhaNaVendaException {
        Cliente cliente = buscarUm(cpfCliente);
        Vendedor vendedor = (Vendedor) UsuarioAutenticadoBack.getUsuario();
        Veiculo veiculo = veiculoService.buscarUm(codigo);

        vendedor.vender(veiculo, cliente);

        usuarioBanco.alterar(cliente.getCPF(), cliente); // Carro cliente
        usuarioBanco.alterar(vendedor.getCPF(), vendedor); // Comissão
        veiculoService.alterar(veiculo.getCODIGO(), veiculo); // Vendido
    }

    public List<Vendedor> buscarVendedores() {
        List<Cliente> listaClientes = usuarioBanco.buscarTodos();
        return filtrarVendedores(listaClientes);
    }

    private List<Vendedor> filtrarVendedores(List<Cliente> listaClientes) {
        List<Vendedor> listaVendedores = new ArrayList<>();

        listaClientes.forEach(cliente -> {
            if (cliente instanceof Vendedor vendedor) {
                listaVendedores.add(vendedor);
            }
        });

        return listaVendedores;
    }

    public void remover(Long cpf) throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        usuarioBanco.remover(cpf);
    }

    public String buscarPagamento(Long cpf) throws ObjetoNaoEncontradoException {
        Vendedor vendedor = (Vendedor) buscarUm(cpf);
        return vendedor.verPagamento();
    }

    public List<Cliente> buscarUsuarios() {
        return usuarioBanco.buscarTodos();
    }

    public List<String> buscarPagamentoVendedores() {
        List<Vendedor> listaVendedores = buscarVendedores();
        Gerente gerente = (Gerente) UsuarioAutenticadoBack.getUsuario();
        return gerente.verPagamentoVendedores(listaVendedores);
    }
}
