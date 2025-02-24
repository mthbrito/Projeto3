package locadora.model;

import locadora.utils.DataHandler;

import java.io.*;

public class Pagamento {

    private static int idPagamentoCounter = carregarIdPagamentoCounter();
    private int idPagamento;
    private int idLocacao;
    private double valorPago;
    private String dataPagamento;
    private final MetodosPagamento metodoPagamento;

    public Pagamento(int idLocacao, double valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        this.idPagamento = idPagamentoCounter++;
        salvarIdPagamentoCounter(idPagamentoCounter);
        this.idLocacao = idLocacao;
        this.valorPago = valorPago;
        this.dataPagamento = new DataHandler().formatarData(dataPagamento);
        this.metodoPagamento = metodoPagamento;
    }

    public Pagamento(int idPagamento, int idLocacao, double valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        this.idPagamento = idPagamento;
        this.idLocacao = idLocacao;
        this.valorPago = valorPago;
        this.dataPagamento = new DataHandler().formatarData(dataPagamento);
        this.metodoPagamento = metodoPagamento;
    }

    private static int carregarIdPagamentoCounter() {
        File file = new File("src/main/java/locadora/utils/idPagamentoCounter.txt");
        if (!file.exists()) {
            return 1000;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro: " + e.getMessage());
            return 1000;
        }
    }

    private static void salvarIdPagamentoCounter(int idCounter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/locadora/utils/idPagamentoCounter.txt"))) {
            writer.write(String.valueOf(idCounter));
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
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
