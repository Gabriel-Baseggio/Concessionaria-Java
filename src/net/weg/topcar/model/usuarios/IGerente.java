package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.List;

public interface IGerente extends IVendedor {

    String cadastrarVeiculo(Veiculo veiculo, IBanco<Veiculo, Long> banco) throws ObjetoExistenteException;

    String removerVeiculo(Long codigo, IBanco<Veiculo, Long> banco) throws ObjetoNaoEncontradoException, PermissaoNegadaException;

    String editarVeiculo(Veiculo veiculo, IBanco<Veiculo, Long> banco) throws ObjetoNaoEncontradoException;

    String alterarPrecoVeiculo(Long codigo, Double novoPreco, IBanco<Veiculo, Long> banco)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException;

    String cadastrarCliente(Cliente cliente, IBanco<Cliente, Long> banco)
            throws ObjetoExistenteException, PermissaoNegadaException;

    String removerCliente(Long cpf, IBanco<Cliente, Long> banco)
            throws ObjetoNaoEncontradoException, PermissaoNegadaException;

    String editarCliente(Cliente cliente, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

    List<Vendedor> verVendedores(IBanco<Cliente, Long> banco);

    List<Cliente> verClientes(IBanco<Cliente, Long> banco);

    List<String> verPagamentoVendedores(List<Vendedor> vendedores);

    String verPagamentoVendedor(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

}
