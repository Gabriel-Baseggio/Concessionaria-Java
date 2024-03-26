package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class VeiculoController {

    private static Cliente usuarioLogado;
    private IBanco<Cliente, Long> bancoUsuarios = new BancoUsuarios();
    private IBanco<Veiculo, Long> bancoVeiculos = new BancoVeiculos();

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public void verVeiculos() {
        List<Veiculo> listaVeiculos = bancoVeiculos.buscarTodos();
        List<Veiculo> listaVeiculosDisponiveis = filtrarVeiculosDisponiveis(listaVeiculos);

        listaVeiculosDisponiveis.forEach(veiculo -> {
            saida.escrevaL(veiculo.toString());
        });
    }

    public void verVeiculo() {
        try {
            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);
            saida.escrevaL(veiculo.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void removerVeiculo() {
        try {
            isGerente();
            Long codigo = entradaCodigo();
            bancoVeiculos.remover(codigo);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void alterarPreco() {
        try {
            isGerente();

            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);

            Double preco = entradaPreco(veiculo.getPreco());
            veiculo.setPreco(preco);

            bancoVeiculos.alterar(codigo, veiculo);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void cadastroVeiculo() {
        try {
            isGerente();

            Long codigo = entradaCodigo();
            validarCodigo(codigo);

            Double preco = entradaPreco();
            validarPreco(preco);

            String placa = entradaPlaca();
            Long ano = entradaAno();
            String modelo = entradaModelo();
            String marca = entradaMarca();
            Double quilometragem = entradaQuilometragem();
            Boolean novo = entradaNovo();

            Long tipo = selecionaTipoDeVeiculo();
            if (tipo == 1) {
                String carroceria = entradaCarroceria();
                String itensExtra = entradaItensExtra();
                cadastroCarro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
            } else if (tipo == 2) {
                String tipoMotor = entradaTipoMotor();
                Long cilindradas = entradaCilindradas();
                Long qtdMarchas = entradaQtdMarchas();
                cadastroMoto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor,
                        cilindradas, qtdMarchas);
            } else {
                String tracao = entradaTracao();
                cadastroCaminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
            }

        } catch (PermissaoNegadaException | ObjetoExistenteException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    private void validarCodigo(Long codigo) throws VeiculoExistenteException {
        if (bancoVeiculos.existe(codigo)) {
            throw new VeiculoExistenteException(codigo);
        }
    }

    private void validarPreco(Double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
    }

    private Long selecionaTipoDeVeiculo() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Qual o tipo de veículo que você deseja cadastrar?
                    1 - Carro;
                    2 - Moto;
                    3 - Caminhão.
                    """, saida);
        } while (entrada > 2);
        return entrada;
    }

    private void cadastroCarro(Long codigo, Double preco, String placa, Long ano, String modelo, String marca,
                               Double quilometragem, Boolean novo, String carroceria,
                               String itensExtra) throws PrecoInvalidoException, ObjetoExistenteException {
        Carro carro = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
        bancoVeiculos.adicionar(carro);
    }

    private void cadastroMoto(Long codigo, Double preco, String placa, Long ano, String modelo, String marca,
                              Double quilometragem, Boolean novo, String tipoMotor, Long cilindradas,
                              Long qtdMarchas) throws PrecoInvalidoException, ObjetoExistenteException {
        Moto moto = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor, cilindradas,
                qtdMarchas);
        bancoVeiculos.adicionar(moto);
    }

    private void cadastroCaminhao(Long codigo, Double preco, String placa, Long ano, String modelo, String marca,
                               Double quilometragem, Boolean novo,
                                  String tracao) throws PrecoInvalidoException, ObjetoExistenteException {
        Caminhao caminhao = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
        bancoVeiculos.adicionar(caminhao);
    }

    private Long entradaCodigo() {
        return entradaInteiro.leiaComSaidaEValidacao("Código: ", saida);
    }

    private Double entradaPreco() {
        return entradaDecimal.leiaComSaidaEValidacao("Preço: ", saida);
    }

    private Double entradaPreco(Double preco) {
        Double novoPreco = entradaDecimal.leiaComSaida("Preço: ", saida);
        return novoPreco <= 0 ? preco : novoPreco;
    }

    private String entradaPlaca() {
        return entradaTexto.leiaComSaidaEValidacao("Placa: ", saida);
    }

    private Long entradaAno() {
        return entradaInteiro.leiaComSaidaEValidacao("Ano: ", saida);
    }

    private String entradaModelo() {
        return entradaTexto.leiaComSaidaEValidacao("Modelo: ", saida);
    }

    private String entradaMarca() {
        return entradaTexto.leiaComSaidaEValidacao("Marca: ", saida);
    }

    private Double entradaQuilometragem() {
        return entradaDecimal.leiaComSaidaEValidacao("Quilometragem: ", saida);
    }

    private Boolean entradaNovo() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Veículo 0km?
                    1 - Sim;
                    2 - Não.
                    """, saida);
        } while (entrada > 2);
        return entrada == 1;
    }

    private String entradaCarroceria() {
        return entradaTexto.leiaComSaidaEValidacao("Carroceria: ", saida);
    }

    private String entradaItensExtra() {
        return entradaTexto.leiaComSaidaEValidacao("Itens extra: ", saida);
    }

    private String entradaTipoMotor() {
        return entradaTexto.leiaComSaidaEValidacao("Tipo do motor: ", saida);
    }
    private Long entradaCilindradas() {
        return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);
    }

    private Long entradaQtdMarchas() {
        return entradaInteiro.leiaComSaidaEValidacao("Quantidade de marchas: ", saida);
    }

    private String entradaTracao() {
        return entradaTexto.leiaComSaidaEValidacao("Tração: ", saida);
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

    private List<Veiculo> filtrarVeiculosDisponiveis(List<Veiculo> listaVeiculos) {
        List<Veiculo> listaVeiculosDisponiveis = new ArrayList<>();
        listaVeiculos.forEach(veiculo -> {
            if (!veiculo.isVendido()) {
                listaVeiculosDisponiveis.add(veiculo);
            }
        });
        return listaVeiculosDisponiveis;
    }

}
