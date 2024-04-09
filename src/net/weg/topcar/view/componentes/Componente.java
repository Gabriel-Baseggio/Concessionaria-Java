package net.weg.topcar.view.componentes;

import net.weg.topcar.view.entrada_saida.*;

public class Componente {

    protected final Entrada<String> entradaTexto = new EntradaTexto();
    protected final Entrada<Long> entradaInteiro = new EntradaInteiro();
    protected final Entrada<Double> entradaDecimal = new EntradaDecimal();

    protected final Saida saida = new Saida();

}
