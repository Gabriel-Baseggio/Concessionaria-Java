package net.weg.topcar.model.exceptions;

public class VeiculoExistenteException extends ObjetoExistenteException{
    public VeiculoExistenteException(Long codigo) {
        super("já existe um veículo com código " + codigo + "!");
    }
}
