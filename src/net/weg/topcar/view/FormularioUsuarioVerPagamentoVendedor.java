package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.TipoDeUsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IVendedor;

public class FormularioUsuarioVerPagamentoVendedor extends FormularioUsuario {

    public void verPagamentoVendedor() throws PermissaoNegadaException {
        try {
            isGerente();

            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);

            if (cliente instanceof IVendedor vendedor) {
                saida.escrevaL(vendedor.verPagamento());
            } else {
                throw new TipoDeUsuarioInvalidoException(cliente);
            }
        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
