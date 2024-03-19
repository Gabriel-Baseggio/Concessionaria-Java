package net.weg.topcar.model.exceptions;

public class VeiculoExistenteException extends Exception{
    public VeiculoExistenteException() {
        super("Código de veículo já cadastrado!");
    }
}
