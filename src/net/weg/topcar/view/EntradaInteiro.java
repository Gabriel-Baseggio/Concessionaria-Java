package net.weg.topcar.view;

import java.util.InputMismatchException;

public class EntradaInteiro extends Entrada<Long> {
    @Override
    public Long leia() throws InputMismatchException {
        return sc.nextLong();
    }
}
