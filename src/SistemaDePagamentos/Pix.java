package SistemaDePagamentos;

import java.math.BigDecimal;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pix implements MetodoPagamento {
    private String chavePix;

    public Pix() {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(BigDecimal valor) throws IllegalArgumentException, PagamentoException {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }

        if (!validarChavePix()) {
            throw new PagamentoException("Chave Pix inválida.");
        }

        // Simulação de processamento do pagamento
        boolean pagamentoAprovado = simularProcessamentoPagamento();

        if (pagamentoAprovado) {
            String codigoTransacao = gerarCodigoTransacao();
            LocalDateTime dataHoraTransacao = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            System.out.println("Pagamento via Pix processado com sucesso:");
            System.out.println("Valor: R$ " + valor.setScale(2, BigDecimal.ROUND_HALF_UP));
            System.out.println("Chave Pix: " + mascaraChavePix());
            System.out.println("Código da Transação: " + codigoTransacao);
            System.out.println("Data/Hora: " + dataHoraTransacao.format(formatter));
            return true;
        } else {
            throw new PagamentoException("Erro: Pagamento não aprovado. Por favor, tente novamente ou use outro método de pagamento.");
        }
    }

    @Override
    public String getDescricao() {
        return "Pagamento via Pix";
    }

    @Override
    public boolean processarPagamento(double valor) {
        return false;
    }

    private boolean validarChavePix() {
        // Implementar lógica de validação da chave Pix
        return chavePix != null && !chavePix.isEmpty();
    }

    private boolean simularProcessamentoPagamento() {
        // Simula uma chance de 95% de aprovação do pagamento
        return new Random().nextDouble() < 0.95;
    }

    private String gerarCodigoTransacao() {
        return String.format("%010d", new Random().nextInt(1000000000));
    }

    private String mascaraChavePix() {
        if (chavePix.length() <= 4) {
            return chavePix;
        }
        return chavePix.substring(0, 4) + "*".repeat(chavePix.length() - 4);
    }
}
