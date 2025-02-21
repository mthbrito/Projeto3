package locadora.model;

import java.util.Date;

public class Locacao {

    private Cliente cliente;
    private Date dataDeRetirada;
    private Date dataDeDevolucao;

    public Locacao(Cliente cliente, Date dataDeRetirada, Date dataDeDevolucao) {
        this.cliente = cliente;
        this.dataDeRetirada = dataDeRetirada;
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(Date dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }

    public Date getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(Date dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

}
