package net.weg.topcar.model.exceptions;

public class AcessoNegadoException extends RuntimeException {
    public AcessoNegadoException() {
        super("Você não tem permissão para executar isso!");
    }

    public AcessoNegadoException(String message){
        super(message);
    }
}
