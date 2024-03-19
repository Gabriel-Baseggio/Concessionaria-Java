package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.exceptions.VeiculoExistenteException;
import net.weg.topcar.model.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Gerente extends Funcionario {

    public Gerente(String nome, String cpf, String senha, int matricula, double salario) {
        super(nome, cpf, senha, matricula, salario);
    }

    @Override
    public String menu() {
        return super.menu() +
                """
                        7 - Cadastrar veículo
                        8 - Remover veículo
                        9 - Editar veículo
                        10 - Alterar preço de um veículo
                        11 - Cadastrar vendedor/cliente
                        12 - Remover vendedor/cliente
                        13 - Editar vendedor/cliente
                        14 - Ver vendedores
                        15 - Ver clientes
                        16 - Ver pagamento dos vendedores
                        17 - Ver pagamento de um vendedor
                        """;
    }

    @Override
    public void venderVeiculo(Usuario cliente, Veiculo veiculo) {
        if (!(veiculo.isVendido())) {
            cliente.addVeiculo(veiculo);
            this.adicionarComissao(veiculo.getPreco() * 0.02);
        }
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws VeiculoExistenteException {
        veiculo.addVeiculo();
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculo.removeVeiculo();
    }

    public void editarVeiculo(Veiculo veiculoEditar, Veiculo veiculoEditado) {
        veiculoEditar.editarVeiculo(veiculoEditado);
    }

    public void alterarPrecoVeiculo(Veiculo veiculoEditar, double novoPreco) throws PrecoInvalidoException {
        veiculoEditar.setPreco(novoPreco);
    }

    public void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        usuario.addUsuario();
    }

    public void removerUsuario(Usuario usuario) {
        usuario.removeUsuario();
    }

    public void editarUsuario(Usuario usuarioEditar, Usuario usuarioEditado) {
        usuarioEditar.editarUsuario(usuarioEditado);
    }

    public List<Vendedor> verVendedores() {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        for (Usuario usuarioChecar : USUARIOS) {
            if (usuarioChecar instanceof Vendedor vendedor) {
                vendedores.add(vendedor);
            }
        }
        return Collections.unmodifiableList(vendedores);
    }

    public List<Cliente> verClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Usuario usuarioChecar : USUARIOS) {
            if (usuarioChecar instanceof Cliente cliente) {
                clientes.add(cliente);
            }
        }
        return Collections.unmodifiableList(clientes);
    }

    public List<String> verPagamentosDosVendedores() {
        List<String> pagamentos = new ArrayList<>();
        verVendedores().forEach((v) -> pagamentos.add(v.verPagamento()));
        return Collections.unmodifiableList(pagamentos);
    }

    // Fazer uma sobrecarga pra buscarUsuario por matricula?
    public String verPagamentoVendedor(int matricula) {
        for (Vendedor vendedor : verVendedores()) {
            if (vendedor.matricula == matricula) {
                return vendedor.verPagamento();
            }
        }
        return "Vendedor não encontrado!";
    }

}
