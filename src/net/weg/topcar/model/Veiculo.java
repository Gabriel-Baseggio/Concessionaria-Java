package net.weg.topcar.model;

import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.exceptions.VeiculoExistenteException;
import net.weg.topcar.model.exceptions.VeiculoNaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Veiculo {

    private static final ArrayList<Veiculo> VEICULOS = new ArrayList<>();
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

    public static List<Veiculo> verVEICULOS() {
        ArrayList<Veiculo> veiculosNaoVendidos = new ArrayList<>();
        for (Veiculo veiculo : VEICULOS) {
            if (!veiculo.isVendido()) {
                veiculosNaoVendidos.add(veiculo);
            }
        }
        return Collections.unmodifiableList(veiculosNaoVendidos);
    }

    public static Veiculo buscarVeiculo(int codigo) throws VeiculoNaoEncontradoException {
        for (Veiculo veiculo : VEICULOS) {
            if (veiculo.CODIGO == codigo) {
                return veiculo;
            }
        }
        throw new VeiculoNaoEncontradoException();
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isVendido() {
        return vendido;
    }


    public void alterarVendido() {
        this.vendido = !vendido;
    }

    public void editarVeiculo(Veiculo veiculoEditado) {
        VEICULOS.set(VEICULOS.indexOf(this), veiculoEditado);
    }

    public void addVeiculo() throws VeiculoExistenteException {
        try {
            buscarVeiculo(this.getCODIGO());
            throw new VeiculoExistenteException();
        } catch (VeiculoNaoEncontradoException exception) {
            VEICULOS.add(this);
        }
    }

    public void removeVeiculo() {
        VEICULOS.remove(this);
    }

    @Override
    public String toString() {
        return "Veículo" +
                "\nCódigo: " + CODIGO +
                "\nPreço: " + preco +
                "\nPlaca: " + placa +
                "\nAno: " + ano +
                "\nModelo: " + modelo +
                "\nMarca: " + marca +
                "\nKilometragem: " + kilometragem +
                "\nEstado: " + estado +
                "\nVendido: " + (vendido ? "sim" : "não");
    }
}
