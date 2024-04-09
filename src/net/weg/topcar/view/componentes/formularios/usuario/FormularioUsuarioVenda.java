package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.controller.VeiculoController;
import net.weg.topcar.model.exceptions.FalhaNaVendaException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.veiculos.Veiculo;

public class FormularioUsuarioVenda extends FormularioUsuario {

    public void vender() {
        try {
            isVendedor();

            saida.escrevaL("Identifique o comprador: ");
            Long cpf = entradaCPF();

            saida.escrevaL("Identifique o veículo: ");
            Long codigo = entradaCodigo();

            usuarioController.vender(cpf, codigo);
        } catch (FalhaNaVendaException | PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
