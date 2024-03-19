package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.VeiculoExistenteException;
import net.weg.topcar.model.Veiculo;

import java.util.List;

public interface IGerente extends IFuncionario {

    void cadastrarVeiculo(Veiculo veiculo) throws VeiculoExistenteException;

    void removerVeiculo(int codigo);

    void editarVeiculo(int codigo, Veiculo veiculoEditado);

    void alterarPrecoVeiculo(int codigo, double novoPreco) throws PrecoInvalidoException;

    void cadastrarUsuario(Usuario usuario) throws UsuarioExistenteException;

    void removerUsuario(String cpf);

    void editarUsuario(String cpf, Usuario usuarioEditado) throws ObjetoNaoEncontradoException;

    List<Vendedor> verVendedores();

    List<Cliente> verClientes();

    List<String> verPagamentosDosVendedores();

    String verPagamentoVendedor(int matricula);

}
