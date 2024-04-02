package net.weg.topcar.model.exceptions;

public class FalhaNaCompraException extends Exception {

    public FalhaNaCompraException() {
        super("Falha na compra!");
    }

    public FalhaNaCompraException(String texto) {
        super("Falha na compra: " + texto);
    }
}
