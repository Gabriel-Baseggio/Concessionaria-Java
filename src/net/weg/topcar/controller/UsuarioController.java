package net.weg.topcar.controller;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.service.UsuarioService;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private static Cliente usuarioLogado;
    private IBanco<Cliente, Long> bancoUsuarios;
    private IBanco<Veiculo, Long> bancoVeiculos;

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();
    private UsuarioService usuarioService;
    private final Autenticacao autenticacao;

    public UsuarioController(IBanco<Cliente, Long> bancoUsuarios, IBanco<Veiculo, Long> bancoVeiculos) {
        this.bancoUsuarios = bancoUsuarios;
        this.bancoVeiculos = bancoVeiculos;
        this.autenticacao = new Autenticacao(bancoUsuarios);
    }

    //Cliente
    public List<Veiculo> verMeusVeiculos() {
        return usuarioLogado.verMeusVeiculos();
    }

    public Cliente adicionar(Cliente novoCliente) throws ObjetoExistenteException {
        if (novoCliente != null) {
            validarCPF(novoCliente.getCPF());
            return usuarioService.adicionar(novoCliente);
        }
        throw new UsuarioInvalidoException("Usuário nulo!");
    }

    //Vendedor
    public void verPagamento() throws PermissaoNegadaException {
        Vendedor vendedor = isVendedor();
        saida.escrevaL(vendedor.verPagamento());
    }

    //Vendedor
    public void verUsuario() throws PermissaoNegadaException {
        try {
            isVendedor();
            Cliente cliente = buscarUsuario();
            saida.escrevaL(cliente.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Vendedor
    public void vender() throws PermissaoNegadaException {
        try {
            Vendedor vendedor = isVendedor();

            saida.escrevaL("Identifique o comprador: ");
            Cliente cliente = buscarUsuario();

            saida.escrevaL("Identifique o veículo: ");
            Veiculo veiculo = buscarVeiculo();

            vendedor.vender(veiculo, cliente);

            atualizarEnvolvidosNaVenda(cliente, vendedor, veiculo);
        } catch (ObjetoNaoEncontradoException | FalhaNaCompraException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void removerUsuario() throws PermissaoNegadaException {
        try {
            isGerente();
            Long cpf = entradaCPF();
            bancoUsuarios.remover(cpf);
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        isGerente();
        usuarioService.alterar(clienteEditado);
    }

    //Gerente
    public void verVendedores() throws PermissaoNegadaException {
        isGerente();
        List<Vendedor> listaVendedores = buscarVendedores();
        saida.escrevaL(listaVendedores.toString());
    }

    //Gerente
    public void verClientes() throws PermissaoNegadaException {
        isGerente();
        List<Cliente> listaClientes = bancoUsuarios.buscarTodos();
        saida.escrevaL(listaClientes.toString());
    }

    //Gerente
    public void verPagamentoVendedores() throws PermissaoNegadaException {
        isGerente();
        List<Vendedor> listaVendedores = buscarVendedores();
        listaVendedores.forEach(vendedor -> {
            saida.escrevaL(vendedor.verPagamentoComNome());
        });
    }

    private void atualizarEnvolvidosNaVenda(Cliente cliente, Vendedor vendedor, Veiculo veiculo) throws ObjetoNaoEncontradoException {
        bancoUsuarios.alterar(cliente.getCPF(), cliente); // Carro cliente
        bancoUsuarios.alterar(vendedor.getCPF(), vendedor); // Comissão
        bancoVeiculos.alterar(veiculo.getCODIGO(), veiculo); // Vendido
    }

    private List<Vendedor> buscarVendedores() {
        List<Cliente> listaClientes = bancoUsuarios.buscarTodos();
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

    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioService.buscarUm(cpf);
    }

    private Veiculo buscarVeiculo() throws ObjetoNaoEncontradoException {
        Long codigo = entradaCodigo();
        return bancoVeiculos.buscarUm(codigo);
    }

    public void validarCPF(Long cpf) throws UsuarioExistenteException {
        if (usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }

    private void cadastrarVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Vendedor(nome, cpf, senha, salario));
    }

    private void cadastrarCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Cliente(nome, cpf, senha));
    }
}
