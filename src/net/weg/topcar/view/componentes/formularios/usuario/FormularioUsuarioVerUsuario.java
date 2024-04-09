package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;

public class FormularioUsuarioVerUsuario extends FormularioUsuario {

    public void verUsuario() throws PermissaoNegadaException {
        try {
            isVendedor();

            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);

            saida.escrevaL(cliente.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
