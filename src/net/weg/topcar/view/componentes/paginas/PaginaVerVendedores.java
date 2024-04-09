package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.componentes.Componente;

import java.util.List;

public class PaginaVerVendedores extends Componente {

    public void verVendedores() throws PermissaoNegadaException {
        isGerente();
        List<Vendedor> listaVendedores = buscarVendedores();
        saida.escrevaL(listaVendedores.toString());
    }

}
