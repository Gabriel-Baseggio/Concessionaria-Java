package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.view.componentes.Componente;

import java.util.List;

public class PaginaVerMeusVeiculos extends Componente {

    private UsuarioController usuarioController;

    public void verMeusVeiculos() {
        List<Veiculo> listaMeusVeiculos = usuarioController.verMeusVeiculos();
        listaMeusVeiculos.forEach(veiculo -> saida.escrevaL(veiculo.toString()));
    }

}
