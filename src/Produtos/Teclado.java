package Produtos;

public class Teclado extends Produto {
    private boolean mecanico;
    private String layout;
    private boolean retroiluminado;

    public Teclado(String modelo, String codigoDeBarras, double valor) {
        super(modelo, codigoDeBarras, valor);
        this.mecanico = mecanico;
        this.layout = layout;
        this.retroiluminado = retroiluminado;
    }

    @Override
    public String getTipo() {
        return "Teclado";
    }

    public boolean isMecanico() {
        return mecanico;
    }

    public String getLayout() {
        return layout;
    }

    public boolean isRetroiluminado() {
        return retroiluminado;
    }

    @Override
    public String toString() {
        return "Teclado{" +
                "modelo='" + getModelo() + '\'' +
                ", codigoDeBarras='" + getCodigoDeBarras() + '\'' +
                ", valor=" + getValor() +
                ", mecanico=" + mecanico +
                ", layout='" + layout + '\'' +
                ", retroiluminado=" + retroiluminado +
                ", disponivel=" + isDisponivel() +
                '}';
    }
}
