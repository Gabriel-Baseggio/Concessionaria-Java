package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.UsuarioAutenticadoFront;
import net.weg.topcar.view.componentes.formularios.Formulario;

public class FormularioUsuario extends Formulario {

    protected UsuarioController usuarioController;

    protected Long entradaCPF() {
        return entradaInteiro.leiaComSaidaEValidacao("Cpf: ", saida);
    }
    protected Long entradaCodigo() {
        return entradaInteiro.leiaComSaidaEValidacao("Código: ", saida);
    }

    protected String entradaNome() {
        return entradaTexto.leiaComSaidaEValidacao("Nome: ", saida);
    }

    protected String entradaNome(String nome) {
        String novoNome = entradaTexto.leiaComSaida("Nome: ", saida);
        return novoNome.isBlank() ? nome : novoNome;
    }

    protected String entradaSenhaComConfirmacao() {
        String senha, confSenha;

        do {
            senha = entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
            confSenha = entradaTexto.leiaComSaidaEValidacao("Confirme a senha: ", saida);
        } while (!(senha.equals(confSenha)));

        return senha;
    }

    protected String entradaSenhaSemConfirmacao() {
        return entradaTexto.leiaComSaidaEValidacao("Senha: ", saida);
    }


    protected Double entradaSalario() {
        return entradaDecimal.leiaComSaidaEValidacao("Salário: ", saida);
    }

    protected Double entradaSalario(Double salario) {
        Double novoSalario = entradaDecimal.leiaComSaida("Salário: ", saida);
        return novoSalario <= 0 ? salario : novoSalario;
    }

    protected Long selecionaTipoDeUsuario() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Qual o tipo de usuário que você deseja cadastrar?
                    1 - Vendedor;
                    2 - Cliente.
                    """, saida);
        } while (entrada > 2);
        return entrada;
    }

}
