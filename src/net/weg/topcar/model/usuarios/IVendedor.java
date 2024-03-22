package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public interface IVendedor extends ICliente {

    void vender(Veiculo veiculo, Cliente cliente);

    Cliente buscarUsuario(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;

    String verPagamento();

    String verPagamentoComNome();

}
