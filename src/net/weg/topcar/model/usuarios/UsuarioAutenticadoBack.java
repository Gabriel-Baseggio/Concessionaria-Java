package net.weg.topcar.model.usuarios;

public class UsuarioAutenticadoBack {
    private static Cliente usuarioAutenticado;

    public static void setUsuario(Cliente usuario) {
        usuarioAutenticado = usuario;
    }

    public static Cliente getUsuario() {
        return usuarioAutenticado;
    }
}
