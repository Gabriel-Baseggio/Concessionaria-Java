package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;

public class FormularioUsuarioRemocao extends FormularioUsuario {

    public void removerUsuario() {
        try {
            isGerente();
            Long cpf = entradaCPF();
            usuarioController.remover(cpf);
        } catch (ObjetoNaoEncontradoException | PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
