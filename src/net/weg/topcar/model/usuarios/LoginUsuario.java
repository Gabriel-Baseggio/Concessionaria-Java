package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.model.exceptions.SenhaIncorretaException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class LoginUsuario {

    private final BancoUsuario bancoUsuario;
    private Cliente cliente;

    public LoginUsuario(BancoUsuario bancoUsuario) {
        this.bancoUsuario = bancoUsuario;
    }

    public Cliente login(String cpf, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
        this.cliente = this.bancoUsuario.buscarUm(cpf);
        this.validarSenha(senha);
        return this.cliente;
    }

    private void validarSenha(String senha) throws SenhaIncorretaException {
        if (!this.cliente.getSenha().equals(senha)) {
            throw new SenhaIncorretaException();
        }
    }

}
