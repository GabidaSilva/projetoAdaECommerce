package SistemaDePagamentos;

import java.math.BigDecimal;
import java.util.Random;

public class CartaoDeCredito implements MetodoPagamento {
    private String numeroCartao;
    private String nomePortador;
    private String dataValidade;
    private String codigoSeguranca;

    public CartaoDeCredito() {
        this.numeroCartao = numeroCartao;
        this.nomePortador = nomePortador;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
    }

    @Override
    public boolean processarPagamento(BigDecimal valor) throws IllegalArgumentException, PagamentoException {
        return false;
    }

    @Override
    public String getDescricao() {
        return "";
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }

        if (!validarCartao()) {
            System.out.println("Erro: Cartão de crédito inválido.");
            return false;
        }

        // Simulação de processamento do pagamento
        boolean pagamentoAprovado = simularProcessamentoPagamento();

        if (pagamentoAprovado) {
            System.out.println("Pagamento via Cartão de Crédito no valor de R$ " + String.format("%.2f", valor) + " processado com sucesso.");
            System.out.println("Número do Cartão: **** **** **** " + numeroCartao.substring(numeroCartao.length() - 4));
            System.out.println("Portador: " + nomePortador);
            return true;
        } else {
            System.out.println("Erro: Pagamento não aprovado. Por favor, verifique seu saldo ou entre em contato com seu banco.");
            return false;
        }
    }

    private boolean validarCartao() {
        // Implementar lógica de validação do cartão (número, data de validade, etc.)
        return numeroCartao != null && numeroCartao.length() == 16 &&
                nomePortador != null && !nomePortador.isEmpty() &&
                dataValidade != null && dataValidade.matches("\\d{2}/\\d{2}") &&
                codigoSeguranca != null && codigoSeguranca.length() == 3;
    }

    private boolean simularProcessamentoPagamento() {
        // Simula uma chance de 90% de aprovação do pagamento
        return new Random().nextDouble() < 0.9;
    }
}
