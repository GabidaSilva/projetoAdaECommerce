package Pedidos;

import Produtos.Produto;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() { return produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public double calcularTotal() { return precoVenda * quantidade; }
}
