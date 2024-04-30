package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    private IBanco<Veiculo, Long> veiculoBanco;

    public List<Veiculo> buscarVeiculosDisponiveis() {
        List<Veiculo> veiculos =  veiculoBanco.buscarTodos();

        return filtrarVeiculosDisponiveis(veiculos);
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

    public Veiculo buscarUm(Long codigo) throws ObjetoNaoEncontradoException {
        return veiculoBanco.buscarUm(codigo);
    }

    public void remover(Long codigo) throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        veiculoBanco.remover(codigo);
    }

    public void alterarPreco(Long codigo, Double preco) throws ObjetoNaoEncontradoException,
            PrecoInvalidoException {
        Veiculo veiculo = veiculoBanco.buscarUm(codigo);
        validarPreco(preco);
        veiculo.setPreco(preco);
        veiculoBanco.alterar(codigo, veiculo);
    }

    public void adicionar(Veiculo novoVeiculo) throws ObjetoExistenteException, PrecoInvalidoException {
        validarCodigo(novoVeiculo.getCODIGO());
        validarPreco(novoVeiculo.getPreco());
        veiculoBanco.adicionar(novoVeiculo);
    }

    private void validarCodigo(Long codigo) throws VeiculoExistenteException {
        if (veiculoBanco.existe(codigo)) {
            throw new VeiculoExistenteException(codigo);
        }
    }

    private void validarPreco(Double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
    }

    public void alterar(Long codigo, Veiculo veiculoEditado) throws ObjetoNaoEncontradoException {
        veiculoBanco.alterar(codigo, veiculoEditado);
    }
}
