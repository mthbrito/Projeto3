package locadora.view;

import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.dao.LocacaoDAO;
import locadora.model.MetodosPagamento;
import locadora.model.Pagamento;

import javax.swing.*;
import java.awt.*;

public class PagamentosView {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PagamentosView window = new PagamentosView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public PagamentosView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 430, 240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblPagamentos = new JLabel("Pagamentos");
        lblPagamentos.setHorizontalAlignment(SwingConstants.CENTER);
        lblPagamentos.setVerticalAlignment(SwingConstants.CENTER);
        lblPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPagamentos.setBounds(165, 10, 70, 20);
        frame.getContentPane().add(lblPagamentos);

        JPanel panelRegistroPagamento = new JPanel();
        panelRegistroPagamento.setLayout(null);
        panelRegistroPagamento.setBackground(new Color(255, 255, 255));
        panelRegistroPagamento.setBounds(10, 40, 395, 100);
        frame.getContentPane().add(panelRegistroPagamento);

        JLabel lblIdPagamento = new JLabel("ID (Pagamento)");
        lblIdPagamento.setVerticalAlignment(SwingConstants.CENTER);
        lblIdPagamento.setBounds(120, 10, 80, 20);
        panelRegistroPagamento.add(lblIdPagamento);

        JTextField txtIdPagamento = new JTextField();
        txtIdPagamento.setBounds(210, 10, 70, 20);
        panelRegistroPagamento.add(txtIdPagamento);

        JComboBox<String> comboBoxLocacoes = new JComboBox<>(new LocacaoDAO().listagemLocacoesCadastradas());
        comboBoxLocacoes.setBounds(65, 40, 125, 20);
        comboBoxLocacoes.setSelectedIndex(-1);
        panelRegistroPagamento.add(comboBoxLocacoes);

        JTextField txtValorPago = new JTextField();
        txtValorPago.setBounds(65, 70, 125, 20);
        panelRegistroPagamento.add(txtValorPago);

        JTextField txtDataPagamento = new JTextField();
        txtDataPagamento.setBounds(315, 40, 70, 20);
        panelRegistroPagamento.add(txtDataPagamento);

        JComboBox<MetodosPagamento> comboBoxMetodoPagamento = new JComboBox<>(MetodosPagamento.values());
        comboBoxMetodoPagamento.setBounds(315, 70, 70, 20);
        panelRegistroPagamento.add(comboBoxMetodoPagamento);

        JLabel lblLocacao = new JLabel("Locacão");
        lblLocacao.setBounds(10, 40, 45, 15);
        lblLocacao.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblLocacao);

        JLabel lblValorPago = new JLabel("ValorPago");
        lblValorPago.setBounds(10, 70, 45, 15);
        lblValorPago.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblValorPago);

        JLabel lblDataPagamento = new JLabel("Data de pagamento");
        lblDataPagamento.setBounds(200, 40, 120, 15);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblDataPagamento);

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento");
        lblMetodoPagamento.setBounds(200, 70, 120, 15);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblMetodoPagamento);

        JButton btnRegistrarPagamento = new JButton("<html><div style='text-align:center'>Registrar Pagamento</div></html>");
        btnRegistrarPagamento.setBounds(10, 150, 90, 40);
        frame.add(btnRegistrarPagamento);

        JButton btnEditarPagamento = new JButton("<html><div style='text-align:center'>Editar Pagamento</div></html>");
        btnEditarPagamento.setBounds(165, 150, 90, 40);
        frame.add(btnEditarPagamento);

        JButton btnExcluirPagamento = new JButton("<html><div style='text-align:center'>Excluir Pagamento</div></html>");
        btnExcluirPagamento.setBounds(315, 150, 90, 40);
        frame.add(btnExcluirPagamento);

        btnRegistrarPagamento.addActionListener(e -> new PagamentoController().registrarPagamento(comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento));
        btnEditarPagamento.addActionListener(e -> new PagamentoController().editarPagamento(txtIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento));
        btnExcluirPagamento.addActionListener(e -> new PagamentoController().excluirPagamento(txtIdPagamento));

    }
}
