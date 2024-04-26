package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.service.AutenticacaoService;
import net.weg.topcar.service.VeiculoService;

public class CaminhaoController extends VeiculoController {


    public CaminhaoController(VeiculoService veiculoService, AutenticacaoService autenticacaoService) {
        super(veiculoService, autenticacaoService);
    }

    public void cadastrarCaminhao(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                                  Double quilometragem, String placa, String tracao)
            throws PrecoInvalidoException, ObjetoExistenteException {
        isGerente();
        Caminhao novoCaminhao = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
        cadastrarVeiculo(novoCaminhao);
    }

    public void editarCaminhao(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                               Double quilometragem, String placa, String tracao)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException {
        isGerente();
        buscarVeiculo(codigo);
        Caminhao caminhaoEditado = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
        editarVeiculo(caminhaoEditado);
    }

}
