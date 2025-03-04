package Produtos;

public interface ComodidadeAdicional {

    boolean isComodidadeAdicionalDisponivel();
    String oferecerComodidadeAdicional() throws UnsupportedOperationException;

    double getCustoComodidadeAdicional();
    String getDescricaoComodidadeAdicional();
}
