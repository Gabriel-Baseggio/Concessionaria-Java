package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.SenhaIncorretaException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class Autenticacao {

    private final IBanco<Cliente, Long> bancoUsuarios;
    private Cliente cliente;

    public Autenticacao(IBanco<Cliente, Long> bancoUsuarios) {
        this.bancoUsuarios = bancoUsuarios;
    }

    public Cliente login(Long cpf, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
        this.cliente = this.bancoUsuarios.buscarUm(cpf);
        this.validarSenha(senha);
        return this.cliente;
    }

    private void validarSenha(String senha) throws SenhaIncorretaException {
        if (!this.cliente.getSenha().equals(senha)) {
            throw new SenhaIncorretaException();
        }
    }

}
