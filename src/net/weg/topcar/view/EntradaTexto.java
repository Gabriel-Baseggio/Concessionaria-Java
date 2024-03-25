package net.weg.topcar.view;

public class EntradaTexto extends Entrada<String> {

    @Override
    public String leia() {
        return sc.next();
    }

    @Override
    protected boolean validaEntrada(String valor) {
        return valor.isBlank();
    }
}
