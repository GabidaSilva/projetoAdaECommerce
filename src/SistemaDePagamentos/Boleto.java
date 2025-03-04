package SistemaDePagamentos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Boleto implements MetodoPagamento {
    private String numeroBoleto;
    private LocalDate dataVencimento;
    private static final int DIAS_PARA_VENCIMENTO = 3;

    public Boleto() {
        this.numeroBoleto = gerarNumeroBoleto();
        this.dataVencimento = LocalDate.now().plusDays(DIAS_PARA_VENCIMENTO);
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }

        System.out.println("Boleto gerado com sucesso:");
        System.out.println("Número do Boleto: " + numeroBoleto);
        System.out.println("Valor: R$ " + String.format("%.2f", valor));
        System.out.println("Data de Vencimento: " + dataVencimento);
        System.out.println("Por favor, efetue o pagamento até a data de vencimento.");

        return true;
    }

    private String gerarNumeroBoleto() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 48; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public String getNumeroBoleto() {
        return numeroBoleto;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    @Override
    public boolean processarPagamento(BigDecimal valor) throws IllegalArgumentException, PagamentoException {
        return false;
    }

    @Override
    public boolean isDisponivel() {
        return MetodoPagamento.super.isDisponivel();
    }

    @Override
    public String getDescricao() {
        return "";
    }
}
