package Produtos;

public class PersonalComputer extends Produto implements ComodidadeAdicional {

    private boolean bluetooth;
    private boolean cameraDeVideo;
    private static final double CUSTO_COMODIDADE = 75.0;

    public PersonalComputer(String modelo, String codigoDeBarras, double valor) {
        super(modelo, codigoDeBarras, valor);
        this.bluetooth = bluetooth;
        this.cameraDeVideo = cameraDeVideo;
    }

    @Override
    public String getTipo() {
        return "Personal Computer";
    }

    @Override
    public boolean isComodidadeAdicionalDisponivel() {
        return bluetooth && cameraDeVideo;
    }

    @Override
    public String oferecerComodidadeAdicional() throws UnsupportedOperationException {
        if (isComodidadeAdicionalDisponivel()) {
            return "Pacote de software profissional e suporte técnico premium para " + getModelo();
        } else {
            throw new UnsupportedOperationException("Comodidade adicional não disponível para este PC.");
        }
    }

    @Override
    public double getCustoComodidadeAdicional() {
        return CUSTO_COMODIDADE;
    }

    @Override
    public String getDescricaoComodidadeAdicional() {
        return "Pacote de software profissional e suporte técnico premium";
    }

    public boolean hasBluetooth() {
        return bluetooth;
    }

    public boolean hasCameraDeVideo() {
        return cameraDeVideo;
    }

    @Override
    public String toString() {
        return "PersonalComputer{" +
                "modelo='" + getModelo() + '\'' +
                ", codigoDeBarras='" + getCodigoDeBarras() + '\'' +
                ", valor=" + getValor() +
                ", bluetooth=" + bluetooth +
                ", cameraDeVideo=" + cameraDeVideo +
                ", disponivel=" + isDisponivel() +
                '}';
    }
}
