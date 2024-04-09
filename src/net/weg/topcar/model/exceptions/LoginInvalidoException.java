package net.weg.topcar.model.exceptions;

public class LoginInvalidoException extends Exception {
    public LoginInvalidoException() {
        super("Dados de login inválidos!");
    }
}
