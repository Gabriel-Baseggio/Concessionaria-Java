package net.weg.topcar.model.exceptions;

public class ObjetoNaoEncontradoException extends Exception{

    public ObjetoNaoEncontradoException(String mensagem) {
        super("Objeto não encontrado: " + mensagem);
    }

}
