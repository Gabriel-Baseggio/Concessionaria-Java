package net.weg.topcar;

public class Main {

//    private static final Scanner sc = new Scanner(System.in);
//    private static Cliente clienteLogado;

    public static void main(String[] args) {
//        Gerente g = new Gerente("Romário", "012.345.678-90", "123", 777, 100000);
//        try {
//            new net.weg.topcar.model.usuarios.Cliente("Marcio", "134.431.869-15", "123").addUsuario();
//            new Vendedor("Albertinho", "732.546.338-69", "123", 7, 10000).addUsuario();
//            g.addUsuario();
//        } catch (UsuarioExistenteException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        try {
//            g.cadastrarVeiculo(new Carro(1, 700, "ABC 1A34", 2020, "Kwid", "Renault", 60000,
//                    "Novo", "Hatchback", "Módulo de vidro e teto solar"));
//            g.cadastrarVeiculo(new Moto(2, 370, "DEF 5D67", 2009, "Sportster XR 1200", "Harley-Davidson",
//                    35000, "Seminovo", "2 tempos", 1200, 6));
//            g.cadastrarVeiculo(new Caminhao(3, 100, "HIJ 8H90", 2015, "R 460 Super", "Scania",
//                    250000, "Seminovo", "6x2"));
//            g.cadastrarVeiculo(new Carro(4, 168, "KLM 1K23", 2024, "Corolla", "Toyota", 0,
//                    "Novo", "Sedan", "Airbags de cortina, módulo de vidro e tilt-down"));
//            g.cadastrarVeiculo(new Moto(5, 145, "NOP 4N56", 2022, "Factor YBR 125I ED", "Yamaha",
//                    23000, "Seminovo", "4 tempos", 125, 6));
//            g.cadastrarVeiculo(new Caminhao(6, 678, "QRS 7Q89", 2024, "FH 540", "Volvo", 0,
//                    "Novo", "8x2"));
//        } catch (PrecoInvalidoException | VeiculoExistenteException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        System.out.println();
//        System.out.println("Bem vindo a Concessionária");
//
//        do {
//            menuInicial();
//        } while (clienteLogado == null);
    }

//    private static String inputString(String text) {
//        System.out.print(text);
//        return sc.nextLine();
//    }
//
//    private static int inputInt(String text) {
//        System.out.print(text);
//        return Integer.parseInt(sc.nextLine());
//    }
//
//    private static double inputDouble(String text) {
//        System.out.print(text);
//        return Double.parseDouble(sc.nextLine());
//    }
//
//    private static void menuInicial() {
//        System.out.println();
//
//        String menu = """
//                0 - Sair
//                1 - Cadastrar cliente
//                2 - Fazer login
//                3 - Ver veículos em estoque
//                4 - Ver detalhes de um veículo
//                >\s""";
//        int escolha = inputInt(menu);
//
//        switch (escolha) {
//            case 0 -> System.exit(0);
//            case 1 -> cadastrarCliente();
//            case 2 -> logarUsuario();
//            case 3 -> verEstoqueDeVeiculos();
//            case 4 -> verDetalhesVeiculo();
//            default -> System.out.println("Opção inválida!");
//        }
//    }
//
//    private static void cadastrarCliente() {
//        System.out.println();
//
//        if (clienteLogado == null) {
//            String nome, cpf, senha;
//            nome = inputString("Digite seu nome: ");
//
//            cpf = inputString("Digite seu cpf: ");
//            if (cpf.length() < 11) {
//                System.out.println("O cpf deve conter no mínimo 11 números!");
//                return;
//            }
//
//            senha = inputString("Digite sua senha: ");
//
//            net.weg.topcar.model.usuarios.Cliente cliente = new net.weg.topcar.model.usuarios.Cliente(nome, cpf, senha);
//            try {
//                cliente.addUsuario();
//                System.out.println("Cadastro efetuado com sucesso!");
//            } catch (UsuarioExistenteException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void logarUsuario() {
//        System.out.println();
//
//        String cpf = inputString("Digite seu cpf: ");
//        String senha = inputString("Digite sua senha: ");
//
//        try {
//            clienteLogado = Cliente.login(cpf, senha);
//            System.out.println("Login efetuado com sucesso!");
//            menuUsuario();
//        } catch (ObjetoNaoEncontradoException exception) {
//            System.out.println(exception.getMessage());
//            int escolha = inputInt("""
//                    Deseja realizar o cadastro?
//                    1 - Sim;
//                    Outro - Não.
//                    >""");
//            if (escolha == 1) {
//                cadastrarCliente();
//            }
//        } catch (SenhaIncorretaException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//    }
//
//    private static void verDetalhesVeiculo() {
//        System.out.println();
//
//        Veiculo veiculo;
//        int cod = inputInt("Digite o código do veículo que você quer ver: ");
//        try {
//            veiculo = Veiculo.buscarVeiculo(cod);
//            System.out.println(veiculo);
//        } catch (VeiculoNaoEncontradoException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//    }
//
//    private static void verEstoqueDeVeiculos() {
//        System.out.println();
//
//        for (Veiculo veiculo : Veiculo.verVEICULOS()) {
//            System.out.println(veiculo.getCODIGO() + ": " + veiculo.getModelo());
//        }
//    }
//
//    private static void menuUsuario() {
//        do {
//            System.out.println();
//
//            int escolha = inputInt(clienteLogado.menu());
//
//            try {
//                if (escolha < 0 || escolha > 17) {
//                    throw new OpcaoInvalidaException();
//                } else if (escolha > 3 && !(clienteLogado instanceof Funcionario)) {
//                    throw new AcessoNegadoException();
//                } else if (escolha > 6 && !(clienteLogado instanceof Gerente)) {
//                    throw new AcessoNegadoException();
//                }
//            } catch (OpcaoInvalidaException | AcessoNegadoException exception) {
//                System.out.println(exception.getMessage());
//            }
//
//            switch (escolha) {
//                case 0 -> logout();
//                case 1 -> verEstoqueDeVeiculos();
//                case 2 -> verDetalhesVeiculo();
//                case 3 -> verMeusVeiculos();
//            }
//            if (clienteLogado instanceof Funcionario) {
//                switch (escolha) {
//                    case 4 -> venderVeiculo();
//                    case 5 -> procurarCliente();
//                    case 6 -> verPagamento();
//                }
//                if (clienteLogado instanceof Gerente) {
//                    switch (escolha) {
//                        case 7 -> cadastrarVeiculo();
//                        case 8 -> removerVeiculo();
//                        case 9 -> editarVeiculo();
//                        case 10 -> alterarPrecoVeiculo();
//                        case 11 -> cadastrarUsuario();
//                        case 12 -> removerUsuario();
//                        case 13 -> editarUsuario();
//                        case 14 -> verVendedores();
//                        case 15 -> verClientes();
//                        case 16 -> verPagamentoVendedores();
//                        case 17 -> verPagamentoVendedor();
//                    }
//                }
//            }
//
//        } while (clienteLogado != null);
//    }
//
//    private static void logout() {
//        System.out.println();
//        System.out.println("Saindo da sua conta!");
//        clienteLogado = null;
//    }
//
//    private static void verMeusVeiculos() {
//        System.out.println();
//        System.out.println(clienteLogado.verMeusVeiculos().toString());
//    }
//
//    private static void venderVeiculo() {
//        if (clienteLogado instanceof Funcionario funcionario) {
//            System.out.println();
//
//            String cpf = inputString("Digite seu cpf: ");
//            Veiculo veiculo;
//            int cod = inputInt("Digite o código do veículo que você quer vender: ");
//            try {
//                veiculo = Veiculo.buscarVeiculo(cod);
//                funcionario.venderVeiculo(Cliente.buscarUsuario(cpf), veiculo);
//                System.out.println("Venda efetuada com sucesso!");
//            } catch (ObjetoNaoEncontradoException | VeiculoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//            }
//
//        }
//    }
//
//    private static void procurarCliente() {
//        if (clienteLogado instanceof Funcionario funcionario) {
//            System.out.println();
//            String cpf = inputString("Digite seu cpf: ");
//            try {
//                System.out.println(funcionario.procurarCliente(cpf));
//            } catch (ObjetoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void verPagamento() {
//        if (clienteLogado instanceof Funcionario funcionario) {
//            System.out.println();
//            System.out.println(funcionario.calcularPagamento());
//        }
//    }
//
//    private static void cadastrarVeiculo() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            int opcao;
//            do {
//                String menu = """
//                        1 - Cadastrar carro
//                        2 - Cadastrar moto
//                        3 - Cadastrar caminhão
//                        >\s""";
//                opcao = inputInt(menu);
//
//                if (opcao != 1 && opcao != 2 && opcao != 3) {
//                    System.out.println("Opção inválida!");
//                }
//
//            } while (opcao != 1 && opcao != 2 && opcao != 3);
//
//            String estado;
//            do {
//                int tipo = inputInt("""
//                        O veículo é:
//                            1 - Novo
//                            2 - Seminovo
//                        >\s""");
//
//                switch (tipo) {
//                    case 1 -> estado = "Novo";
//                    case 2 -> estado = "Seminovo";
//                    default -> estado = "";
//                }
//                if (estado.equals("")) {
//                    System.out.println("Opção inválida!");
//                }
//            } while (estado.equals(""));
//
//            int cod = inputInt("Digite o código do veículo: ");
//
//            double preco = inputDouble("Digite o preço do veículo: ");
//
//            String placa = "";
//            double kilometragem = 0;
//            if (estado.equals("Seminovo")) {
//                placa = inputString("Digite a placa do veículo: ");
//
//                kilometragem = inputDouble("Digite a kilometragem do veículo: ");
//            }
//
//            int ano = inputInt("Digite o ano do veículo: ");
//
//            String modelo = inputString("Digite o modelo do veículo: ");
//
//            String marca = inputString("Digite a marca do veículo: ");
//
//            try {
//                switch (opcao) {
//                    case 1 -> {
//                        String carroceria = inputString("Digite o tipo da carroceria do carro: ");
//                        String itensExtras = inputString("Digite os itens extra do carro: ");
//                        gerente.cadastrarVeiculo(new Carro(cod, preco, placa, ano, modelo, marca, kilometragem, estado, carroceria, itensExtras));
//                    }
//                    case 2 -> {
//                        String tipoMotor = inputString("Digite o tipo do motor da moto: ");
//                        int cilindradas = inputInt("Digite as cilindradas da moto: ");
//                        int qtdMarchas = inputInt("Digite a quantidade de marchas da moto: ");
//                        gerente.cadastrarVeiculo(new Moto(cod, preco, placa, ano, modelo, marca, kilometragem, estado, tipoMotor, cilindradas, qtdMarchas));
//
//
//                    }
//                    case 3 -> {
//                        String tracao = inputString("Digite a tração do caminhão: ");
//                        gerente.cadastrarVeiculo(new Caminhao(cod, preco, placa, ano, modelo, marca, kilometragem, estado, tracao));
//                    }
//                }
//            } catch (VeiculoExistenteException | PrecoInvalidoException exception) {
//                System.out.println(exception.getMessage());
//            }
//
//            System.out.println("Cadastro de veículo efetuado com sucesso!");
//
//        }
//    }
//
//    private static void removerVeiculo() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//            Veiculo veiculo;
//            int cod = inputInt("Digite o código do veículo a remover: ");
//            try {
//                veiculo = Veiculo.buscarVeiculo(cod);
//                gerente.removerVeiculo(veiculo);
//                System.out.println("Veículo removido com sucesso!");
//            } catch (VeiculoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void editarVeiculo() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            int opcao;
//            do {
//                opcao = inputInt("""
//                        1 - Editar um carro
//                        2 - Editar uma moto
//                        3 - Editar um caminhão
//                        >""");
//                if (opcao != 1 && opcao != 2 && opcao != 3) {
//                    System.out.println("Opção inválida!");
//                }
//
//            } while (opcao != 1 && opcao != 2 && opcao != 3);
//
//
//            Veiculo veiculoAntigo;
//            int cod;
//            cod = inputInt("Digite o código do veículo a editar: ");
//
//            try {
//                veiculoAntigo = Veiculo.buscarVeiculo(cod);
//                if (veiculoAntigo.isVendido()) {
//                    System.out.println("Esse veículo já foi vendido, você não pode alterá-lo!");
//                    return;
//                } else {
//                    if (opcao == 1 && !(veiculoAntigo instanceof Carro)) {
//                        System.out.println("Esse veículo não é um carro!");
//                        return;
//                    }
//                    if (opcao == 2 && !(veiculoAntigo instanceof Moto)) {
//                        System.out.println("Esse veículo não é uma moto!");
//                        return;
//                    }
//                    if (opcao == 3 && !(veiculoAntigo instanceof Caminhao)) {
//                        System.out.println("Esse veículo não é um caminhão!");
//                        return;
//                    }
//                }
//
//                System.out.println("Dados desse veículo: ");
//                System.out.println(veiculoAntigo);
//            } catch (VeiculoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//                return;
//            }
//
//            String estado;
//            try {
//                int tipo = inputInt("""
//                        O veículo é:
//                            1 - Novo
//                            2 - Seminovo
//                        >\s""");
//
//                switch (tipo) {
//                    case 1 -> estado = "Novo";
//                    case 2 -> estado = "Seminovo";
//                    default -> throw new OpcaoInvalidaException();
//                }
//            } catch (OpcaoInvalidaException exception) {
//                System.out.println(exception.getMessage());
//                return;
//            }
//
//            double preco = inputDouble("Digite o novo preço do veículo: ");
//
//            String placa = "";
//            double kilometragem = 0;
//            if (estado.equals("Seminovo")) {
//                placa = inputString("Digite a nova placa do veículo: ");
//
//                kilometragem = inputDouble("Digite a nova kilometragem do veículo: ");
//            }
//            int ano = inputInt("Digite o novo ano do veículo: ");
//
//            String modelo = inputString("Digite o novo modelo do veículo: ");
//
//            String marca = inputString("Digite a nova marca do veículo: ");
//
//            try {
//                switch (opcao) {
//                    case 1 -> {
//                        String carroceria = inputString("Digite o novo tipo da carroceria do carro: ");
//                        String itensExtras = inputString("Digite os novos itens extra do carro: ");
//                        gerente.editarVeiculo(veiculoAntigo, new Carro(cod, veiculoAntigo.getPreco(), placa, ano, modelo, marca, kilometragem, estado, carroceria, itensExtras));
//                    }
//                    case 2 -> {
//                        String tipoMotor = inputString("Digite o novo tipo do motor da moto: ");
//                        int cilindradas = inputInt("Digite as novas cilindradas da moto: ");
//                        int qtdMarchas = inputInt("Digite a nova quantidade de marchas da moto: ");
//                        gerente.editarVeiculo(veiculoAntigo, new Moto(cod, preco, placa, ano, modelo, marca, kilometragem, estado, tipoMotor, cilindradas, qtdMarchas));
//                    }
//                    case 3 -> {
//                        String tracao = inputString("Digite a nova tração do caminhão: ");
//                        gerente.editarVeiculo(veiculoAntigo, new Caminhao(cod, preco, placa, ano, modelo, marca, kilometragem, estado, tracao));
//                    }
//                }
//                System.out.println("Veículo editado com sucesso!");
//            } catch (PrecoInvalidoException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void alterarPrecoVeiculo() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            int cod;
//            cod = inputInt("Digite o código do carro para alterar o preço: ");
//            Veiculo veiculo;
//            try {
//                veiculo = Veiculo.buscarVeiculo(cod);
//                gerente.alterarPrecoVeiculo(veiculo, inputDouble("Digite o novo preço: "));
//                System.out.println("Preço do veículo alterado com sucesso!");
//            } catch (PrecoInvalidoException | VeiculoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void cadastrarUsuario() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            int opcao;
//            try {
//                opcao = inputInt("""
//                        1 - Cadastrar cliente
//                        2 - Cadastrar vendedor
//                        """);
//                if (opcao != 1 && opcao != 2) {
//                    throw new OpcaoInvalidaException();
//                }
//            } catch (OpcaoInvalidaException exception) {
//                System.out.println(exception.getMessage());
//                return;
//            }
//
//            String nome = inputString("Digite o nome do usuário: ");
//
//            String cpf = inputString("Digite o cpf do usuário: ");
//
//            String senha = inputString("Digite a senha do usário: ");
//
//            try {
//                switch (opcao) {
//                    case 1 -> gerente.cadastrarUsuario(new net.weg.topcar.model.usuarios.Cliente(nome, cpf, senha));
//                    case 2 -> {
//                        int matricula = inputInt("Digite a matrícula do vendedor: ");
//                        double salario = inputDouble("Digite o salário: ");
//                        gerente.cadastrarUsuario(new Vendedor(nome, cpf, senha, matricula, salario));
//                    }
//                }
//                System.out.println("Cadastro de usuário efetuado com sucesso!");
//            } catch (UsuarioExistenteException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
//
//    private static void removerUsuario() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//            Cliente cliente;
//            String cpf = inputString("Digite o cpf do usuário a remover: ");
//            try {
//                cliente = Cliente.buscarUsuario(cpf);
//                if (cliente instanceof Gerente) {
//                    throw new AcessoNegadoException();
//                }
//                gerente.removerUsuario(cliente);
//                System.out.println("Usuário removido com sucesso!");
//            } catch (ObjetoNaoEncontradoException | AcessoNegadoException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private static void editarUsuario() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            int opcao;
//            do {
//                opcao = inputInt("""
//                        1 - Editar cliente
//                        2 - Editar vendedor
//                        """);
//                if (opcao != 1 && opcao != 2) {
//                    System.out.println("Opção inválida!");
//                }
//
//            } while (opcao != 1 && opcao != 2);
//
//            Cliente clienteAntigo;
//            String cpf;
//            cpf = inputString("Digite o cpf do usuário a editar: ");
//
//            try {
//                clienteAntigo = Cliente.buscarUsuario(cpf);
//            } catch (ObjetoNaoEncontradoException exception) {
//                System.out.println(exception.getMessage());
//                return;
//            }
//
//            if (opcao == 1 && !(clienteAntigo instanceof net.weg.topcar.model.usuarios.Cliente)) {
//                System.out.println("Esse usuário não é um cliente!");
//                return;
//            }
//            if (opcao == 2 && !(clienteAntigo instanceof Vendedor)) {
//                System.out.println("Esse usuário não é um vendedor!");
//                return;
//            }
//
//            System.out.println("Dados desse usuário: ");
//            System.out.println(clienteAntigo);
//
//            String nome = inputString("Digite o nome do usuário: ");
//
//            String senha = inputString("Digite a senha do usário: ");
//
//            switch (opcao) {
//                case 1 -> gerente.editarUsuario(clienteAntigo, new net.weg.topcar.model.usuarios.Cliente(nome, cpf, senha));
//                case 2 -> {
//                    int matricula = inputInt("Digite a matrícula do vendedor: ");
//                    double salario = inputDouble("Digite o salário do vendedor: ");
//                    gerente.editarUsuario(clienteAntigo, new Vendedor(nome, cpf, senha, matricula, salario));
//                }
//            }
//            System.out.println("Usuário editado com sucesso!");
//        }
//    }
//
//    private static void verVendedores() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            System.out.println(gerente.verVendedores());
//        }
//    }
//
//    private static void verClientes() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            System.out.println(gerente.verClientes());
//        }
//    }
//
//    private static void verPagamentoVendedores() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            System.out.println(gerente.verPagamentosDosVendedores());
//        }
//    }
//
//    private static void verPagamentoVendedor() {
//        if (clienteLogado instanceof Gerente gerente) {
//            System.out.println();
//
//            System.out.println("Digite a matrícula do vendedor: ");
//            int matricula = Integer.parseInt(sc.nextLine());
//
//            System.out.println(gerente.verPagamentoVendedor(matricula));
//        }
//    }

}