package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Veiculo;

import java.util.List;

public interface IUsuario {

    List<Veiculo> verVeiculos();

    List<Veiculo> verMeusVeiculos();

    Veiculo verVeiculo(int codigo);

}
