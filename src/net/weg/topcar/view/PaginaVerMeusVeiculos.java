package net.weg.topcar.view;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.List;

public class PaginaVerMeusVeiculos {

    private Saida saida;
    private UsuarioController usuarioController;

    public void verMeusVeiculos() {
        List<Veiculo> listaMeusVeiculos = usuarioController.verMeusVeiculos();
        listaMeusVeiculos.forEach(veiculo -> saida.escrevaL(veiculo.toString()));
    }

}
