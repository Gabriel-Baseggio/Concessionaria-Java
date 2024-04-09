package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.service.VeiculoService;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class VeiculoController {

    private static Cliente usuarioLogado;
    private final VeiculoService veiculoService;
    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public void verVeiculos() {
        List<Veiculo> listaVeiculosDisponiveis = veiculoService.buscarVeiculosDisponiveis();

        listaVeiculosDisponiveis.forEach(veiculo -> {
            saida.escrevaL(veiculo.toString());
        });
    }

    public void verVeiculo() {
        try {
            Long codigo = entradaCodigo();
            Veiculo veiculo = veiculoService.buscarUm(codigo);
            saida.escrevaL(veiculo.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void removerVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();
            Long codigo = entradaCodigo();
            veiculoService.remover(codigo);
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void alterarPreco() throws PermissaoNegadaException {
        try {
            isGerente();

            Long codigo = entradaCodigo();

            Double preco = entradaPreco();
            veiculoService.alterarPreco(codigo, preco);
        } catch (ObjetoNaoEncontradoException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void cadastrarVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();

            Long codigo = entradaCodigo();
            Double preco = entradaPreco();
            Long ano = entradaAno();
            String modelo = entradaModelo();
            String marca = entradaMarca();
            Boolean novo = entradaNovo();

            Double quilometragem = 0.0;
            String placa = "";
            if (!novo){
                quilometragem = entradaQuilometragem();
                placa = entradaPlaca();
            }

            Long tipo = selecionaTipoDeVeiculo();
            Veiculo novoVeiculo;
            if (tipo == 1) {
                String carroceria = entradaCarroceria();
                String itensExtra = entradaItensExtra();
                novoVeiculo = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo,
                        carroceria, itensExtra);
            } else if (tipo == 2) {
                String tipoMotor = entradaTipoMotor();
                Long cilindradas = entradaCilindradas();
                Long qtdMarchas = entradaQtdMarchas();
                novoVeiculo = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor,
                        cilindradas, qtdMarchas);
            } else {
                String tracao = entradaTracao();
                novoVeiculo = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
            }
            veiculoService.adicionar(novoVeiculo);
        } catch (ObjetoExistenteException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void editarVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();
            Long codigo = entradaCodigo();
            Veiculo veiculo = veiculoService.buscarUm(codigo);

            Double preco = entradaPreco(veiculo.getPreco());

            Long ano = entradaAno(veiculo.getAno());
            String modelo = entradaModelo(veiculo.getModelo());
            String marca = entradaMarca(veiculo.getMarca());
            Boolean novo = entradaNovo();

            Double quilometragem = 0.0;
            String placa = "";
            if (!novo){
                quilometragem = entradaQuilometragem(veiculo.getQuilometragem());
                placa = entradaPlaca(veiculo.getPlaca());
            }

            Veiculo veiculoEditado;
            if (veiculo instanceof Carro carro) {
                String carroceria = entradaCarroceria(carro.getCarroceria());
                String itensExtra = entradaItensExtra(carro.getItensExtra());
                veiculoEditado = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
            } else if (veiculo instanceof Moto moto) {
                String tipoMotor = entradaTipoMotor(moto.getTipoMotor());
                Long cilindradas = entradaCilindradas(moto.getCilindradas());
                Long qtdMarchas = entradaQtdMarchas(moto.getQtdMarchas());
                veiculoEditado = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor,
                        cilindradas, qtdMarchas);
            } else {
                String tracao = entradaTracao(((Caminhao) veiculo).getTracao());
                veiculoEditado = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
            }
            veiculoService.alterar(codigo, veiculoEditado);
        } catch (ObjetoNaoEncontradoException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

}
