package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.List;

public interface IGerente extends IVendedor {

    String cadastrarVeiculo(Veiculo veiculo, IBanco<Veiculo, Integer> banco) throws ObjetoExistenteException;

    String removerVeiculo(Integer codigo, IBanco<Veiculo, Integer> banco) throws ObjetoNaoEncontradoException;

    String editarVeiculo(Veiculo veiculo, IBanco<Veiculo, Integer> banco) throws ObjetoNaoEncontradoException;

    String alterarPrecoVeiculo(Integer codigo, Double novoPreco, IBanco<Veiculo, Integer> banco)
            throws PrecoInvalidoException, ObjetoNaoEncontradoException;

    String cadastrarCliente(Cliente cliente, IBanco<Cliente, Long> banco)
            throws ObjetoExistenteException, AcessoNegadoException;

    String removerCliente(Long cpf, IBanco<Cliente, Long> banco)
            throws ObjetoNaoEncontradoException, AcessoNegadoException;

    String editarCliente(Cliente cliente, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

    List<Vendedor> verVendedores(IBanco<Cliente, Long> banco);

    List<Cliente> verClientes(IBanco<Cliente, Long> banco);

    List<String> verPagamentosDosVendedores(IBanco<Cliente, Long> banco);

    String verPagamentoVendedor(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

}
