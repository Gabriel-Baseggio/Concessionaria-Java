package net.weg.topcar.view;

import net.weg.topcar.controller.VeiculoController;

public class FormularioVeiculo extends Formulario {

    protected VeiculoController veiculoController;

    protected Long selecionaTipoDeVeiculo() {
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

    protected Long entradaCodigo() {
        return entradaInteiro.leiaComSaidaEValidacao("Código: ", saida);
    }

    protected Double entradaPreco() {
        return entradaDecimal.leiaComSaidaEValidacao("Preço: ", saida);
    }

    protected Double entradaPreco(Double preco) {
        Double novoPreco = entradaDecimal.leiaComSaida("Preço: ", saida);
        return novoPreco <= 0 ? preco : novoPreco;
    }

    protected String entradaPlaca() {
        return entradaTexto.leiaComSaidaEValidacao("Placa: ", saida);
    }

    protected String entradaPlaca(String placa) {
        String novaPlaca = entradaTexto.leiaComSaida("Placa: ", saida);
        return novaPlaca.isBlank() ? placa : novaPlaca;
    }

    protected Long entradaAno() {
        return entradaInteiro.leiaComSaidaEValidacao("Ano: ", saida);
    }

    protected Long entradaAno(Long ano) {
        Long novoAno = entradaInteiro.leiaComSaida("Ano: ", saida);
        return novoAno <= 0 ? ano : novoAno ;
    }

    protected String entradaModelo() {
        return entradaTexto.leiaComSaidaEValidacao("Modelo: ", saida);
    }

    protected String entradaModelo(String modelo) {
        String novoModelo = entradaTexto.leiaComSaida("Modelo: ", saida);
        return novoModelo.isBlank() ? modelo : novoModelo ;
    }

    protected String entradaMarca() {
        return entradaTexto.leiaComSaidaEValidacao("Marca: ", saida);
    }

    protected String entradaMarca(String marca) {
        String novaMarcha = entradaTexto.leiaComSaida("Marca: ", saida);
        return novaMarcha.isBlank() ? marca : novaMarcha ;
    }

    protected Double entradaQuilometragem() {
        return entradaDecimal.leiaComSaidaEValidacao("Quilometragem: ", saida);
    }

    protected Double entradaQuilometragem(Double quilometragem) {
        Double novaQuilometragem = entradaDecimal.leiaComSaida("Quilometragem: ", saida);
        return novaQuilometragem <= 0 ? quilometragem : novaQuilometragem;
    }

    protected Boolean entradaNovo() {
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

    protected String entradaCarroceria() {
        return entradaTexto.leiaComSaidaEValidacao("Carroceria: ", saida);
    }

    protected String entradaCarroceria(String carroceria) {
        String novaCarroceria = entradaTexto.leiaComSaida("Carroceria: ", saida);
        return novaCarroceria.isBlank() ? carroceria : novaCarroceria;
    }

    protected String entradaItensExtra() {
        return entradaTexto.leiaComSaidaEValidacao("Itens extra: ", saida);
    }

    protected String entradaItensExtra(String itensExtra) {
        String novosItensExtra = entradaTexto.leiaComSaida("Itens extra: ", saida);
        return novosItensExtra.isBlank() ? itensExtra : novosItensExtra;
    }

    protected String entradaTipoMotor() {
        return entradaTexto.leiaComSaidaEValidacao("Tipo do motor: ", saida);
    }

    protected String entradaTipoMotor(String tipoMotor) {
        String novoTipoMotor = entradaTexto.leiaComSaida("Tipo do motor: ", saida);
        return novoTipoMotor.isBlank() ? tipoMotor : novoTipoMotor;
    }

    protected Long entradaCilindradas() {
        return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);
    }

    protected Long entradaCilindradas(Long cilindradas) {
        Long novasCilindradas = entradaInteiro.leiaComSaida("Cilindradas: ", saida);
        return novasCilindradas <= 0 ? cilindradas : novasCilindradas;
    }

    protected Long entradaQtdMarchas() {
        return entradaInteiro.leiaComSaidaEValidacao("Quantidade de marchas: ", saida);
    }

    protected Long entradaQtdMarchas(Long qtdMarchas) {
        Long novaQtdMarchas = entradaInteiro.leiaComSaida("Quantidade de marchas: ", saida);
        return novaQtdMarchas <= 0 ? qtdMarchas : novaQtdMarchas;
    }

    protected String entradaTracao() {
        return entradaTexto.leiaComSaidaEValidacao("Tração: ", saida);
    }

    protected String entradaTracao(String tracao) {
        String novaTracao = entradaTexto.leiaComSaida("Tração: ", saida);
        return novaTracao.isBlank() ? tracao : novaTracao;
    }

}
