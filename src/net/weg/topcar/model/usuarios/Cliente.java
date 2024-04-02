package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Cliente implements ICliente {

    private final Long CPF;
    private final String nome;
    private final String senha;
    private final ArrayList<Veiculo> VEICULOS = new ArrayList<>();

    public Cliente(String nome, Long cpf, String senha) {
        this.nome = nome;
        this.CPF = cpf;
        this.senha = senha;
    }

    public Long getCPF() {
        return this.CPF;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return this.nome;
    }


    public String menu() {
        return """          
                1 - Ver veículos em estoque;
                2 - Ver detalhes de um veículo;
                3 - Ver meus veículos;
                """;
    }

    @Override
    public List<Veiculo> verVeiculos(IBanco<Veiculo, Integer> banco) {
        return banco.buscarTodos();
    }

    public List<Veiculo> verMeusVeiculos() {
        return Collections.unmodifiableList(VEICULOS);
    }

    @Override
    public Veiculo verVeiculo(Integer codigo, IBanco<Veiculo, Integer> banco) throws ObjetoNaoEncontradoException {
        return banco.buscarUm(codigo);
    }

    public void adicionarProprioVeiculo(Veiculo veiculo) {
        veiculo.alterarStatusVendido();
        VEICULOS.add(veiculo);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome +
                "\nCPF: " + this.CPF;
    }
}
