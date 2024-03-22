package net.weg.topcar.model.exceptions;

public class ObjetoExistenteException extends Exception {
    public ObjetoExistenteException() {
        super("Esse objeto já estpa cadastrado(a)!");
    }
}
