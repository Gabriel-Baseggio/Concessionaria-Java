package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Moto extends Veiculo {

    private String tipoMotor;
    private int cilindradas;
    private int qtdMarchas;

    public Moto(int codigo, double preco, String placa, int ano, String modelo, String marca, double kilometragem, String estado, String tipoMotor, int cilindradas, int qtdMarchas) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, kilometragem, estado);
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
