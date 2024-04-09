package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;

public class Formulario {

    protected final Entrada<String> entradaTexto = new EntradaTexto();
    protected final Entrada<Long> entradaInteiro = new EntradaInteiro();
    protected final Entrada<Double> entradaDecimal = new EntradaDecimal();
    protected final Saida saida = new Saida();

    protected void isGerente(Cliente usuarioLogado) throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }


}
