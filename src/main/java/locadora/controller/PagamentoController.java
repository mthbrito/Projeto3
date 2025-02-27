package locadora.controller;

import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static locadora.utils.DataHandler.*;

public class PagamentoController {

    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public void registrarPagamento(JComboBox<String> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String dadosLocacoes = (String) comboBoxLocacoes.getSelectedItem();
        int idLocacao = dadosLocacoes != null ? Integer.parseInt(dadosLocacoes.split("/")[0]) : 0;
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();
        Pagamento pagamento = new Pagamento(idLocacao, Double.parseDouble(txtValorPago.getText()), txtDataPagamento.getText(), metodoPagamento);
        pagamentoDAO.salvar(pagamento);
        Veiculo veiculo = new LocacaoDAO().ler(idLocacao).getVeiculo();
        veiculo.setStatus(StatusVeiculo.DISPONIVEL);
        new VeiculoDAO().atualizar(veiculo);
    }

    public void editarPagamento(JTextField txtIdPagamento, JComboBox<String> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String dadosLocacoes = (String) comboBoxLocacoes.getSelectedItem();
        int idLocacao = dadosLocacoes != null ? Integer.parseInt(dadosLocacoes.split("/")[0]) : 0;
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();
        Pagamento pagamento = new Pagamento(Integer.parseInt(txtIdPagamento.getText()), idLocacao, Double.parseDouble(txtValorPago.getText()), txtDataPagamento.getText(), metodoPagamento);
        pagamentoDAO.atualizar(pagamento);
    }

    public void excluirPagamento(JTextField txtIdPagamento) {
        pagamentoDAO.deletar(Integer.parseInt(txtIdPagamento.getText()));
    }

    public double calcularPagamento(JComboBox<String> comboBoxLocacoes, JTextField txtDataPagamento) {
        String dadosLocacoes = (String) comboBoxLocacoes.getSelectedItem();
        int idLocacao = dadosLocacoes != null ? Integer.parseInt(dadosLocacoes.split("/")[0]) : 0;
        Locacao locacao = new LocacaoDAO().ler(idLocacao);
        String dataRetirada = locacao.getDataDeRetirada();
        String dataDevolucao = locacao.getDataDeDevolucao();
        LocalDate retirada = converterDataArmazenada(dataRetirada);
        LocalDate devolucao = converterDataArmazenada(dataDevolucao);
        long numeroDias = ChronoUnit.DAYS.between(retirada, devolucao);
        double diariaCaminhao = 600.00;
        double diariaCarro = 250.00;
        double diariaMoto = 125.00;
        double valorPago = 0;
        switch (locacao.getVeiculo().getTipo()) {
            case "Caminhão":
                valorPago = diariaCaminhao * numeroDias;
                break;
            case "Carro":
                valorPago = diariaCarro * numeroDias;
                break;
            case "Moto":
                valorPago = diariaMoto * numeroDias;
                break;
        }
        String dataPagamento = txtDataPagamento.getText();
        LocalDate pagamento = converterDataInserida(dataPagamento);
        if(pagamento.isAfter(devolucao)) {
            long numeroDiasAtrasado = ChronoUnit.DAYS.between(devolucao, pagamento);
            valorPago = valorPago + (100 * numeroDiasAtrasado);
            String valor = "Devolução atrasada! O novo valor incidido de multa é R$" + valorPago;
            JOptionPane.showMessageDialog(null, valor, "Multa de Atraso", JOptionPane.INFORMATION_MESSAGE);
        }
        return valorPago;
    }
}
