package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
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

    public void verVeiculos(){
        List<Veiculo> listaVeiculos = bancoVeiculos.buscarTodos();
        List<Veiculo> listaVeiculosDisponiveis = filtrarVeiculosDisponiveis(listaVeiculos);

        listaVeiculosDisponiveis.forEach(veiculo -> {
            saida.escrevaL(veiculo.toString());
        });
    }

    public void verVeiculo(){
        try {
            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);
            saida.escrevaL(veiculo.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void removerVeiculo(){
        try {
            isGerente();
            Long codigo = entradaCodigo();
            bancoVeiculos.remover(codigo);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException e){
            saida.escrevaL(e.getMessage());
        }
    }

    public void alterarPreco(){
        try {
            isGerente();

            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);

            Double preco = entradaPreco(veiculo.getPreco());
            veiculo.setPreco(preco);

            bancoVeiculos.alterar(codigo, veiculo);
        } catch (PermissaoNegadaException | ObjetoNaoEncontradoException | PrecoInvalidoException e){
            saida.escrevaL(e.getMessage());
        }
    }

    public void cadastroVeiculo(){
        try{

            isGerente();

            Long codigo = entradaCodigo();
            validaCodigo(codigo);

            Double preco = entradaPreco();
            String placa = entradaPlaca();
            Long ano = entradaAno();
            String modelo = entradaModelo();
            String marca = entradaMarca();
            Double quilometragem = entradaQuilometragem();
            Boolean novo = entradaEstado();

        } catch (PermissaoNegadaException | ObjetoExistenteException e){
            saida.escrevaL(e.getMessage());
        }
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
        return entradaTexto.leiaComSaidaEValidacao("Placa: ", saida)
    }

    private Long entradaAno() {
    }

    private String entradaModelo() {
    }

    private String entradaMarca() {
    }

    private Double entradaQuilometragem() {
    }

    private Boolean entradaEstado() {
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

    private List<Veiculo> filtrarVeiculosDisponiveis(List<Veiculo> listaVeiculos){
        List<Veiculo> listaVeiculosDisponiveis = new ArrayList<>();
        listaVeiculos.forEach(veiculo -> {
            if(!veiculo.isVendido()){
                listaVeiculosDisponiveis.add(veiculo);
            }
        });
        return listaVeiculosDisponiveis;
    }

}
