package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.Veiculo;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Carro extends Veiculo {

    private String carroceria;
    private String itensExtra;

    public Carro(int codigo, double preco, String placa, int ano, String modelo, String marca, double kilometragem, String estado, String carroceria, String itensExtra) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, kilometragem, estado);
        this.carroceria = carroceria;
        this.itensExtra = itensExtra;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCarro" +
                "\nCarroceria: " + carroceria +
                "\nItens extra: " + itensExtra;
    }
}
