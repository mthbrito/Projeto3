package locadora.model;

import java.util.Date;

public class Pagamento {

    private int idPagamento;
    private int idLocacao;
    private double valorPago;
    private Date dataPagamento;
    private String metodoPagamento;

    public Pagamento(int idPagamento, int idLocacao, double valorPago, Date dataPagamento, String metodoPagamento) {
        this.idPagamento = idPagamento;
        this.idLocacao = idLocacao;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", idLocacao=" + idLocacao +
                ", valorPago=" + valorPago +
                ", dataPagamento=" + dataPagamento +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                '}';
    }

}
