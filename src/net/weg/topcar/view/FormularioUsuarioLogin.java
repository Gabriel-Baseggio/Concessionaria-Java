package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;

public class FormularioUsuarioLogin extends FormularioUsuario{

    public void login() throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        String senha = entradaSenhaSemConfirmacao();

        usuarioLogado = autenticacao.login(cpf, senha);
        return usuarioLogado;
    }

}
