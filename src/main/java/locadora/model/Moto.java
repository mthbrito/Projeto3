package locadora.model;

public class Moto extends Veiculo{

    public Moto(String placa, String modelo, int ano, String status){
        super("Moto", placa, modelo, ano, status);
    }

    @Override
    double calcularCustoLocacao() {
        return 0;
    }
}
