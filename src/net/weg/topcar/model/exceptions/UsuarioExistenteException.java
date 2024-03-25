package net.weg.topcar.model.exceptions;

public class UsuarioExistenteException extends ObjetoExistenteException {

    public UsuarioExistenteException(Long cpf) {
        super("já existe um usuário com CPF " + cpf + "!");
    }

}
