package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public class Caminhao extends Veiculo {

    private String tracao;

    public Caminhao(Long codigo, Double preco, String placa, Long ano, String modelo, String marca,
                    Double quilometragem, Boolean novo, String tracao) throws PrecoInvalidoException {
        super(codigo, preco, placa, ano, modelo, marca, quilometragem, novo);
        this.tracao = tracao;
    }

    public String getTracao() {
        return tracao;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTração: " + this.tracao;
    }
}
