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
    private IBanco<Veiculo, Long> bancoVeiculos = new BancoVeiculos();

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    //Cliente
    public String cadastroUsuario() {
        try {
            Long cpf = entradaCPF();
            validarCPF(cpf);

            String nome = entradaNome();
            String senha = entradaSenha();

            if (usuarioLogado instanceof IGerente) {
                Long escolha = selecionaTipoDeUsuario();
                if (escolha == 1) {
                    Double salario = entradaSalario();
                    cadastroVendedor(nome, cpf, senha, salario);
                    return "Vendedor cadastrado com sucesso!";
                }
            }
            cadastroCliente(nome, cpf, senha);
            return "Cliente cadastrado com sucesso!";
        } catch (ObjetoExistenteException e) {
            return e.getMessage();
        }

    }

    //Cliente
    public void VerMeusVeiculos(){
        List<Veiculo> listaMeusVeiculos = usuarioLogado.verMeusVeiculos();
        listaMeusVeiculos.forEach(veiculo -> saida.escrevaL(veiculo.toString()));
    }

    //Vendedor
    public void verPagamento() {
        try {
            Vendedor vendedor = isVendedor();
            saida.escrevaL(vendedor.verPagamento());
        } catch (PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Vendedor
    public void verUsuario() {
        try {
            isVendedor();
            Cliente cliente = buscarUsuario();
            saida.escrevaL(cliente.toString());
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Vendedor
    public void vender() {
        try {
            Vendedor vendedor = isVendedor();

            saida.escrevaL("Identifique o comprador: ");
            Cliente cliente = buscarUsuario();

            saida.escrevaL("Identifique o veículo: ");
            Veiculo veiculo = buscarVeiculo();

            vendedor.vender(veiculo, cliente);

            atualizarEnvolvidosNaVenda(cliente, vendedor, veiculo);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException | FalhaNaCompraException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void removerUsuario() {
        try {
            isGerente();
            Long cpf = entradaCPF();
            bancoUsuarios.remover(cpf);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void editarUsuario() {
        try {
            isGerente();
            Cliente cliente = buscarUsuario();

            if (cliente instanceof Gerente) {
                throw new PermissaoNegadaException();
            }

            String nome = entradaNome(cliente.getNome());

            if (cliente instanceof Vendedor vendedor) {
                Double salario = entradaSalario(vendedor.getSalario());
                bancoUsuarios.alterar(cliente.getCPF(),
                        new Vendedor(nome, vendedor.getCPF(), vendedor.getSenha(), salario));
            } else {
                bancoUsuarios.alterar(cliente.getCPF(),
                        new Cliente(nome, cliente.getCPF(), cliente.getSenha()));
            }
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void verVendedores() {
        try {
            isGerente();
            List<Vendedor> listaVendedores = buscarVendedores();
            saida.escrevaL(listaVendedores.toString());
        } catch (PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void verClientes() {
        try {
            isGerente();
            List<Cliente> listaClientes = bancoUsuarios.buscarTodos();
            saida.escrevaL(listaClientes.toString());
        } catch (PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void verPagamentoVendedores() {
        try {
            isGerente();
            List<Vendedor> listaVendedores = buscarVendedores();
            listaVendedores.forEach(vendedor -> {
                saida.escrevaL(vendedor.verPagamentoComNome());
            });
        } catch (PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    //Gerente
    public void verPagamentoVendedor() {
        try {
            isGerente();

            Cliente cliente = buscarUsuario();

            if (cliente instanceof IVendedor vendedor) {
                saida.escrevaL(vendedor.verPagamento());
            } else {
                throw new TipoDeUsuarioInvalidoException(cliente);
            }
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException e) {
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

    private String entradaSenha() {
        String senha, confSenha;

        do {
            senha = entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
            confSenha = entradaTexto.leiaComSaidaEValidacao("Confirme a senha: ", saida);
        } while (!(senha.equals(confSenha)));

        return senha;
    }

    private Double entradaSalario() {
        return entradaDecimal.leiaComSaidaEValidacao("Salário: ", saida);
    }

    private Double entradaSalario(Double salario) {
        Double novoSalario = entradaDecimal.leiaComSaida("Salário: ", saida);
        return novoSalario <= 0 ? salario : novoSalario;
    }

    private Long selecionaTipoDeUsuario() {
        return entradaInteiro.leiaComSaidaEValidacao("""
                Qual o perfil de usuário que você deseja cadastrar?
                1 - Vendedor;
                2 - Cliente.
                """, saida);
    }

    private void cadastroVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Vendedor(nome, cpf, senha, salario));
    }

    private void cadastroCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Cliente(nome, cpf, senha));
    }

}
