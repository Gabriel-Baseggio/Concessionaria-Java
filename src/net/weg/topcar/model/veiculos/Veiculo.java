package net.weg.topcar.model.veiculos;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;

public abstract class Veiculo {

    private final int CODIGO;
    private double preco;
    private String placa;
    private int ano;
    private String modelo;
    private String marca;
    private double kilometragem;
    private String estado;
    private boolean vendido;

    public Veiculo(int codigo, double preco, String placa, int ano, String modelo, String marca, double kilometragem, String estado) throws PrecoInvalidoException {
        this.CODIGO = codigo;
        setPreco(preco);
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.marca = marca;
        this.kilometragem = kilometragem;
        this.estado = estado;
    }

    public int getCODIGO() {
        return this.CODIGO;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

    public String getModelo() {
        return this.modelo;
    }

    public boolean isVendido() {
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
                "\nKilometragem: " + this.kilometragem +
                "\nEstado: " + this.estado +
                "\nVendido: " + (this.vendido ? "sim" : "não");
    }
}
