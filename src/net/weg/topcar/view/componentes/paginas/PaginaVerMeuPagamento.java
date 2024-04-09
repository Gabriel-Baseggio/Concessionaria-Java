package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.UsuarioAutenticadoFront;
import net.weg.topcar.view.componentes.Componente;

public class PaginaVerMeuPagamento extends Componente {

    private UsuarioController usuarioController;

    public void verPagamento() {
        try {
            Vendedor vendedor =
                    (Vendedor) usuarioController.buscarUsuario(UsuarioAutenticadoFront.getUsuario().getCPF());
            saida.escrevaL(vendedor.verPagamento());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        } catch (ClassCastException e){
            saida.escrevaL("Permissão negada! Você não é um vendedor!");
        } catch (NullPointerException e) {
            saida.escrevaL("Você deve fazer login no sistema com um usuário vendedor para " +
                    "executar esta ação!");
        }
    }

}
