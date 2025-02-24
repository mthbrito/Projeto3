package locadora.controller;

import locadora.dao.PagamentoDAO;
import locadora.model.Cliente;
import locadora.model.MetodosPagamento;
import locadora.model.Pagamento;

import javax.swing.*;

public class PagamentoController {

    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public void registrarPagamento(JComboBox<String> comboBoxLocacoes, JTextField txtValorPago, JTextField txtDataPagamento, JComboBox<MetodosPagamento> comboBoxMetodoPagamento) {
        String dadosLocacoes = (String) comboBoxLocacoes.getSelectedItem();
        int idLocacao = dadosLocacoes != null ? Integer.parseInt(dadosLocacoes.split("/")[0]) : 0;
        MetodosPagamento metodoPagamento = (MetodosPagamento) comboBoxMetodoPagamento.getSelectedItem();
        Pagamento pagamento = new Pagamento(idLocacao, Double.parseDouble(txtValorPago.getText()), txtDataPagamento.getText(), metodoPagamento);
        pagamentoDAO.salvar(pagamento);
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
}
