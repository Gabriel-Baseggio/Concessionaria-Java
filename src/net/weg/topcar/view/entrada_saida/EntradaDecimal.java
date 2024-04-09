package net.weg.topcar.view.entrada_saida;

import java.util.InputMismatchException;

public class EntradaDecimal extends Entrada<Double> {

    @Override
    public Double leia() throws InputMismatchException {
        return sc.nextDouble();
    }

    @Override
    protected boolean validaEntrada(Double valor) {
        return valor <= 0.0;
    }
}
