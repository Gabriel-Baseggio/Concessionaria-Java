package net.weg.topcar.view;

import java.util.InputMismatchException;

public class EntradaTexto extends Entrada<String> {

    @Override
    public String leia() {
        return sc.next();
    }
}
