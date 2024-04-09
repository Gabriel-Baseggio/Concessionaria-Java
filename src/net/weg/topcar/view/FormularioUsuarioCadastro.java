package net.weg.topcar.view;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioCadastro extends FormularioUsuario{

    public void cadastrarUsuario() throws PermissaoNegadaException {
        try {
            Long cpf = entradaCPF();
            usuarioController.validarCPF(cpf);

            String nome = entradaNome();
            String senha = entradaSenhaComConfirmacao();

            Cliente novoCliente = null;
            if (usuarioAutenticado.getUsuarioAutenticado() instanceof IGerente) {
                Long tipo = selecionaTipoDeUsuario();
                if (tipo == 1) {
                    Double salario = entradaSalario();
                    novoCliente = new Vendedor(nome, cpf, senha, salario);
                }
            }
            if (novoCliente == null) {
                novoCliente = new Cliente(nome, cpf, senha);
            }
            usuarioController.adicionar(novoCliente);
            saida.escrevaL("Usuário cadastrado com sucesso!");
        } catch (ObjetoExistenteException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
