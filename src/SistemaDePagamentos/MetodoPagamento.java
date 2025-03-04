package SistemaDePagamentos;

import java.math.BigDecimal;

public interface MetodoPagamento {

    boolean processarPagamento(BigDecimal valor) throws IllegalArgumentException, PagamentoException;

    default boolean isDisponivel() {
        return true;
    }

    String getDescricao();

    boolean processarPagamento(double valor);
}
