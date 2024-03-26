package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.Collections;
import java.util.List;

public class BancoVeiculos implements IBanco<Veiculo, Long> {

    private List<Veiculo> veiculos;

    @Override
    public List<Veiculo> buscarTodos() {
        return Collections.unmodifiableList(veiculos);
    }

    @Override
    public Veiculo buscarUm(Long codigo) throws VeiculoNaoEncontradoException {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getCODIGO() == codigo) {
                return veiculo;
            }
        }
        throw new VeiculoNaoEncontradoException(codigo);
    }

    @Override
    public void adicionar(Veiculo veiculo) throws VeiculoExistenteException {
        try {
            this.buscarUm(veiculo.getCODIGO());
            throw new VeiculoExistenteException(veiculo.getCODIGO());
        } catch (ObjetoNaoEncontradoException exception){
            veiculos.add(veiculo);
        }
    }

    @Override
    public void remover(Long codigo) throws VeiculoNaoEncontradoException {
        try {
            Veiculo veiculo = buscarUm(codigo);
            veiculos.remove(veiculo);
        } catch (ObjetoNaoEncontradoException exception) {
            throw new VeiculoNaoEncontradoException(codigo);
        }

    }

    @Override
    public Boolean existe(Long codigo) {
        try {
            buscarUm(codigo);
            return true;
        } catch (VeiculoNaoEncontradoException exception) {
            return false;
        }
    }

    @Override
    public void alterar(Long codigo, Veiculo novoVeiculo) throws VeiculoNaoEncontradoException {
        Veiculo veiculo = buscarUm(codigo);
        veiculos.set(veiculos.indexOf(veiculo), novoVeiculo);
    }

}
