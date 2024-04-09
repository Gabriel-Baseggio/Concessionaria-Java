package net.weg.topcar.view.componentes.formularios;

import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.IVendedor;
import net.weg.topcar.view.UsuarioAutenticadoFront;
import net.weg.topcar.view.componentes.Componente;

public class Formulario extends Componente {

    protected void isGerente() throws PermissaoNegadaException {
        if (!(UsuarioAutenticadoFront.getUsuario() instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }
    protected void isVendedor() throws PermissaoNegadaException {
        if (!(UsuarioAutenticadoFront.getUsuario() instanceof IVendedor)) {
            throw new PermissaoNegadaException("O usuário não é um vendedor.");
        }
    }


}
