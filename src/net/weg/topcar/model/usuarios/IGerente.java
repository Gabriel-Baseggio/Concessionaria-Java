package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.VeiculoExistenteException;
import net.weg.topcar.model.Veiculo;

import java.util.List;

public interface IGerente extends IVendedor {

    void cadastrarVeiculo(Veiculo veiculo, IBanco<Veiculo, Integer> banco) throws VeiculoExistenteException;

    void removerVeiculo(Integer codigo, IBanco<Veiculo, Integer> banco);

    void editarVeiculo(Integer codigo, Veiculo veiculoEditado, IBanco<Veiculo, Integer> banco);

    void alterarPrecoVeiculo(Integer codigo, Double novoPreco, IBanco<Veiculo, Integer> banco) throws PrecoInvalidoException;

    void cadastrarCliente(Cliente cliente, IBanco<Cliente, Long> banco) throws UsuarioExistenteException;

    void removerCliente(Long cpf, IBanco<Cliente, Long> banco);

    void editarCliente(Long cpf, Cliente clienteEditado, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

    List<Vendedor> verVendedores();

    List<Cliente> verClientes();

    List<String> verPagamentosDosVendedores();

    String verPagamentoVendedor(Long cpf);

}
