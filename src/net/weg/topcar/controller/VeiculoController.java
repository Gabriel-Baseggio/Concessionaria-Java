package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.service.AutenticacaoService;
import net.weg.topcar.service.VeiculoService;

import java.util.List;

public class VeiculoController {

    private final VeiculoService veiculoService;
    private final AutenticacaoService autenticacaoService;

    public VeiculoController(VeiculoService veiculoService, AutenticacaoService autenticacaoService) {
        this.veiculoService = veiculoService;
        this.autenticacaoService = autenticacaoService;
    }

    public List<Veiculo> buscarVeiculosDisponiveis() {
        return veiculoService.buscarVeiculosDisponiveis();
    }

    public Veiculo buscarVeiculo(Long codigo) throws ObjetoNaoEncontradoException {
        return veiculoService.buscarUm(codigo);
    }

    public void removerVeiculo(Long codigo) throws PermissaoNegadaException, ObjetoNaoEncontradoException {
        isGerente();
        veiculoService.remover(codigo);
    }

    public void alterarPreco(Long codigo, Double novoPreco) throws PermissaoNegadaException,
            ObjetoNaoEncontradoException, PrecoInvalidoException {
        isGerente();
        veiculoService.alterarPreco(codigo, novoPreco);
    }

    protected void cadastrarVeiculo(Veiculo veiculo) throws ObjetoExistenteException, PrecoInvalidoException {
        veiculoService.adicionar(veiculo);
    }

    public void editarVeiculo(Veiculo veiculo) throws PermissaoNegadaException, ObjetoNaoEncontradoException {
        veiculoService.alterar(veiculo.getCODIGO(), veiculo);
    }

    protected void isGerente() throws PermissaoNegadaException {
        if (!(UsuarioAutenticadoBack.getUsuario() instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }
}
