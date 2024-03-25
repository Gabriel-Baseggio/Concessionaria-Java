package net.weg.topcar.model.exceptions;

public class VeiculoNaoEncontradoException extends ObjetoNaoEncontradoException{

    public VeiculoNaoEncontradoException(Integer codigo) {
        super("não existe um veículo com código " + codigo + "!");
    }
}
