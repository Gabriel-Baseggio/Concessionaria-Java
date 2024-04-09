package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioEdicao extends FormularioUsuario {



    public void editarUsuario() {
        try {
            isGerente();

            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);

            if (cliente instanceof Gerente) {
                throw new PermissaoNegadaException();
            }

            String nome = entradaNome(cliente.getNome());

            Cliente clienteEditado;
            if (cliente instanceof Vendedor vendedor) {
                Double salario = entradaSalario(vendedor.getSalario());
                clienteEditado = new Vendedor(nome, cpf, vendedor.getSenha(), salario);
            } else {
                clienteEditado = new Cliente(nome, cpf, cliente.getSenha());
            }
            usuarioController.alterar(clienteEditado);
        } catch (ObjetoNaoEncontradoException | PermissaoNegadaException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
