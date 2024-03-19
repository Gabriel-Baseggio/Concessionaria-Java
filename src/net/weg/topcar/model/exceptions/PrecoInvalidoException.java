package net.weg.topcar.model.exceptions;

public class PrecoInvalidoException extends Exception {
    public PrecoInvalidoException() {
        super("Preço de veículo inválido, deve ser acima de R$0!");
    }
}
