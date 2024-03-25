package net.weg.topcar.model.exceptions;

import net.weg.topcar.model.usuarios.Cliente;

public class TipoDeUsuarioInvalidoException extends Exception {
    public TipoDeUsuarioInvalidoException(Cliente cliente) {
        super("Tipo de usuário inválido: " + cliente.getClass().getSimpleName());
    }
}
