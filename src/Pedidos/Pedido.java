package Pedidos;

import Clientes.Cliente;
import Notificacoes.Notificador;
import Produtos.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private final int id;
    private final Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private String status;
    private final Date dataCriacao;

    public Pedido(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.status = "Aberto";
        this.dataCriacao = new Date();
    }

    public void adicionarItem(Produto produto, int quantidade, double precoVenda) {
        if (status.equals("Aberto")) {
            itens.add(new ItemPedido(produto, quantidade, precoVenda));
            System.out.println("Item adicionado: " + produto.getModelo() + ", Quantidade: " + quantidade + ", Preço: " + precoVenda);
        } else {
            throw new IllegalStateException("Não é possível adicionar itens a um pedido que não está aberto.");
        }
    }

    public void removerItem(Produto produto) {
        if (status.equals("Aberto")) {
            itens.removeIf(item -> item.getProduto().equals(produto));
        } else {
            throw new IllegalStateException("Não é possível remover itens de um pedido que não está aberto.");
        }
    }

    public void alterarQuantidade(Produto produto, int novaQuantidade) {
        if (status.equals("Aberto")) {
            for (ItemPedido item : itens) {
                if (item.getProduto().equals(produto)) {
                    item.setQuantidade(novaQuantidade);
                    return;
                }
            }
            throw new IllegalArgumentException("Produto não encontrado no pedido.");
        } else {
            throw new IllegalStateException("Não é possível alterar a quantidade de itens em um pedido que não está aberto.");
        }
    }

    public BigDecimal calcularTotal() {
        return BigDecimal.valueOf( itens.stream().mapToDouble( ItemPedido::calcularTotal).sum() );
    }

    public void finalizar(Notificador notificador) {
        if (calcularTotal().compareTo(BigDecimal.ZERO) > 0) {
            status = "Aguardando pagamento";
            notificador.notificar("Seu pedido foi finalizado e está aguardando pagamento.", cliente);
        } else {
            throw new IllegalStateException("Não é possível finalizar um pedido vazio ou com valor total zero.");
        }
    }

    public void pagar(Notificador notificador) {
        if (status.equals("Aguardando pagamento")) {
            status = "Pago";
            notificador.notificar("Pagamento Recebido.", cliente);
        } else {
            throw new IllegalStateException("Não é possível pagar um pedido que não está aguardando pagamento.");
        }
    }

    public void entregar(Notificador notificador) {
        if (status.equals("Pago")) {
            status = "Entregue";
            notificador.notificar("Seu pedido foi entregue!", cliente);
        } else {
            throw new IllegalStateException("Não é possível entregar um pedido que não foi pago.");
        }
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return new ArrayList<>(itens); }
    public String getStatus() { return status; }
    public Date getDataCriacao() { return (Date) dataCriacao.clone(); }
}
