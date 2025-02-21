package locadora.model;

import locadora.Utils.Data;

import java.time.LocalDate;
import java.util.Date;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private String dataDeRetirada;
    private String dataDeDevolucao;

    public Locacao(Cliente cliente, Veiculo veiculo, String dataDeRetirada, String dataDeDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataDeRetirada = new Data().formataData(dataDeRetirada);
        this.dataDeDevolucao = new Data().formataData(dataDeDevolucao);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(String dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }

    public String getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(String dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataDeRetirada='" + dataDeRetirada + '\'' +
                ", dataDeDevolucao='" + dataDeDevolucao + '\'' +
                '}';
    }

}
