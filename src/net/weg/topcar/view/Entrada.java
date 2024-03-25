package net.weg.topcar.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Entrada<T> {

    protected final Scanner sc = new Scanner(System.in);

    public abstract T leia() throws InputMismatchException;

    public T leiaComSaida(String texto, Saida saida) throws InputMismatchException {
        saida.escreva(texto);
        return leia();
    }

    public T leiaComSaidaEValidacao(String texto, Saida saida) throws InputMismatchException {
        T valor;
        do {
            valor = leiaComSaida(texto, saida);
        } while (validaEntrada(valor));
        return valor;
    }

    public T leiaComValidacao() throws InputMismatchException {
        T valor;
        do {
            valor = leia();
        } while (validaEntrada(valor));
        return valor;
    }

    protected abstract boolean validaEntrada(T valor);

}
