package net.weg.topcar.model.exceptions;

public class ObjetoExistenteException extends Exception {
    public ObjetoExistenteException() {
        super("Objeto existente!");
    }
    public ObjetoExistenteException(String message) {
        super("Objeto existente: " + message);
    }
}
