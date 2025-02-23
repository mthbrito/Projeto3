package locadora.model;

public class Caminhao extends Veiculo{

    public Caminhao(String placa, String modelo, int ano, String status) {
        super("Caminhão",placa, modelo, ano, status);
    }

    @Override
    double calcularCustoLocacao() {
        return 0;
    }

}
