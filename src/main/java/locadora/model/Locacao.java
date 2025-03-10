package locadora.model;

import locadora.utils.DataHandler;

import java.io.*;

public class Locacao {

    private static final String caminhoIdLocacaoCounter = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\data\\txt\\idLocacaoCounter.txt";
    private static int idLocacaoCounter = carregarIdLocacaoCounter();
    private int idLocacao;
    private Cliente cliente;
    private Veiculo veiculo;
    private String dataDeRetirada;
    private String dataDeDevolucao;

    public Locacao(Cliente cliente, Veiculo veiculo, String dataDeRetirada, String dataDeDevolucao) {
        this.idLocacao = idLocacaoCounter++;
        salvarIdLocacaoCounter(idLocacaoCounter);
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataDeRetirada = new DataHandler().formatarData(dataDeRetirada);
        this.dataDeDevolucao = new DataHandler().formatarData(dataDeDevolucao);
    }

    public Locacao(int idLocacao, Cliente cliente, Veiculo veiculo, String dataDeRetirada, String dataDeDevolucao) {
        this.idLocacao = idLocacao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataDeRetirada = new DataHandler().formatarData(dataDeRetirada);
        this.dataDeDevolucao = new DataHandler().formatarData(dataDeDevolucao);
    }

    private static int carregarIdLocacaoCounter() {
        File file = new File(caminhoIdLocacaoCounter);
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

    private static void salvarIdLocacaoCounter(int idCounter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoIdLocacaoCounter))) {
            writer.write(String.valueOf(idCounter));
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
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
                "idLocacao=" + idLocacao +
                ", cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataDeRetirada='" + dataDeRetirada + '\'' +
                ", dataDeDevolucao='" + dataDeDevolucao + '\'' +
                '}';
    }
}
