package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.Veiculo;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.List;

public interface ICliente {

    List<Veiculo> verVeiculos(IBanco<Veiculo, Integer> banco);

    List<Veiculo> verMeusVeiculos();

    Veiculo verVeiculo(Integer codigo, IBanco<Veiculo, Integer> banco) throws ObjetoNaoEncontradoException;
}
