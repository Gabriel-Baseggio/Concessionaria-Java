package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.service.AutenticacaoService;
import net.weg.topcar.service.VeiculoService;

public class MotoController extends VeiculoController {


    public MotoController(VeiculoService veiculoService, AutenticacaoService autenticacaoService) {
        super(veiculoService, autenticacaoService);
    }

    public void cadastrarMoto(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                              Double quilometragem, String placa, String tipoMotor, Long cilindradas, Long qtdMarchas)
            throws PrecoInvalidoException, ObjetoExistenteException {
        isGerente();
        Moto novaMoto = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo,
                tipoMotor, cilindradas, qtdMarchas);
        cadastrarVeiculo(novaMoto);
    }

    public void editarMoto(Long codigo, Double preco, Long ano, String modelo, String marca, Boolean novo,
                           Double quilometragem, String placa, String tipoMotor, Long cilindradas, Long qtdMarchas)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException {
        isGerente();
        buscarVeiculo(codigo);
        Moto motoEditada = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo,
                tipoMotor, cilindradas, qtdMarchas);
        editarVeiculo(motoEditada);
    }
}
