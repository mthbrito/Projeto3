package locadora.model;

public abstract class Veiculo {

    protected String placa;
    protected String modelo;
    protected int ano;
    protected String status;

    public Veiculo(String placa, String modelo, int ano, String status) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = status;
    }

    abstract double calcularCustoLocacao();

}
