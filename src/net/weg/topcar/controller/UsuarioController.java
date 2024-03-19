package net.weg.topcar.controller;

import net.weg.topcar.view.*;

public class UsuarioController {
    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public void cadastroUsuario(){
        String nome = entradaTexto.leia("Informe o seu nome: ", saida);
        Long cpf = entradaInteiro.leia("Informe o seu cpf: ", saida);
        String senha = entradaTexto.leia("Informe a sua senha: ", saida);


    }

}
