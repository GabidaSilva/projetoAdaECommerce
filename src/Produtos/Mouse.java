package Produtos;

public class Mouse extends Produto {
    private int dpi;
    private boolean wireless;
    private int numBotoes;

    public Mouse(String modelo, String codigoDeBarras, double valor) {
        super(modelo, codigoDeBarras, valor);
        this.dpi = dpi;
        this.wireless = wireless;
        this.numBotoes = numBotoes;
    }

    @Override
    public String getTipo() {
        return "Mouse";
    }

    public int getDpi() {
        return dpi;
    }

    public boolean isWireless() {
        return wireless;
    }

    public int getNumBotoes() {
        return numBotoes;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "modelo='" + getModelo() + '\'' +
                ", codigoDeBarras='" + getCodigoDeBarras() + '\'' +
                ", valor=" + getValor() +
                ", dpi=" + dpi +
                ", wireless=" + wireless +
                ", numBotoes=" + numBotoes +
                ", disponivel=" + isDisponivel() +
                '}';
    }
}
