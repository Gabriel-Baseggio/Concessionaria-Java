package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.Veiculo;

import java.util.Collections;
import java.util.List;

public class BancoVeiculos implements IBanco<Veiculo, Integer> {

    private List<Veiculo> veiculos;

    public List<Veiculo> buscarTodos() {
        return Collections.unmodifiableList(veiculos);
    }

    public Veiculo buscarUm(Integer codigo) throws ObjetoNaoEncontradoException {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getCODIGO()  == codigo) {
                return veiculo;
            }
        }
        throw new ObjetoNaoEncontradoException();
    }

    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void remover(Integer codigo) throws ObjetoNaoEncontradoException {
        Veiculo veiculo = buscarUm(codigo);
        veiculos.remove(veiculo);
    }

    public void alterar(Integer codigo, Veiculo novoVeiculo) throws ObjetoNaoEncontradoException {
        Veiculo veiculo = buscarUm(codigo);
        veiculos.set(veiculos.indexOf(veiculo), novoVeiculo);
    }

}
