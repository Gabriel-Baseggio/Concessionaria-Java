package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.componentes.Componente;

import java.util.List;

public class PaginaVerPagamentoVendedores extends Componente {

    public void verPagamentoVendedores() throws PermissaoNegadaException {
        isGerente();
        List<Vendedor> listaVendedores = buscarVendedores();
        listaVendedores.forEach(vendedor -> {
            saida.escrevaL(vendedor.verPagamentoComNome());
        });
    }

}
