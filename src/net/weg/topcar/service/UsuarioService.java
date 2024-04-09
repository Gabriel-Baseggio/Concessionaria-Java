package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private IBanco<Cliente, Long> usuarioBanco;

    public Cliente adicionar(Cliente novoCliente) throws ObjetoExistenteException {
        return usuarioBanco.adicionar(novoCliente);
    }

    public boolean existe(Long cpf) {
        return usuarioBanco.existe(cpf);
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        usuarioBanco.alterar(clienteEditado.getCPF(), clienteEditado);
    }

    public Cliente buscarUm(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioBanco.buscarUm(cpf);
    }

    public List<Veiculo> meusVeiculos() {
        return UsuarioAutenticadoBack.getUsuario().verMeusVeiculos();
    }

    private void atualizarEnvolvidosNaVenda(Cliente cliente, Vendedor vendedor, Veiculo veiculo) throws ObjetoNaoEncontradoException {
        bancoUsuarios.alterar(cliente.getCPF(), cliente); // Carro cliente
        bancoUsuarios.alterar(vendedor.getCPF(), vendedor); // Comissão
        bancoVeiculos.alterar(veiculo.getCODIGO(), veiculo); // Vendido
    }

    private List<Vendedor> buscarVendedores() {
        List<Cliente> listaClientes = bancoUsuarios.buscarTodos();
        return filtrarVendedores(listaClientes);
    }

    private List<Vendedor> filtrarVendedores(List<Cliente> listaClientes) {
        List<Vendedor> listaVendedores = new ArrayList<>();

        listaClientes.forEach(cliente -> {
            if (cliente instanceof Vendedor vendedor) {
                listaVendedores.add(vendedor);
            }
        });

        return listaVendedores;
    }

    private Veiculo buscarVeiculo() throws ObjetoNaoEncontradoException {
        Long codigo = entradaCodigo();
        return bancoVeiculos.buscarUm(codigo);
    }

    private void cadastrarVendedor(String nome, Long cpf, String senha, Double salario) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Vendedor(nome, cpf, senha, salario));
    }

    private void cadastrarCliente(String nome, Long cpf, String senha) throws ObjetoExistenteException {
        bancoUsuarios.adicionar(new Cliente(nome, cpf, senha));
    }

}
