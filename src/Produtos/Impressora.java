package Produtos;

public class Impressora extends Produto {
    private String tecnologiaImpressao;
    private boolean colorida;
    private int ppm; // Páginas por minuto

    public Impressora(String modelo, String codigoDeBarras, double valor) {
        super(modelo, codigoDeBarras, valor);
        this.tecnologiaImpressao = tecnologiaImpressao;
        this.colorida = colorida;
        this.ppm = ppm;
    }

    @Override
    public String getTipo() {
        return "Impressora";
    }

    public String getTecnologiaImpressao() {
        return tecnologiaImpressao;
    }

    public boolean isColorida() {
        return colorida;
    }

    public int getPpm() {
        return ppm;
    }

    @Override
    public String toString() {
        return "Impressora{" +
                "modelo='" + getModelo() + '\'' +
                ", codigoDeBarras='" + getCodigoDeBarras() + '\'' +
                ", valor=" + getValor() +
                ", tecnologiaImpressao='" + tecnologiaImpressao + '\'' +
                ", colorida=" + colorida +
                ", ppm=" + ppm +
                ", disponivel=" + isDisponivel() +
                '}';
    }
}
