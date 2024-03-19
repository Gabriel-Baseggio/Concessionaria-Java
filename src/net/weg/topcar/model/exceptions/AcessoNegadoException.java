package net.weg.topcar.model.exceptions;

public class AcessoNegadoException extends Exception {
    public AcessoNegadoException() {
        super("Você não tem permissão para executar isso!");
    }
}
