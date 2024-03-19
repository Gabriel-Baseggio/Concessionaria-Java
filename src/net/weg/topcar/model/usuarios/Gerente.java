package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.exceptions.VeiculoExistenteException;
import net.weg.topcar.model.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Gerente extends Vendedor implements IGerente {

    public Gerente(String nome, Long cpf, String senha, Double salario) {
        super(nome, cpf, senha, salario);
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
    public void removerVeiculo(Integer codigo) {

    }

    @Override
    public void editarVeiculo(Integer codigo, Veiculo veiculoEditado) {

    }

    @Override
    public void alterarPrecoVeiculo(Integer codigo, Double novoPreco) throws PrecoInvalidoException {

    }

    @Override
    public void cadastrarCliente(Cliente cliente) throws UsuarioExistenteException {

    }

    @Override
    public void removerCliente(Long cpf) {

    }

    @Override
    public void removerUsuario(Long cpf) {

    }

    @Override
    public void editarCliente(Long cpf, Cliente clienteEditado, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(cpf, clienteEditado);
    }


    public List<Vendedor> verVendedores() {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        for (Cliente clienteChecar : USUARIOS) {
            if (clienteChecar instanceof Vendedor vendedor) {
                vendedores.add(vendedor);
            }
        }
        return Collections.unmodifiableList(vendedores);
    }

    public List<Cliente> verClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Cliente clienteChecar : USUARIOS) {
            if (clienteChecar instanceof Cliente cliente) {
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

    @Override
    public String verPagamentoVendedor(Long cpf) {
        for (Vendedor vendedor : verVendedores()) {
            if (vendedor.matricula == matricula) {
                return vendedor.verPagamento();
            }
        }
        return "Vendedor não encontrado!";
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
