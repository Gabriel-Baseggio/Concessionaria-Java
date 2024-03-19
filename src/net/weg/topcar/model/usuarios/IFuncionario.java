package net.weg.topcar.model.usuarios;

import net.weg.topcar.model.Usuario;
import net.weg.topcar.model.Veiculo;

public interface IFuncionario extends IUsuario {

    void vender(Veiculo veiculo, Usuario cliente);

    Usuario procurarUsuario(String cpf);

    String verPagamento();

}
