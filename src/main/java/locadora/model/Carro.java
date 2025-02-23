package locadora.model;

public class Carro extends Veiculo{

    public Carro(String placa, String modelo, int ano, StatusVeiculo status){
        super("Carro", placa, modelo, ano, status);
    }

    @Override
    double calcularCustoLocacao() {
        return 0;
    }

}
