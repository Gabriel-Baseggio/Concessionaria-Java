package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoUsuarios;
import net.weg.topcar.dao.BancoVeiculos;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.model.veiculos.Caminhao;
import net.weg.topcar.model.veiculos.Carro;
import net.weg.topcar.model.veiculos.Moto;
import net.weg.topcar.model.veiculos.Veiculo;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class VeiculoController {

    private static Cliente usuarioLogado;
    private IBanco<Veiculo, Long> bancoVeiculos;

    private final Entrada<String> entradaTexto = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Entrada<Double> entradaDecimal = new EntradaDecimal();
    private final Saida saida = new Saida();

    public VeiculoController(IBanco<Veiculo, Long> bancoVeiculos) {
        this.bancoVeiculos = bancoVeiculos;
    }

    public void verVeiculos() {
        List<Veiculo> listaVeiculos = bancoVeiculos.buscarTodos();
        List<Veiculo> listaVeiculosDisponiveis = filtrarVeiculosDisponiveis(listaVeiculos);

        listaVeiculosDisponiveis.forEach(veiculo -> {
            saida.escrevaL(veiculo.toString());
        });
    }

    public void verVeiculo() {
        try {
            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);
            saida.escrevaL(veiculo.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void removerVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();
            Long codigo = entradaCodigo();
            bancoVeiculos.remover(codigo);
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void alterarPreco() throws PermissaoNegadaException {
        try {
            isGerente();

            Long codigo = entradaCodigo();
            Veiculo veiculo = bancoVeiculos.buscarUm(codigo);

            Double preco = entradaPreco(veiculo.getPreco());
            veiculo.setPreco(preco);

            bancoVeiculos.alterar(codigo, veiculo);
        } catch (ObjetoNaoEncontradoException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void cadastrarVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();

            Long codigo = entradaCodigo();
            validarCodigo(codigo);

            Double preco = entradaPreco();
            validarPreco(preco);

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
            bancoVeiculos.adicionar(novoVeiculo);
        } catch (ObjetoExistenteException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    public void editarVeiculo() throws PermissaoNegadaException {
        try {
            isGerente();
            Veiculo veiculo = buscarVeiculo();

            Double preco = entradaPreco(veiculo.getPreco());
            validarPreco(preco);

            Long ano = entradaAno(veiculo.getAno());
            String modelo = entradaModelo(veiculo.getModelo());
            String marca = entradaMarca(veiculo.getMarca());
            Boolean novo = entradaNovo();

            Double quilometragem = 0.0;
            String placa = "";
            if (!novo){
                quilometragem = entradaQuilometragem(veiculo.getQuilometragem());
                placa = entradaPlaca(veiculo.getPlaca());
            }

            Long codigo = veiculo.getCODIGO();
            Veiculo veiculoEditado;
            if (veiculo instanceof Carro carro) {
                String carroceria = entradaCarroceria(carro.getCarroceria());
                String itensExtra = entradaItensExtra(carro.getItensExtra());
                veiculoEditado = new Carro(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, carroceria, itensExtra);
            } else if (veiculo instanceof Moto moto) {
                String tipoMotor = entradaTipoMotor(moto.getTipoMotor());
                Long cilindradas = entradaCilindradas(moto.getCilindradas());
                Long qtdMarchas = entradaQtdMarchas(moto.getQtdMarchas());
                veiculoEditado = new Moto(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tipoMotor,
                        cilindradas, qtdMarchas);
            } else {
                String tracao = entradaTracao(((Caminhao) veiculo).getTracao());
                veiculoEditado = new Caminhao(codigo, preco, placa, ano, modelo, marca, quilometragem, novo, tracao);
            }
            bancoVeiculos.alterar(codigo, veiculoEditado);
        } catch (ObjetoNaoEncontradoException | PrecoInvalidoException e) {
            saida.escrevaL(e.getMessage());
        }
    }

    private Veiculo buscarVeiculo() throws ObjetoNaoEncontradoException {
        Long codigo = entradaCodigo();
        return bancoVeiculos.buscarUm(codigo);
    }

    private void validarCodigo(Long codigo) throws VeiculoExistenteException {
        if (bancoVeiculos.existe(codigo)) {
            throw new VeiculoExistenteException(codigo);
        }
    }

    private void validarPreco(Double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
    }

    private Long selecionaTipoDeVeiculo() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Qual o tipo de veículo que você deseja cadastrar?
                    1 - Carro;
                    2 - Moto;
                    3 - Caminhão.
                    """, saida);
        } while (entrada > 2);
        return entrada;
    }

    private Long entradaCodigo() {
        return entradaInteiro.leiaComSaidaEValidacao("Código: ", saida);
    }

    private Double entradaPreco() {
        return entradaDecimal.leiaComSaidaEValidacao("Preço: ", saida);
    }

    private Double entradaPreco(Double preco) {
        Double novoPreco = entradaDecimal.leiaComSaida("Preço: ", saida);
        return novoPreco <= 0 ? preco : novoPreco;
    }

    private String entradaPlaca() {
        return entradaTexto.leiaComSaidaEValidacao("Placa: ", saida);
    }

    private String entradaPlaca(String placa) {
        String novaPlaca = entradaTexto.leiaComSaida("Placa: ", saida);
        return novaPlaca.isBlank() ? placa : novaPlaca;
    }

    private Long entradaAno() {
        return entradaInteiro.leiaComSaidaEValidacao("Ano: ", saida);
    }

    private Long entradaAno(Long ano) {
        Long novoAno = entradaInteiro.leiaComSaida("Ano: ", saida);
        return novoAno <= 0 ? ano : novoAno ;
    }

    private String entradaModelo() {
        return entradaTexto.leiaComSaidaEValidacao("Modelo: ", saida);
    }

    private String entradaModelo(String modelo) {
        String novoModelo = entradaTexto.leiaComSaida("Modelo: ", saida);
        return novoModelo.isBlank() ? modelo : novoModelo ;
    }

    private String entradaMarca() {
        return entradaTexto.leiaComSaidaEValidacao("Marca: ", saida);
    }

    private String entradaMarca(String marca) {
        String novaMarcha = entradaTexto.leiaComSaida("Marca: ", saida);
        return novaMarcha.isBlank() ? marca : novaMarcha ;
    }

    private Double entradaQuilometragem() {
        return entradaDecimal.leiaComSaidaEValidacao("Quilometragem: ", saida);
    }

    private Double entradaQuilometragem(Double quilometragem) {
        Double novaQuilometragem = entradaDecimal.leiaComSaida("Quilometragem: ", saida);
        return novaQuilometragem <= 0 ? quilometragem : novaQuilometragem;
    }

    private Boolean entradaNovo() {
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Veículo 0km?
                    1 - Sim;
                    2 - Não.
                    """, saida);
        } while (entrada > 2);
        return entrada == 1;
    }

    private String entradaCarroceria() {
        return entradaTexto.leiaComSaidaEValidacao("Carroceria: ", saida);
    }

    private String entradaCarroceria(String carroceria) {
        String novaCarroceria = entradaTexto.leiaComSaida("Carroceria: ", saida);
        return novaCarroceria.isBlank() ? carroceria : novaCarroceria;
    }

    private String entradaItensExtra() {
        return entradaTexto.leiaComSaidaEValidacao("Itens extra: ", saida);
    }

    private String entradaItensExtra(String itensExtra) {
        String novosItensExtra = entradaTexto.leiaComSaida("Itens extra: ", saida);
        return novosItensExtra.isBlank() ? itensExtra : novosItensExtra;
    }

    private String entradaTipoMotor() {
        return entradaTexto.leiaComSaidaEValidacao("Tipo do motor: ", saida);
    }

    private String entradaTipoMotor(String tipoMotor) {
        String novoTipoMotor = entradaTexto.leiaComSaida("Tipo do motor: ", saida);
        return novoTipoMotor.isBlank() ? tipoMotor : novoTipoMotor;
    }

    private Long entradaCilindradas() {
        return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);
    }

    private Long entradaCilindradas(Long cilindradas) {
        Long novasCilindradas = entradaInteiro.leiaComSaida("Cilindradas: ", saida);
        return novasCilindradas <= 0 ? cilindradas : novasCilindradas;
    }

    private Long entradaQtdMarchas() {
        return entradaInteiro.leiaComSaidaEValidacao("Quantidade de marchas: ", saida);
    }

    private Long entradaQtdMarchas(Long qtdMarchas) {
        Long novaQtdMarchas = entradaInteiro.leiaComSaida("Quantidade de marchas: ", saida);
        return novaQtdMarchas <= 0 ? qtdMarchas : novaQtdMarchas;
    }

    private String entradaTracao() {
        return entradaTexto.leiaComSaidaEValidacao("Tração: ", saida);
    }

    private String entradaTracao(String tracao) {
        String novaTracao = entradaTexto.leiaComSaida("Tração: ", saida);
        return novaTracao.isBlank() ? tracao : novaTracao;
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("O usuário não é um gerente.");
        }
    }

    private List<Veiculo> filtrarVeiculosDisponiveis(List<Veiculo> listaVeiculos) {
        List<Veiculo> listaVeiculosDisponiveis = new ArrayList<>();
        listaVeiculos.forEach(veiculo -> {
            if (!veiculo.isVendido()) {
                listaVeiculosDisponiveis.add(veiculo);
            }
        });
        return listaVeiculosDisponiveis;
    }

}
