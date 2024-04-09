package net.weg.topcar.view;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuario extends Formulario {

    protected UsuarioAutenticado usuarioAutenticado;
    protected UsuarioController usuarioController;

    protected void isGerente(){
        super.isGerente(usuarioAutenticado.getUsuarioAutenticado());
    }

    protected Vendedor isVendedor() throws PermissaoNegadaException {
        if (usuarioAutenticado.getUsuarioAutenticado() instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadaException("O usuário não é um vendedor.");
    }

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
