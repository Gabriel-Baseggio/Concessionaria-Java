package net.weg.topcar.view.componentes.formularios.veiculo;

import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.PrecoInvalidoException;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.model.veiculos.Veiculo;

public class FormularioVeiculoCadastro extends FormularioVeiculo {

    public void cadastrarVeiculo() throws PermissaoNegadaException {
        try {
            Long codigo = entradaCodigo();
            Double preco = entradaPreco();
            Long ano = entradaAno();
            String modelo = entradaModelo();
            String marca = entradaMarca();
            Boolean novo = entradaNovo();

            Double quilometragem = 0.0;
            String placa = "";
            if (!novo){
                quilometragem = entradaQuilometragem();
                placa = entradaPlaca();
            }

            Long tipo = selecionaTipoDeVeiculo();
            Veiculo novoVeiculo;
            if (tipo == 1) {
                String carroceria = entradaCarroceria();
                String itensExtra = entradaItensExtra();
                novoVeiculo = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo,
                        carroceria, itensExtra);
            } else if (tipo == 2) {
                String tipoMotor = entradaTipoMotor();
                Long cilindradas = entradaCilindradas();
                Long qtdMarchas = entradaQtdMarchas();
                novoVeiculo = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor,
                        cilindradas, qtdMarchas);
            } else {
                String tracao = entradaTracao();
                novoVeiculo = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
            }
            veiculoController.adicionar(novoVeiculo);
        } catch (ObjetoExistenteException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

}
