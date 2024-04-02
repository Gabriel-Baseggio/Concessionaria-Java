package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Carro extends Veiculo {

    private String carroceria;
    private String itensExtra;

    public Carro(Long codigo, Double preco, String placa, Long ano, String modelo, String marca, Double quilometragem,
                 Boolean novo, String carroceria, String itensExtra) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, quilometragem, novo);
        this.carroceria = carroceria;
        this.itensExtra = itensExtra;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public String getItensExtra() {
        return itensExtra;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCarroceria: " + this.carroceria +
                "\nItens extra: " + this.itensExtra;
    }
}
