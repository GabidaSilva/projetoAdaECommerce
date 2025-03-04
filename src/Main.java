import Clientes.*;
import Produtos.*;
import SistemaDePagamentos.*;
import Notificacoes.*;
import Pedidos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Notificador notificador = new EmailNotificador();

    public static void main(String[] args) throws PagamentoException {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> cadastrarProduto();
                case 4 -> listarProdutos();
                case 5 -> criarPedido();
                case 6 -> finalizarPedido();
                case 7 -> listarPedidos();
                case 8 -> devolverProduto();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Cadastrar Produto");
        System.out.println("4. Listar Produtos");
        System.out.println("5. Criar Pedido");
        System.out.println("6. Finalizar Pedido");
        System.out.println("7. Listar Pedidos");
        System.out.println("8. Devolver Produto");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o tipo de cliente (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o ID do cliente: ");
        String id = scanner.nextLine();
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o documento do cliente: ");
        String documento = scanner.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        Cliente cliente;
        if (tipoCliente == 1) {
            cliente = new PessoaFisica(id, nome, documento, endereco, telefone, email);
        } else if (tipoCliente == 2) {
            cliente = new PessoaJuridica(id, nome, documento, endereco, telefone, email);
        } else {
            System.out.println("Tipo de cliente inválido!");
            return;
        }

        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            System.out.printf("ID: %s | Nome: %s | Documento: %s | Endereço: %s | Telefone: %s | Email: %s%n",
                    cliente.getId(), cliente.getNome(), cliente.getDocumento(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail());
        }
    }
    private static void cadastrarProduto() {
        System.out.print("Digite o tipo de produto (1 - Impressora, 2 - Mouse, 3 - Notebook, 4 - Personal Computer, 5 - Teclado): ");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o modelo do produto: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite o código de barras do produto: ");
        String codigoDeBarras = scanner.nextLine();
        System.out.print("Digite o valor do produto: ");
        double valor = scanner.nextDouble();

        Produto produto;
        switch (tipoProduto) {
            case 1 -> produto = new Impressora(modelo, codigoDeBarras, valor);
            case 2 -> produto = new Mouse(modelo, codigoDeBarras, valor);
            case 3 -> produto = new Notebook(modelo, codigoDeBarras, valor);
            case 4 -> produto = new PersonalComputer(modelo, codigoDeBarras, valor);
            case 5 -> produto = new Teclado(modelo, codigoDeBarras, valor);
            default -> {
                System.out.println("Tipo de produto inválido!");
                return;
            }
        }

        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }
    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n=== Lista de Produtos ===");
        for (Produto produto : produtos) {
            System.out.printf("Modelo: %s | Código de Barras: %s | Valor: R$ %.2f | Tipo: %s | Disponível: %s%n",
                    produto.getModelo(), produto.getCodigoDeBarras(), produto.getValor(),
                    produto.getTipo(), produto.isDisponivel() ? "Sim" : "Não");
        }
    }
    private static void criarPedido() {
        System.out.print("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();

        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Pedido pedido = new Pedido(cliente);
        boolean continuarAdicionando = true;

        while (continuarAdicionando) {
            System.out.print("Digite o código de barras do produto: ");
            String codigoDeBarras = scanner.nextLine();

            Produto produto = buscarProdutoPorCodigoDeBarras(codigoDeBarras);
            if (produto == null || !produto.isDisponivel()) {
                System.out.println("Produto não encontrado ou indisponível.");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            pedido.adicionarItem(produto, quantidade, produto.getValor());

            System.out.print("Deseja adicionar mais produtos? (S/N): ");
            String resposta = scanner.nextLine();
            continuarAdicionando = resposta.equalsIgnoreCase("S");
        }

        pedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private static void finalizarPedido() throws PagamentoException {
        System.out.print("Digite o ID do pedido: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = buscarPedidoPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        System.out.println("\nEscolha o método de pagamento:");
        System.out.println("1. Boleto");
        System.out.println("2. Pix");
        System.out.println("3. Cartão de Crédito");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();

        MetodoPagamento metodoPagamento;
        switch (opcaoPagamento) {
            case 1 -> metodoPagamento = new Boleto();
            case 2 -> metodoPagamento = new Pix();
            case 3 -> metodoPagamento = new CartaoDeCredito();
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        pedido.finalizar(notificador);
        if (metodoPagamento.processarPagamento(pedido.calcularTotal())) {
            pedido.pagar(notificador);
            System.out.println("Pedido finalizado e pago com sucesso!");
        } else {
            System.out.println("Falha no pagamento. Pedido não finalizado.");
        }
    }

    private static void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.printf("ID: %d | Cliente: %s | Status: %s | Total: R$ %.2f%n",
                    pedido.getId(), pedido.getCliente().getNome(), pedido.getStatus(), pedido.calcularTotal());
        }
    }

    private static void devolverProduto() {
        System.out.print("Digite o código de barras do produto a ser devolvido: ");
        String codigoDeBarrasProduto = scanner.nextLine();

        Produto produto = null;

        for (Produto p : produtos) {
            if (p.getCodigoDeBarras().equals(codigoDeBarrasProduto)) {
                produto = p;
                break;
            }
        }
        if (produto != null) {
            produto.devolver();
            System.out.printf("\nProduto '%s' devolvido com sucesso!%n", produto.getModelo());
        } else {
            System.out.println("\nNão foi possível encontrar o produto para devolução.");
        }
    }
    private static Cliente buscarClientePorId(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    private static Produto buscarProdutoPorCodigoDeBarras(String codigoDeBarras) {
        for (Produto produto : produtos) {
            if (produto.getCodigoDeBarras().equals(codigoDeBarras)) {
                return produto;
            }
        }
        return null;
    }

    private static Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}
