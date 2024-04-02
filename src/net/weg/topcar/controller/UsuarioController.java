package net.weg.topcar.controller;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.model.veiculos.Veiculo;
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
    private final Autenticacao autenticacao;

    public UsuarioController(IBanco<Cliente, Long> bancoUsuarios, IBanco<Veiculo, Long> bancoVeiculos) {
        this.bancoUsuarios = bancoUsuarios;
        this.bancoVeiculos = bancoVeiculos;
        this.autenticacao = new Autenticacao(bancoUsuarios);
    }

    //Cliente
    public void cadastrarUsuario() throws PermissaoNegadaException {
        try {
            Long cpf = entradaCPF();
            validarCPF(cpf);

            String nome = entradaNome();
            String senha = entradaSenhaComConfirmacao();

            Cliente novoCliente = null;
            if (usuarioLogado instanceof IGerente) {
                Long tipo = selecionaTipoDeUsuario();
                if (tipo == 1) {
                    Double salario = entradaSalario();
                    novoCliente = new Vendedor(nome, cpf, senha, salario);
                }
            }
            if (novoCliente == null) {
                novoCliente = new Cliente(nome, cpf, senha);
            }
            bancoUsuarios.adicionar(novoCliente);
            saida.escrevaL("Usuário cadastrado com sucesso!");
        } catch (ObjetoExistenteException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Cliente
    public Cliente login() throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        String senha = entradaSenhaSemConfirmacao();

        usuarioLogado = autenticacao.login(cpf, senha);
        return usuarioLogado;
    }

    //Cliente
    public void verMeusVeiculos() {
        List<Veiculo> listaMeusVeiculos = usuarioLogado.verMeusVeiculos();
        listaMeusVeiculos.forEach(veiculo -> saida.escrevaL(veiculo.toString()));
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

    //Gerente
    public void editarUsuario() throws PermissaoNegadaException {
        try {
            isGerente();
            Cliente cliente = buscarUsuario();

            if (cliente instanceof Gerente) {
                throw new PermissaoNegadaException();
            }

            String nome = entradaNome(cliente.getNome());

            Long cpf = cliente.getCPF();
            Cliente clienteEditado;
            if (cliente instanceof Vendedor vendedor) {
                Double salario = entradaSalario(vendedor.getSalario());
                clienteEditado = new Vendedor(nome, cpf, vendedor.getSenha(), salario);
            } else {
                clienteEditado = new Cliente(nome, cpf, cliente.getSenha());
            }
            bancoUsuarios.alterar(cpf, clienteEditado);
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
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

    //Gerente
    public void verPagamentoVendedor() throws PermissaoNegadaException {
        try {
            isGerente();

            Cliente cliente = buscarUsuario();

            if (cliente instanceof IVendedor vendedor) {
                saida.escrevaL(vendedor.verPagamento());
            } else {
                throw new TipoDeUsuarioInvalidoException(cliente);
            }
        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
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

    private Cliente buscarUsuario() throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        return bancoUsuarios.buscarUm(cpf);
    }

    private Veiculo buscarVeiculo() throws ObjetoNaoEncontradoException {
        Long codigo = entradaCodigo();
        return bancoVeiculos.buscarUm(codigo);
    }

    private Long entradaCPF() {
        return entradaInteiro.leiaComSaidaEValidacao("Cpf: ", saida);
    }

    private void validarCPF(Long cpf) throws UsuarioExistenteException {
        if (bancoUsuarios.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }

    private Long entradaCodigo() {
        return entradaInteiro.leiaComSaidaEValidacao("Código: ", saida);
    }

    private String entradaNome() {
        return entradaTexto.leiaComSaidaEValidacao("Nome: ", saida);
    }

    private String entradaNome(String nome) {
        String novoNome = entradaTexto.leiaComSaida("Nome: ", saida);
        return novoNome.isBlank() ? nome : novoNome;
    }

    private String entradaSenhaComConfirmacao() {
        String senha, confSenha;

        do {
            senha = entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
            confSenha = entradaTexto.leiaComSaidaEValidacao("Confirme a senha: ", saida);
        } while (!(senha.equals(confSenha)));

        return senha;
    }

    private String entradaSenhaSemConfirmacao() {
        return entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
    }


    private Double entradaSalario() {
        return entradaDecimal.leiaComSaidaEValidacao("Salário: ", saida);
    }

    private Double entradaSalario(Double salario) {
        Double novoSalario = entradaDecimal.leiaComSaida("Salário: ", saida);
        return novoSalario <= 0 ? salario : novoSalario;
    }

    private Long selecionaTipoDeUsuario() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Qual o tipo de usuário que você deseja cadastrar?
                    1 - Vendedor;
                    2 - Cliente.
                    """, saida);
        } while (entrada > 2);
        return entrada;
    }

    private void cadastrarVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Vendedor(nome, cpf, senha, salario));
    }

    private void cadastrarCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Cliente(nome, cpf, senha));
    }

}
