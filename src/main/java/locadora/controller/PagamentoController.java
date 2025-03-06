package locadora.controller;

import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
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

    public static void resetarEntradas(JComboBox<Integer> comboBoxIdPagamento, JComboBox<Integer> comboBoxIdLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        comboBoxIdPagamento.setSelectedIndex(-1);
        comboBoxIdLocacoes.setSelectedIndex(-1);
        txtValorPago.setText(null);
        txtDataPagamento.setText(null);
        comboBoxMetodoPagamento.setSelectedIndex(-1);
    }

//    public Integer[] getIdLocacaoDeIdPagamento(JComboBox<Integer> comboBoxIdPagamento, JComboBox<Integer> comboBoxIdLocacoes) {
//        if(comboBoxIdPagamento != null && comboBoxIdLocacoes != null){
//            String idPagamento = String.valueOf(comboBoxIdPagamento.getSelectedItem());
//            String idLocacao = String.valueOf(lerPagamento(idPagamento).getIdLocacao());
//            return new Integer[]{Integer.parseInt(idLocacao)};
//        }
//        return new Integer[0];
//    }

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

    public void editarPagamento(JComboBox<Integer> comboBoxIdPagamento, JComboBox<Integer> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String idPagamento = String.valueOf(comboBoxIdPagamento.getSelectedItem());
        String idLocacao = String.valueOf(comboBoxLocacoes.getSelectedItem());
        String valorPago = txtValorPago.getText().trim();
        String dataPagamento = txtDataPagamento.getText().trim();
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();

        if (isIdPagamentoValido(idPagamento) && isEntradasValidas(idLocacao, valorPago, dataPagamento, metodoPagamento)) {
            atualizarPagamento(idPagamento, idLocacao, valorPago, dataPagamento, metodoPagamento);
        }
    }

    public void excluirPagamento(JComboBox<Integer> comboBoxIdPagamento) {
        String idPagamento = String.valueOf(comboBoxIdPagamento);
        deletarPagamento(idPagamento);
    }

    public boolean isIdPagamentoValido(String idPagamento) {
        return idPagamento != null;
    }

    public boolean isIdLocacaoValido(String idLocacao) {
        return idLocacao != null;
    }

    public boolean isValorPagoValido(String valorPago) {
        if (valorPago != null && !valorPago.isEmpty()) {
            String regex = "^[0-9]+.[0-9]{0,2}$";
            return valorPago.matches(regex);
        }
        return false;
    }

    public boolean isDataPagamentoValida(String idLocacao, String dataPagamento) {
        Locacao locacao = new LocacaoDAO().ler(idLocacao);
        String dataRetirada = locacao.getDataDeRetirada();

        if (dataPagamento != null && !dataPagamento.isEmpty()) {
            try {
                LocalDate retirada = converterDataArmazenada(dataRetirada);
                LocalDate pagamento = converterDataInserida(dataPagamento);
                return !pagamento.isBefore(retirada);
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
        if (!isDataPagamentoValida(idLocacao, dataPagamento)) erros.append("- Data de pagamento inválida!\n");
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
            new VeiculoDAO().atualizar(veiculo);
        } catch (PagamentoJaExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Pagamento lerPagamento(String idPagamento) {
        try {
            Pagamento pagamento = pagamentoDAO.ler(Integer.parseInt(idPagamento));
            JOptionPane.showMessageDialog(null, "Pagamento encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return pagamento;
        } catch (PagamentoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void atualizarPagamento(String idPagamento, String idLocacao, String valorPago, String dataPagamento, MetodosPagamento metodoPagamento) {
        try {
            Pagamento pagamento = new Pagamento(Integer.parseInt(idPagamento), Integer.parseInt(idLocacao), Double.parseDouble(valorPago), dataPagamento, metodoPagamento);
            Veiculo veiculo = new LocacaoDAO().ler(pagamento.getIdLocacao()).getVeiculo();
            veiculo.setStatus(StatusVeiculo.DISPONIVEL);
            new VeiculoDAO().atualizar(veiculo);
            pagamentoDAO.atualizar(pagamento);
            JOptionPane.showMessageDialog(null, "Pagamento atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (PagamentoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarPagamento(String idPagamento) {
        try {
            pagamentoDAO.deletar(Integer.parseInt(idPagamento));
            JOptionPane.showMessageDialog(null, "Pagamento excluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (PagamentoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Pagamento não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double calcularPagamento(JComboBox<Integer> comboBoxLocacoes, JTextField txtDataPagamento) {
        String idLocacao = String.valueOf(comboBoxLocacoes.getSelectedItem());
        String dataPagamento = txtDataPagamento.getText().trim();

        double valorPago = 0;
        if (isIdLocacaoValido(idLocacao) && isDataPagamentoValida(idLocacao, dataPagamento)) {
            try {
                valorPago = 0;
                Locacao locacao = new LocacaoDAO().ler(idLocacao);
                LocalDate retirada = converterDataArmazenada(locacao.getDataDeRetirada());
                LocalDate devolucao = converterDataArmazenada(locacao.getDataDeDevolucao());
                LocalDate pagamento = converterDataInserida(dataPagamento);
                long numeroDias = ChronoUnit.DAYS.between(retirada, devolucao);
                double diariaCaminhao = 80.00;
                double diariaCarro = 50.00;
                double diariaMoto = 25.00;

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
                if (pagamento.isAfter(devolucao)) {
                    long numeroDiasAtrasado = ChronoUnit.DAYS.between(devolucao, pagamento);
                    valorPago = valorPago + (100 * numeroDiasAtrasado);
                    String valor = "Devolução atrasada! O novo valor incidido de multa é R$" + valorPago;
                    JOptionPane.showMessageDialog(null, valor, "Multa de Atraso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao calcular valor pago!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valorPago;
    }
}
