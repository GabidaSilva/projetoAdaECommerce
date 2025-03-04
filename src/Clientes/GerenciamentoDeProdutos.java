package Clientes;
import Produtos.Produto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciamentoDeProdutos {
    private List<Produto> listaProdutos;

    public GerenciamentoDeProdutos() {
        listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
        System.out.println(produto.getModelo() + " adicionado com sucesso!");
    }

    public void removerProduto(String modelo) {
        Produto produto = buscarProduto(modelo);
        if (produto != null) {
            listaProdutos.remove(produto);
            System.out.println(produto.getModelo() + " removido com sucesso!");
        } else {
            System.out.println("Produto " + modelo + " não encontrado.");
        }
    }

    public Produto buscarProduto(String modelo) {
        return listaProdutos.stream()
                .filter(p -> p.getModelo().equalsIgnoreCase(modelo))
                .findFirst()
                .orElse(null);
    }

    public Produto buscarProdutoPorCodigoDeBarras(String codigoDeBarras) {
        return listaProdutos.stream()
                .filter(p -> p.getCodigoDeBarras().equals(codigoDeBarras))
                .findFirst()
                .orElse(null);
    }

    public void listarTodosProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Lista de todos os produtos:");
            listaProdutos.forEach(p -> System.out.println(p.getModelo() + " - " + p.getCodigoDeBarras() + " - " + (p.isDisponivel() ? "Disponível" : "Indisponível")));
        }
    }

    public void listarProdutosDisponiveis() {
        List<Produto> disponíveis = listaProdutos.stream()
                .filter(Produto::isDisponivel)
                .collect(Collectors.toList());

        if (disponíveis.isEmpty()) {
            System.out.println("Não há produtos disponíveis no momento.");
        } else {
            System.out.println("Produtos disponíveis para aquisição:");
            disponíveis.forEach(p -> System.out.println(p.getModelo() + " - " + p.getCodigoDeBarras()));
        }
    }

    public void adquirirProduto(String modelo) {
        Produto produto = buscarProduto(modelo);
        if (produto != null && produto.isDisponivel()) {
            produto.adquirir();
        } else {
            if (produto != null) {
                System.out.println("O produto " + modelo + " não está disponível para aquisição.");
            } else {
                System.out.println("Produto " + modelo + " não encontrado.");
            }
        }
    }

    public void devolverProduto(String modelo) {
        Produto produto = buscarProduto(modelo);
        if (produto != null) {
            produto.devolver();
        } else {
            System.out.println("Produto " + modelo + " não encontrado.");
        }
    }

    public void atualizarProduto(String modelo, String novoModelo, String novoCodigoDeBarras) {
        Produto produto = buscarProduto(modelo);
        if (produto != null) {
            produto.setModelo(novoModelo);
            produto.setCodigoDeBarras(novoCodigoDeBarras);
            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto " + modelo + " não encontrado.");
        }
    }

    public int quantidadeProdutosDisponiveis() {
        return (int) listaProdutos.stream().filter(Produto::isDisponivel).count();
    }

    public void ordenarProdutosPorModelo() {
        listaProdutos.sort(Comparator.comparing(Produto::getModelo));
    }

    public void ordenarProdutosPorDisponibilidade() {
        listaProdutos.sort(Comparator.comparing(Produto::isDisponivel).reversed());
    }
}
