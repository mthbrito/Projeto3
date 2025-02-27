package locadora.model;

public abstract class Veiculo {

    protected String tipo;
    protected String placa;
    protected String modelo;
    protected int ano;
    protected StatusVeiculo status;

    public Veiculo(String tipo, String placa, String modelo, int ano, StatusVeiculo status) {
        this.tipo = tipo;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = status;
    }

    abstract double calcularCustoLocacao();

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Veiculo{" +
                "tipo='" + tipo + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", status='" + status + '\'' +
                '}';
    }
}
