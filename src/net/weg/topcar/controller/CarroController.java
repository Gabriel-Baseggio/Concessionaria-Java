package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.service.AutenticacaoService;
import net.weg.topcar.service.VeiculoService;

public class CarroController extends VeiculoController {


    public CarroController(VeiculoService veiculoService, AutenticacaoService autenticacaoService) {
        super(veiculoService, autenticacaoService);
    }

    public void cadastrarCarro(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                               Double quilometragem, String placa, String carroceria, String itensExtra)
            throws ObjetoExistenteException, PrecoInvalidoException {
        isGerente();
        Carro novoCarro = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
        cadastrarVeiculo(novoCarro);
    }

    public void editarCarro(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                            Double quilometragem, String placa, String carroceria, String itensExtra)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException {
        isGerente();
        buscarVeiculo(codigo);
        Carro carroEditado = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
        editarVeiculo(carroEditado);
    }

}
