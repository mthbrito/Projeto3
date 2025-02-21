package locadora.model;

public class Carro extends Veiculo{

    public Carro(String placa, String modelo, int ano, String status){
        super(placa, modelo, ano, status);
    }

    @Override
    double calcularCustoLocacao() {
        return 0;
    }
}
