package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;

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
                        7 - Cadastrar veículo;
                        8 - Remover veículo;
                        9 - Editar veículo;
                        10 - Alterar preço de um veículo;
                        11 - Cadastrar vendedor/cliente;
                        12 - Remover vendedor/cliente;
                        13 - Editar vendedor/cliente;
                        14 - Ver vendedores;
                        15 - Ver clientes;
                        16 - Ver pagamento dos vendedores;
                        17 - Ver pagamento de um vendedor;
                        """;
    }

    @Override
    public String cadastrarVeiculo(Veiculo veiculo, IBanco<Veiculo, Long> banco) throws ObjetoExistenteException {
        banco.adicionar(veiculo);
        return "Veículo cadastrado!";
    }

    @Override
    public String removerVeiculo(Long codigo, IBanco<Veiculo, Long> banco) throws ObjetoNaoEncontradoException {
        banco.remover(codigo);
        return "Veículo removido!";
    }

    /**
     * Método responsável por executar a ação de edição de um veículo em nível de repositório (DAO).
     * O parâmetro de veículo recebe todas as informações editadas do veículo, já o parâmetro
     * de banco recebe qual o repositório que manipula objetos do tipo veículo.
     * O código do veículo permanecerá o mesmo, por esse motivo é possível pegar o mesmo código presente
     * no objeto editado.
     *
     * @param veiculo
     * @param banco
     * @throws ObjetoNaoEncontradoException
     */
    @Override
    public String editarVeiculo(Veiculo veiculo, IBanco<Veiculo, Long> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(veiculo.getCODIGO(), veiculo);
        return "Veículo editado!";
    }

    @Override
    public String alterarPrecoVeiculo(Long codigo, Double novoPreco, IBanco<Veiculo, Long> banco)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException {
        Veiculo veiculo = banco.buscarUm(codigo);
        veiculo.setPreco(novoPreco);
        banco.alterar(codigo, veiculo);
        return "Preço do veículo alterado!";
    }

    @Override
    public String cadastrarCliente(Cliente cliente, IBanco<Cliente, Long> banco)
            throws ObjetoExistenteException, PermissaoNegadaException {
        if(cliente instanceof Gerente)
            throw new PermissaoNegadaException();

        banco.adicionar(cliente);
        return "Cliente cadastrado!";
    }

    @Override
    public String removerCliente(Long cpf, IBanco<Cliente, Long> banco)
            throws ObjetoNaoEncontradoException, PermissaoNegadaException {
        Cliente cliente = banco.buscarUm(cpf);
        if(cliente instanceof Gerente)
            throw new PermissaoNegadaException();

        banco.remover(cpf);
        return "Cliente removido!";
    }

    /**
     * Método responsável por executar a ação de edição de um cliente em nível de repositório (DAO).
     * O parâmetro de cliente recebe todas as informações editadas do cliente, já o parâmetro
     * de banco recebe qual o repositório que manipula objetos do tipo cliente.
     * O cpf do cliente permanecerá o mesmo, por esse motivo é possível pegar o mesmo cpf presente
     * no objeto editado.
     *
     * @param cliente
     * @param banco
     * @throws ObjetoNaoEncontradoException
     */
    @Override
    public String editarCliente(Cliente cliente, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(cliente.getCPF(), cliente);
        return "Cliente editado!";
    }

    @Override
    public List<Vendedor> verVendedores(IBanco<Cliente, Long> banco) {
        // List<Cliente> usuarios = banco.buscarTodos();
        // usuarios.removeIf(c -> !(c instanceof Vendedor));

        List<Cliente> usuarios = banco.buscarTodos();
        List<Vendedor> vendedores = new ArrayList<>();

        usuarios.forEach(cliente -> {
            if(cliente instanceof Vendedor vendedor){
                vendedores.add(vendedor);
            }
        });

        return Collections.unmodifiableList(vendedores);
    }

    @Override
    public List<Cliente> verClientes(IBanco<Cliente, Long> banco) {
        return Collections.unmodifiableList(banco.buscarTodos());
    }

    @Override
    public List<String> verPagamentosDosVendedores(IBanco<Cliente, Long> banco) {
        List<Vendedor> vendedores = verVendedores(banco);
        List<String> pagamentos = new ArrayList<>();

        vendedores.forEach(vendedor -> {
            pagamentos.add(vendedor.verPagamentoComNome());
        });

        return pagamentos;
    }

    @Override
    public String verPagamentoVendedor(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        Cliente cliente = banco.buscarUm(cpf);
        if(cliente instanceof Vendedor vendedor){
            return vendedor.verPagamentoComNome();
        }
        throw new RuntimeException("O usuário informado não é um vendedor!");
    }

//    @Override
//    public void vender(Veiculo veiculo, Cliente cliente) {
//        cliente.adicionarProprioVeiculo(veiculo);
//        this.setComissao(veiculo.getPreco() * 0.02);
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}
