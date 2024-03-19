package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.Veiculo;

public class Vendedor extends Funcionario {
    public Vendedor(String nome, String cpf, String senha, int matricula, double salario) {
        super(nome, cpf, senha, matricula, salario);
    }

    @Override
    public void venderVeiculo(Usuario cliente, Veiculo veiculo) {
        if (!(veiculo.isVendido())) {
            cliente.addVeiculo(veiculo);
            this.adicionarComissao(veiculo.getPreco() * 0.01);
        }
    }

}
