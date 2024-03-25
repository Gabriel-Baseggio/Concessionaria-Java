package net.weg.topcar.model.exceptions;

public class UsuarioNaoEncontradoException extends ObjetoNaoEncontradoException{
    public UsuarioNaoEncontradoException(Long cpf) {
        super("não existe um usuário com CPF " + cpf + "!");
    }
}
