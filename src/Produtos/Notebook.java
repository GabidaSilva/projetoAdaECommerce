package Produtos;

public class Notebook extends Produto implements ComodidadeAdicional {

    private boolean bluetooth;
    private boolean cameraDeVideo;
    private boolean telaRetina;
    private boolean processadorRyzen;
    private static final double CUSTO_COMODIDADE = 50.0;

    public Notebook(String modelo, String codigoDeBarras, double valor) {
        super(modelo, codigoDeBarras, valor);
        this.bluetooth = bluetooth;
        this.cameraDeVideo = cameraDeVideo;
        this.telaRetina = telaRetina;
        this.processadorRyzen = processadorRyzen;
    }

    @Override
    public String getTipo() {
        return "Notebook";
    }

    @Override
    public boolean isComodidadeAdicionalDisponivel() {
        return bluetooth && cameraDeVideo && telaRetina && processadorRyzen;
    }

    @Override
    public String oferecerComodidadeAdicional() throws UnsupportedOperationException {
        if (isComodidadeAdicionalDisponivel()) {
            return "Pacote premium de software incluído para " + getModelo();
        } else {
            throw new UnsupportedOperationException("Comodidade adicional não disponível para este notebook.");
        }
    }

    @Override
    public double getCustoComodidadeAdicional() {
        return CUSTO_COMODIDADE;
    }

    @Override
    public String getDescricaoComodidadeAdicional() {
        return "Pacote premium de software para produtividade e entretenimento";
    }

    public boolean hasBluetooth() {
        return bluetooth;
    }

    public boolean hasCameraDeVideo() {
        return cameraDeVideo;
    }

    public boolean hasTelaRetina() {
        return telaRetina;
    }

    public boolean hasProcessadorRyzen() {
        return processadorRyzen;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "modelo='" + getModelo() + '\'' +
                ", codigoDeBarras='" + getCodigoDeBarras() + '\'' +
                ", valor=" + getValor() +
                ", bluetooth=" + bluetooth +
                ", cameraDeVideo=" + cameraDeVideo +
                ", telaRetina=" + telaRetina +
                ", processadorRyzen=" + processadorRyzen +
                ", disponivel=" + isDisponivel() +
                '}';
    }
}
