package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.Veiculo;

public abstract class Funcionario extends Usuario {

    protected int matricula;
    private double salario;
    private double totalComissao;

    public Funcionario(String nome, String cpf, String senha, int matricula, double salario) {
        super(nome, cpf, senha);
        this.matricula = matricula;
        this.salario = salario;
    }

    @Override
    public String menu() {
        return super.menu() +
                """
                        4 - Vender veículo
                        5 - Procurar cliente
                        6 - Ver pagamento
                        """;
    }

    public abstract void venderVeiculo(Usuario cliente, Veiculo veiculo);

    public Usuario procurarCliente(String cpf) throws ObjetoNaoEncontradoException {
        try {
            return buscarUsuario(cpf);
        } catch (ObjetoNaoEncontradoException exception) {
            throw new ObjetoNaoEncontradoException();
        }
    }

    public double calcularPagamento() {
        return salario + totalComissao;
    }

    protected String verPagamento() {
        return this.matricula + ": " + this.calcularPagamento();
    }

    public void adicionarComissao(double valor) {
        this.totalComissao += valor;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nFuncionário" +
                "\nMatrícula: " + matricula +
                "\nSalário: " + salario +
                "\nTotal de comissão: " + totalComissao +
                "\nPagamento: " + this.calcularPagamento();
    }
}
