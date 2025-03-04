package Produtos;

public abstract class Produto {
    protected String modelo;
    protected String codigoDeBarras;
    protected double valor;
    private boolean disponivel;

    public Produto(String modelo, String codigoDeBarras, double valor) {
        this.modelo = modelo;
        this.codigoDeBarras = codigoDeBarras;
        this.valor = valor;
        this.disponivel = true;
    }

    public String getModelo() { return modelo; }
    public String getCodigoDeBarras() { return codigoDeBarras; }
    public double getValor() { return valor; }
    public boolean isDisponivel() { return disponivel; }

    public void adquirir() { this.disponivel = false; }
    public void devolver() { this.disponivel = true; }

    public abstract String getTipo();

    public void setModelo(String novoModelo) {
    }

    public void setCodigoDeBarras(String novoCodigoDeBarras) {
    }
}
