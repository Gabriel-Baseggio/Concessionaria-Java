package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.Veiculo;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Caminhao extends Veiculo {

    private String tracao;

    public Caminhao(int codigo, double preco, String placa, int ano, String modelo, String marca, double kilometragem, String estado, String tracao) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, kilometragem, estado);
        this.tracao = tracao;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCaminhão" +
                "\nTração: " + tracao;
    }
}
