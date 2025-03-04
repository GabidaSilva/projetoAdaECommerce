package Produtos;

import Clientes.Cliente;
import Clientes.PessoaJuridica;
import java.time.LocalDateTime;

public class Aquisicao {
    private final Cliente cliente;
    private final Produto produto;
    private LocalDateTime dataAquisicao;
    private LocalDateTime dataDevolucao;
    private static final double DESCONTO_PESSOA_JURIDICA = 0.1; // 10% de desconto

    public Aquisicao(Cliente cliente, Produto produto) {
        if (cliente == null || produto == null) {
            throw new IllegalArgumentException("Cliente e Produto não podem ser nulos.");
        }
        this.cliente = cliente;
        this.produto = produto;
    }

    public void realizarAquisicao() {
        if (produto.isDisponivel()) {
            produto.adquirir();
            this.dataAquisicao = LocalDateTime.now();
            double valor = calcularValorAquisicao();
            System.out.printf("Aquisição realizada com sucesso! Valor total: R$ %.2f%n", valor);
            cliente.exibirDetalhesAquisicao(produto);
        } else {
            throw new IllegalStateException("Produto indisponível para aquisição.");
        }
    }

    public double calcularValorAquisicao() {
        double valorBase = produto.getValor();
        if (cliente instanceof PessoaJuridica) {
            valorBase *= (1 - DESCONTO_PESSOA_JURIDICA);
        }
        return valorBase * cliente.getDiasLocacao();
    }

    public void devolver() {
        if (!produto.isDisponivel()) {
            produto.devolver();
            this.dataDevolucao = LocalDateTime.now();
            System.out.println("Produto devolvido com sucesso!");
        } else {
            throw new IllegalStateException("Este produto já está disponível no sistema.");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDateTime getDataAquisicao() {
        return dataAquisicao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Aquisicao{" +
                "cliente=" + cliente.getNome() +
                ", produto=" + produto.getModelo() +
                ", dataAquisicao=" + dataAquisicao +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
