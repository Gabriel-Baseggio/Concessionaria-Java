package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private static Cliente usuarioLogado;
    private IBanco<Cliente, Long> bancoUsuarios = new BancoUsuarios();
    private IBanco<Veiculo, Integer> bancoVeiculos = new BancoVeiculos();

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public String cadastroUsuario() {

        Long cpf = entradaCPF();
        try {
            validaCPF(cpf);
        } catch (UsuarioExistenteException exception) {
            saida.escreval(exception.getMessage());
        }
        String nome = entradaNome();
        String senha = entradaSenhaComConfirmacao();

        try {
            if (usuarioLogado instanceof IGerente) {
                Long escolha = selecionaTipoDeUsuario();
                if (escolha == 1) {
                    cadastroVendedor(nome, cpf, senha);
                    return "Vendedor cadastrado com sucesso!";
                }
            }
            cadastroCliente(nome, cpf, senha);
            return "Cliente cadastrado com sucesso!";
        } catch (ObjetoExistenteException exception) {
            return exception.getMessage();
        }

    }

    public void validaCPF(Long cpf) throws UsuarioExistenteException {
        if (bancoUsuarios.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }

    public void removerUsuario() {
        if (usuarioLogado instanceof IGerente) {
            try {
                Long cpf = entradaCPF();
                bancoUsuarios.remover(cpf);
            } catch (ObjetoNaoEncontradoException | AcessoNegadoException exception) {
                saida.escreval(exception.getMessage());
            }
        } else {
            throw new AcessoNegadoException();
        }
    }

    private List<Vendedor> filtrarVendedores(List<Cliente> clientes) {
        List<Vendedor> vendedores = new ArrayList<>();

        clientes.forEach(cliente -> {
            if (cliente instanceof Vendedor vendedor) {
                vendedores.add(vendedor);
            }
        });

        return vendedores;
    }

    public void verVendedores() {
        if (usuarioLogado instanceof IGerente) {
            List<Vendedor> vendedores = buscarVendedores();

            saida.escreval(vendedores.toString());
        } else {
            throw new AcessoNegadoException();
        }
    }

    public void verClientes() {
        if (usuarioLogado instanceof IGerente) {
            List<Cliente> clientes = bancoUsuarios.buscarTodos();
            saida.escreval(clientes.toString());
        } else {
            throw new AcessoNegadoException();
        }
    }

    public void verPagamentoVendedores() {
        if (usuarioLogado instanceof IGerente) {
            List<Vendedor> vendedores = buscarVendedores();

            vendedores.forEach(vendedor -> {
                saida.escreval(vendedor.verPagamentoComNome());
            });
        } else {
            throw new AcessoNegadoException();
        }
    }

    public void verPagamentoVendedor() {
        if (usuarioLogado instanceof IGerente) {
            try {
                Cliente cliente = buscarUsuario();
                if (cliente instanceof IVendedor vendedor) {
                    saida.escreval(vendedor.verPagamento());
                } else {
                    throw new TipoDeUsuarioInvalidoException(cliente);
                }
            } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException exception) {
                saida.escreval(exception.getMessage());
            }

        } else {
            throw new AcessoNegadoException();
        }
    }

    private Cliente buscarUsuario() throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        return bancoUsuarios.buscarUm(cpf);
    }

    public void verUsuario() {
        if (usuarioLogado instanceof IVendedor) {
            try {
                Cliente cliente = buscarUsuario();
                saida.escreval(cliente.toString());
            } catch (ObjetoNaoEncontradoException exception) {
                saida.escreval(exception.getMessage());
            }
        } else {
            throw new AcessoNegadoException();
        }
    }

    public void verPagamento() {
        if (usuarioLogado instanceof IVendedor vendedor) {
            saida.escreval(vendedor.verPagamento());
        }
    }

    public void vender() {
        if (usuarioLogado instanceof IVendedor vendedor) {
            saida.escreval("Identifique o comprador: ");
            try {
                Cliente cliente = buscarUsuario();
                saida.escreval("Identifique o veículo: ");
                Integer codigo = Math.toIntExact(entradaInteiro.leiaComSaidaEValidacao("Código: ", saida));
                Veiculo veiculo = bancoVeiculos.buscarUm(codigo);
                vendedor.vender(veiculo, cliente);
                bancoUsuarios.alterar(cliente.getCPF(), cliente);
                bancoVeiculos.alterar(veiculo.getCODIGO(), veiculo);
            } catch (ObjetoNaoEncontradoException exception) {
                saida.escreval(exception.getMessage());
            }
        }
    }

    private List<Vendedor> buscarVendedores() {
        List<Cliente> clientes = bancoUsuarios.buscarTodos();
        return filtrarVendedores(clientes);
    }

    private Long entradaCPF() {
        return entradaInteiro.leiaComSaidaEValidacao("Cpf: ", saida);
    }

    private String entradaNome() {
        return entradaTexto.leiaComSaidaEValidacao("Nome: ", saida);
    }

    private String entradaSenhaComConfirmacao() {
        String senha, confSenha;

        do {
            senha = entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
            confSenha = entradaTexto.leiaComSaidaEValidacao("Confirme a senha: ", saida);
        } while (!(senha.equals(confSenha)));

        return senha;
    }

    private String entradaSenha() {
        return entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
    }

    private Double entradaSalario() {
        return entradaDecimal.leiaComSaidaEValidacao("Salário: ", saida);
    }

    private Long selecionaTipoDeUsuario() {
        return entradaInteiro.leiaComSaidaEValidacao("""
                Qual o perfil de usuário que você deseja cadastrar?
                1 - Vendedor;
                2 - Cliente.
                """, saida);
    }

    private void cadastroVendedor(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        Double salario = entradaSalario();
        bancoUsuarios.adicionar(new Vendedor(nome, cpf, senha, salario));
    }

    private void cadastroCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Cliente(nome, cpf, senha));
    }

}
