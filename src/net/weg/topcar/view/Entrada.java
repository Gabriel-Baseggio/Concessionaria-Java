package net.weg.topcar.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Entrada<T> {

    protected final Scanner sc = new Scanner(System.in);

    public abstract T leia() throws InputMismatchException;

    public T leia(String texto, Saida saida) throws InputMismatchException {
        saida.escreva(texto);
        return leia();
    }

}
