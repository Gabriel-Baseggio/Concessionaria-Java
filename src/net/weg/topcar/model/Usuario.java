package net.weg.topcar.model;

import net.weg.topcar.model.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public abstract class Usuario {

    private final String CPF;
    private final ArrayList<Veiculo> VEICULOS = new ArrayList<>();
    private final String nome;
    private final String senha;

    public Usuario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.CPF = cpf;
        this.senha = senha;
    }

    public String getCPF() {
        return this.CPF;
    }

    public String getSenha() {
        return senha;
    }


    public String menu() {
        return """                        
                0 - Logout
                1 - Ver veículos em estoque
                2 - Ver detalhes de um veículo
                3 - Ver meus veículos
                """;
    }

    public List<Veiculo> verMeusVeiculos() {
        return Collections.unmodifiableList(VEICULOS);
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculo.alterarVendido();
        VEICULOS.add(veiculo);
    }

    @Override
    public String toString() {
        return "Usuário" + "\nNome: " + nome + "\nCPF: " + CPF + "\nSenha: " + senha + "\nVeículos: " + VEICULOS;
    }
}
