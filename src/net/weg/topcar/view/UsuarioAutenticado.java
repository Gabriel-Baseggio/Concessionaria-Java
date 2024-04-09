package net.weg.topcar.view;

import net.weg.topcar.model.usuarios.Cliente;

public class UsuarioAutenticado {
    private Cliente usuarioAutenticado;

    public void setUsuarioAutenticado(Cliente usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public Cliente getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
