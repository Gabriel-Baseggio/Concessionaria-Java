package net.weg.topcar.model.exceptions;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException() {
        super("CPF de usuário já cadastrado!");
    }
}
