package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.OpcaoInvalidaException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.Entrada;
import net.weg.topcar.view.EntradaInteiro;
import net.weg.topcar.view.EntradaTexto;
import net.weg.topcar.view.Saida;

public class MenuController {

    private Cliente usuarioLogado;
    private final BancoUsuarios bancoUsuarios = new BancoUsuarios();
    private final BancoVeiculos bancoVeiculos = new BancoVeiculos();

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    //    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    private final UsuarioController usuarioController = new UsuarioController(bancoUsuarios, bancoVeiculos);
    private final VeiculoController veiculoController = new VeiculoController(bancoVeiculos);

    public void menuInicial() {
        do {
            Long escolha;
            saida.escreva("""
                    Bem-vindo à Concessionária TopCar
                                    
                    1 - Cadastrar cliente;
                    2 - Fazer login;
                    3 - Ver veículos em estoque;
                    4 - Ver detalhes de um veículo;
                    0 - Sair.
                    >\s""");

            do {
                escolha = entradaInteiro.leia();
                acaoMenuInicial(escolha.intValue());
            } while (escolha < 0 || escolha > 4);
        } while (true);

    }

    private void acaoMenuInicial(int escolha) {
        switch (escolha) {
            case 0 -> {
                saida.escrevaL("Obrigado por usar nosso sistema!");
                System.exit(0);
            }
            case 1 -> usuarioController.cadastrarUsuario();
            case 2 -> login();
            case 3 -> veiculoController.verVeiculos();
            case 4 -> veiculoController.verVeiculo();
            default -> {
                try {
                    throw new OpcaoInvalidaException();
                } catch (OpcaoInvalidaException e) {
                    saida.escrevaL(e.getMessage() + "\nDigite novamente!");
                }
            }
        }
    }

    private void login() {

        try {
            usuarioLogado = usuarioController.login();
            menuDoUsuario();
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }

    }

    private void menuDoUsuario() {

        do {
            saida.escrevaL(usuarioLogado.menu());
            saida.escrevaL("0 - Logout.");
            int escolha = (entradaInteiro.leia()).intValue();

            try {
                if (escolha < 0 || escolha > 17) {
                    throw new OpcaoInvalidaException();
                } else if (escolha > 3 && !(usuarioLogado instanceof Vendedor)) {
                    throw new PermissaoNegadaException();
                } else if (escolha > 6 && !(usuarioLogado instanceof Gerente)) {
                    throw new PermissaoNegadaException();
                }
            } catch (OpcaoInvalidaException | PermissaoNegadaException exception) {
                saida.escrevaL(exception.getMessage());
            }

            try {
                switch (escolha) {
                    case 0 -> usuarioLogado = null;
                    case 1 -> veiculoController.verVeiculos();
                    case 2 -> veiculoController.verVeiculo();
                    case 3 -> usuarioController.verMeusVeiculos();
                    case 4 -> usuarioController.vender();
                    case 5 -> usuarioController.verUsuario();
                    case 6 -> usuarioController.verPagamento();
                    case 7 -> veiculoController.cadastrarVeiculo();
                    case 8 -> veiculoController.removerVeiculo();
                    case 9 -> veiculoController.editarVeiculo();
                    case 10 -> veiculoController.alterarPreco();
                    case 11 -> usuarioController.cadastrarUsuario();
                    case 12 -> usuarioController.removerUsuario();
                    case 13 -> usuarioController.editarUsuario();
                    case 14 -> usuarioController.verVendedores();
                    case 15 -> usuarioController.verClientes();
                    case 16 -> usuarioController.verPagamentoVendedores();
                    case 17 -> usuarioController.verPagamentoVendedor();
                }
            } catch (PermissaoNegadaException e) {
                saida.escrevaL("Opção inválida!");
            }

        } while (usuarioLogado != null);

    }

}
