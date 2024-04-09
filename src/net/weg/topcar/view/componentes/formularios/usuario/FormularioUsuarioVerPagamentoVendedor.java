package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.TipoDeUsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IVendedor;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioVerPagamentoVendedor extends FormularioUsuario {

    public void verPagamentoVendedor() {
        try {
            isGerente();

            Long cpf = entradaCPF();
            String pagamentoVendedor = usuarioController.buscarPagamento(cpf);
            saida.escrevaL(pagamentoVendedor);
        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException | PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
