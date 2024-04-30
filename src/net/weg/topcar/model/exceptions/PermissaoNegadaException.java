package net.weg.topcar.model.exceptions;

public class PermissaoNegadaException extends Exception {
    public PermissaoNegadaException() {
        super("Você não tem permissão para executar isso!");
    }

    public PermissaoNegadaException(String message){
        super(message);
    }
}
