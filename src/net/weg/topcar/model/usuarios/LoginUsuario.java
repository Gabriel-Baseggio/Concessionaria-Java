package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.exceptions.SenhaIncorretaException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class LoginUsuario {

    private final BancoUsuario bancoUsuario;
    private Usuario usuario;

    public LoginUsuario(BancoUsuario bancoUsuario) {
        this.bancoUsuario = bancoUsuario;
    }

    public Usuario login(String cpf, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
        this.usuario = this.bancoUsuario.buscarUsuario(cpf);
        this.validarSenha(senha);
        return this.usuario;
    }

    private void validarSenha(String senha) throws SenhaIncorretaException {
        if (!this.usuario.getSenha().equals(senha)) {
            throw new SenhaIncorretaException();
        }
    }

}
