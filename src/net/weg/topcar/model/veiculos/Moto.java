package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Moto extends Veiculo {

    private String tipoMotor;
    private Long cilindradas;
    private Long qtdMarchas;

    public Moto(Long codigo, Double preco, String placa, Long ano, String modelo, String marca, Double quilometragem,
                Boolean novo, String tipoMotor, Long cilindradas, Long qtdMarchas) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, quilometragem, novo);
        this.tipoMotor = tipoMotor;
        this.cilindradas = cilindradas;
        this.qtdMarchas = qtdMarchas;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo do motor: " + this.tipoMotor +
                "\nCilindradas: " + this.cilindradas +
                "\nQuantidade de marchas: " + this.qtdMarchas;
    }
}
