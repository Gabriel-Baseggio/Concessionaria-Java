package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.FalhaNaVendaException;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class Vendedor extends Cliente implements IVendedor {
    private Double salario;
    private Double totalComissao;

    public Vendedor(String nome, Long cpf, String senha, Double salario) {
        super(nome, cpf, senha);
        this.salario = salario;
    }

    @Override
    public String menu() {
        return super.menu() +
                """
                        4 - Vender veículo;
                        5 - Procurar cliente;
                        6 - Ver pagamento;
                        """;
    }

    public Double getSalario() {
        return salario;
    }

    public double calcularPagamento() {
        return salario + totalComissao;
    }

    @Override
    public void vender(Veiculo veiculo, Cliente cliente) throws FalhaNaVendaException {
        if(!veiculo.isVendido()){
            cliente.adicionarProprioVeiculo(veiculo);
            this.setComissao(veiculo.getPreco() * 0.01);
        } else {
            throw new FalhaNaVendaException("O veículo já foi comprado!");
        }
    }

    @Override
    public Cliente buscarUsuario(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        return banco.buscarUm(cpf);
    }

    @Override
    public String verPagamento() {
        return this.getCPF() + ": R$" + this.calcularPagamento();
    }

    @Override
    public String verPagamentoComNome() {
        return this.getNome() + ": " + this.verPagamento();
    }

    public void setComissao(double comissao) {
        this.totalComissao += comissao;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSalário: R$" + this.salario +
                "\nTotal de comissão: R$" + this.totalComissao +
                "\nPagamento: R$" + this.calcularPagamento();
    }
}
