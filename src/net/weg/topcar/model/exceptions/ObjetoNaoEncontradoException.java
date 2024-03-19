package net.weg.topcar.model.exceptions;

public class ObjetoNaoEncontradoException extends Exception{

    public ObjetoNaoEncontradoException() {
        super("Usuário não encontrado!");
    }

    public ObjetoNaoEncontradoException(String cpf) {
        super("Usuário com cpf " + cpf + " não encontrado!");
    }

}
