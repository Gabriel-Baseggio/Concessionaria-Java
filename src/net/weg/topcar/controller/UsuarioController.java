package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.view.*;

public class UsuarioController {

    private static Cliente usuarioLogado;
    private IBanco<Cliente, Long> bancoUsuarios = new BancoUsuario();
    private IBanco<Veiculo, Integer> bancoVeiculos = new BancoVeiculos();

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public void cadastroUsuario(){
        Cliente cliente;
        String nome = entradaTexto.leiaComSaidaEValidacao("Informe o seu nome: ", saida);
        Long cpf = entradaInteiro.leiaComSaidaEValidacao("Informe o seu cpf: ", saida);
        String senha = entradaTexto.leiaComSaidaEValidacao("Informe a sua senha: ", saida);

        if(!(usuarioLogado instanceof Gerente)){
            cliente = new Cliente(nome, cpf, senha);
        } else {
            Long escolha = entradaInteiro.leiaComSaidaEValidacao("""
                    Qual o perfil de usuário que você deseja cadastrar?
                    1 - Cliente;
                    Outro - Vendedor.
                    """, saida);
            if(escolha == 1){
                cliente = new Cliente(nome, cpf, senha);
            } else {
                Double salario = entradaDecimal.leiaComSaidaEValidacao("Salário: ", saida);
                cliente = new Vendedor(nome, cpf, senha, salario);
            }
        }

    }

}
