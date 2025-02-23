package locadora.model;

import locadora.utils.DataHandler;

public class Pagamento {

    private int idPagamento;
    private int idLocacao;
    private double valorPago;
    private String dataPagamento;
    private MetodosPagamento metodoPagamento;

    public Pagamento(int idLocacao, double valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        this.idPagamento += 1;
        this.idLocacao = idLocacao;
        this.valorPago = valorPago;
        this.dataPagamento = new DataHandler().formatarData(dataPagamento);
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public MetodosPagamento getMetodoPagamento() {
        return metodoPagamento;
    }


    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", idLocacao=" + idLocacao +
                ", valorPago=" + valorPago +
                ", dataPagamento='" + dataPagamento + '\'' +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                '}';
    }
}
