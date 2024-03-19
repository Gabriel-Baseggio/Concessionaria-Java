package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.Usuario;

import java.util.Collections;
import java.util.List;

public class BancoUsuario implements IBanco<Usuario, String> {

    private List<Usuario> usuarios;

    public List<Usuario> buscarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    public Usuario buscarUm(String cpf) throws ObjetoNaoEncontradoException {
        for (Usuario usuario : usuarios) {
            if (usuario.getCPF().equals(cpf)) {
                return usuario;
            }
        }
        throw new ObjetoNaoEncontradoException();
    }

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void remover(String cpf) throws ObjetoNaoEncontradoException {
        Usuario usuario = buscarUm(cpf);
        usuarios.remove(usuario);
    }

    public void alterar(String cpf, Usuario novoUsuario) throws ObjetoNaoEncontradoException {
        Usuario usuario = buscarUm(cpf);
        usuarios.set(usuarios.indexOf(usuario), novoUsuario);
    }

}
