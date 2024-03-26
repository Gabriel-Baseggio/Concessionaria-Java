package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public abstract class Veiculo {

    private final Long CODIGO;
    private Double preco;
    private String placa;
    private Long ano;
    private String modelo;
    private String marca;
    private Double quilometragem;
    private Boolean novo;
    private Boolean vendido;

    public Veiculo(Long codigo, Double preco, String placa, Long ano, String modelo,
                   String marca, Double quilometragem, Boolean novo) throws PrecoInvalidoException {
        this.CODIGO = codigo;
        setPreco(preco);
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.marca = marca;
        this.quilometragem = quilometragem;
        this.novo = novo;
        this.vendido = false;
    }

    public Long getCODIGO() {
        return this.CODIGO;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

    public String getModelo() {
        return this.modelo;
    }

    public Boolean isVendido() {
        return this.vendido;
    }

    public void alterarStatusVendido() {
        this.vendido = !this.vendido;
    }

    @Override
    public String toString() {
        return "Código: " + this.CODIGO +
                "\nPreço: " + this.preco +
                "\nPlaca: " + this.placa +
                "\nAno: " + this.ano +
                "\nModelo: " + this.modelo +
                "\nMarca: " + this.marca +
                "\nKilometragem: " + this.quilometragem +
                "\nEstado: " + this.novo +
                "\nVendido: " + (this.vendido ? "sim" : "não");
    }
}
