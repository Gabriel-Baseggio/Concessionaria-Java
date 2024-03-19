package net.weg.topcar.model.exceptions;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException() {
        super("Senha incorreta!");
    }
}
