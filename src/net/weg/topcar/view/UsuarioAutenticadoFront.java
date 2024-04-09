package net.weg.topcar.view;

import net.weg.topcar.model.usuarios.Cliente;

public class UsuarioAutenticadoFront {
    private static Cliente usuarioAutenticado;

    public static void setUsuario(Cliente usuario) {
        usuarioAutenticado = usuario;
    }

    public static Cliente getUsuario() {
        return usuarioAutenticado;
    }
}
