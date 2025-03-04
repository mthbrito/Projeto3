package locadora.controller;

import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.exception.LocacaoNaoExisteException;
import locadora.exception.PagamentoJaExisteException;
import locadora.exception.PagamentoNaoExisteException;
import locadora.model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static locadora.utils.DataHandler.converterDataArmazenada;
import static locadora.utils.DataHandler.converterDataInserida;

public class PagamentoController {

    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public void registrarPagamento(JComboBox<Integer> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String idLocacao = String.valueOf(comboBoxLocacoes.getSelectedItem());
        String valorPago = txtValorPago.getText().trim();
        String dataPagamento = txtDataPagamento.getText().trim();
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();

        if (isEntradasValidas(idLocacao, valorPago, dataPagamento, metodoPagamento)) {
            salvarPagamento(idLocacao, valorPago, dataPagamento, metodoPagamento);
            Veiculo veiculo = new LocacaoDAO().ler(idLocacao).getVeiculo();
            veiculo.setStatus(StatusVeiculo.DISPONIVEL);
            new VeiculoDAO().atualizar(veiculo);
        }
    }

    public void editarPagamento(JTextField txtIdPagamento, JComboBox<String> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String idLocacao = String.valueOf(comboBoxLocacoes.getSelectedItem());
        String valorPago = txtValorPago.getText().trim();
        String dataPagamento = txtDataPagamento.getText().trim();
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();
    }


//    public void editarPagamento(JTextField txtIdPagamento, JComboBox<String> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
//        String dadosLocacoes = (String) comboBoxLocacoes.getSelectedItem();
//        int idLocacao = dadosLocacoes != null ? Integer.parseInt(dadosLocacoes.split("/")[0]) : 0;
//        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();
//        Pagamento pagamento = new Pagamento(Integer.parseInt(txtIdPagamento.getText()), idLocacao, Double.parseDouble(txtValorPago.getText()), txtDataPagamento.getText(), metodoPagamento);
//        pagamentoDAO.atualizar(pagamento);
//    }

    public void excluirPagamento(JTextField txtIdPagamento) {
        pagamentoDAO.deletar(Integer.parseInt(txtIdPagamento.getText()));
    }

    public boolean isIdPagamentoValido(String idPagamento) {
        return idPagamento != null;
    }

    public boolean isIdLocacaoValido(String idLocacao) {
        return idLocacao != null;
    }

    public boolean isValorPagoValido(String valorPago) {
        if (valorPago != null && !valorPago.isEmpty()) {
            String regex = "^[0-9]+$";
            return valorPago.matches(regex);
        }
        return false;
    }

    public boolean isDataPagamentoValida(String dataPagamento) {
        if (dataPagamento != null && !dataPagamento.isEmpty()) {
            try {
                converterDataInserida(dataPagamento);
                return true;
            } catch (RuntimeException e) {
                return false;
            }
        }
        return false;
    }

    public boolean isMetodoPagamentoValido(MetodosPagamento metodosPagamento) {
        return metodosPagamento != null;
    }

    public boolean isEntradasValidas(String idLocacao, String valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        StringBuilder erros = new StringBuilder();
        if (!isIdLocacaoValido(idLocacao)) erros.append("- ID de locação inválido!\n");
        if (!isValorPagoValido(valorPago)) erros.append("- Valor pago inválido!\n");
        if (!isDataPagamentoValida(dataPagamento)) erros.append("- Data de pagamento inválida!\n");
        if (!isMetodoPagamentoValido(metodoPagamento)) erros.append("- Método de pagamento inválido!\n");

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(null, "Erros encontrados:\n" + erros, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void salvarPagamento(String idLocacao, String valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        try {
            Pagamento pagamento = new Pagamento(Integer.parseInt(idLocacao), Double.parseDouble(valorPago), dataPagamento, metodoPagamento);
            pagamentoDAO.salvar(pagamento);
            Veiculo veiculo = new LocacaoDAO().ler(idLocacao).getVeiculo();
            veiculo.setStatus(StatusVeiculo.DISPONIVEL);
            new VeiculoDAO().salvar(veiculo);
        } catch (PagamentoJaExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Pagamento lerPagamento(String idPagamento){
        try {
            Pagamento pagamento = pagamentoDAO.ler(Integer.parseInt(idPagamento));
            JOptionPane.showMessageDialog(null, "Pagamento encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return pagamento;
        } catch (PagamentoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void atualizarPagamento(String idLocacao, String valorPago, String dataPagamento, MetodosPagamento metodoPagamento){

    }

    public void deletarPagamento(String idPagamento){
        try {
            pagamentoDAO.deletar(Integer.parseInt(idPagamento));
            JOptionPane.showMessageDialog(null, "Pagamento excluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (PagamentoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double calcularPagamento(JComboBox<Integer> comboBoxLocacoes, JTextField txtDataPagamento) {
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
        if (pagamento.isAfter(devolucao)) {
            long numeroDiasAtrasado = ChronoUnit.DAYS.between(devolucao, pagamento);
            valorPago = valorPago + (100 * numeroDiasAtrasado);
            String valor = "Devolução atrasada! O novo valor incidido de multa é R$" + valorPago;
            JOptionPane.showMessageDialog(null, valor, "Multa de Atraso", JOptionPane.INFORMATION_MESSAGE);
        }
        return valorPago;
    }
}
