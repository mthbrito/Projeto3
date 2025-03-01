package locadora.view;

import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.MetodosPagamento;
import locadora.model.Pagamento;

import javax.swing.*;
import java.awt.*;

public class PagamentosView {

    private final JFrame frame = new JFrame();

    public PagamentosView() {
        initialize();
    }

    private void initialize() {
        frame.setBounds(100, 100, 450,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(46, 107, 240));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblPagamentos = new JLabel("Pagamentos");
        lblPagamentos.setHorizontalAlignment(SwingConstants.CENTER);
        lblPagamentos.setVerticalAlignment(SwingConstants.CENTER);
        lblPagamentos.setFont(new Font("Open Sans", Font.BOLD, 24));
        lblPagamentos.setForeground(Color.WHITE);
        lblPagamentos.setBounds(125, 10, 200, 40);
        panel.add(lblPagamentos);

        JPanel panelRegistroPagamento = new JPanel();
        panelRegistroPagamento.setLayout(null);
        panelRegistroPagamento.setBackground(new Color(255, 255, 255));
        panelRegistroPagamento.setBounds(20, 60, 390, 130);
        panel.add(panelRegistroPagamento);

        JLabel lblIdPagamento = new JLabel("ID (Pagamento)");
        lblIdPagamento.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblIdPagamento.setVerticalAlignment(SwingConstants.CENTER);
        lblIdPagamento.setBounds(40, 10, 90, 30);
        panelRegistroPagamento.add(lblIdPagamento);

        JTextField txtIdPagamento = new JTextField();
        txtIdPagamento.setFont(new Font("Open Sans", Font.BOLD, 12));
        txtIdPagamento.setBounds(150, 10, 95, 30);
        panelRegistroPagamento.add(txtIdPagamento);

        JComboBox<String> comboBoxLocacoes = new JComboBox<>(new LocacaoDAO().listagemLocacoesCadastradas());
        comboBoxLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxLocacoes.setBounds(75, 50, 95, 30);
        comboBoxLocacoes.setSelectedIndex(-1);
        panelRegistroPagamento.add(comboBoxLocacoes);

        JTextField txtValorPago = new JTextField();
        txtValorPago.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtValorPago.setBounds(75, 90, 95, 30);
        panelRegistroPagamento.add(txtValorPago);

        JTextField txtDataPagamento = new JTextField();
        txtDataPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtDataPagamento.setBounds(305, 50, 80, 30);
        panelRegistroPagamento.add(txtDataPagamento);

        JComboBox<MetodosPagamento> comboBoxMetodoPagamento = new JComboBox<>(MetodosPagamento.values());
        comboBoxMetodoPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxMetodoPagamento.setBounds(305, 90, 80, 30);
        panelRegistroPagamento.add(comboBoxMetodoPagamento);

        JLabel lblLocacao = new JLabel("Locacão");
        lblLocacao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblLocacao.setBounds(10, 50, 70, 30);
        lblLocacao.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblLocacao);

        JLabel lblValorPago = new JLabel("Valor Pago");
        lblValorPago.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblValorPago.setBounds(10, 90, 70, 30);
        lblValorPago.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblValorPago);

        JLabel lblDataPagamento = new JLabel("Data de pagamento");
        lblDataPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblDataPagamento.setBounds(175, 50, 140, 30);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblDataPagamento);

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento");
        lblMetodoPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblMetodoPagamento.setBounds(175, 90, 140, 30);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroPagamento.add(lblMetodoPagamento);

        JButton btnGerarValorPago = new JButton("<html><div style='text-align:center'>Gerar valor</div></html>");
        btnGerarValorPago.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnGerarValorPago.setBounds(265, 10, 120, 30);
        panelRegistroPagamento.add(btnGerarValorPago);

        JButton btnRegistrarPagamento = new JButton("<html><div style='text-align:center'>Registrar Pagamento</div></html>");
        btnRegistrarPagamento.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnRegistrarPagamento.setBounds(20, 200, 100, 50);
        panel.add(btnRegistrarPagamento);

        JButton btnEditarPagamento = new JButton("<html><div style='text-align:center'>Editar Pagamento</div></html>");
        btnEditarPagamento.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnEditarPagamento.setBounds(165, 200, 100, 50);
        panel.add(btnEditarPagamento);

        JButton btnExcluirPagamento = new JButton("<html><div style='text-align:center'>Excluir Pagamento</div></html>");
        btnExcluirPagamento.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnExcluirPagamento.setBounds(305, 200, 100, 50);
        panel.add(btnExcluirPagamento);

//        frame.setBounds(100, 100, 430, 240);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//
//        JLabel lblPagamentos = new JLabel("Pagamentos");
//        lblPagamentos.setHorizontalAlignment(SwingConstants.CENTER);
//        lblPagamentos.setVerticalAlignment(SwingConstants.CENTER);
//        lblPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
//        lblPagamentos.setBounds(140, 10, 120, 20);
//        frame.getContentPane().add(lblPagamentos);
//
//        JPanel panelRegistroPagamento = new JPanel();
//        panelRegistroPagamento.setLayout(null);
//        panelRegistroPagamento.setBackground(new Color(255, 255, 255));
//        panelRegistroPagamento.setBounds(10, 40, 395, 100);
//        frame.getContentPane().add(panelRegistroPagamento);
//
//        JLabel lblIdPagamento = new JLabel("ID (Pagamento)");
//        lblIdPagamento.setVerticalAlignment(SwingConstants.CENTER);
//        lblIdPagamento.setBounds(115, 10, 120, 20);
//        panelRegistroPagamento.add(lblIdPagamento);
//
//        JTextField txtIdPagamento = new JTextField();
//        txtIdPagamento.setBounds(210, 10, 70, 20);
//        panelRegistroPagamento.add(txtIdPagamento);
//
//        JComboBox<String> comboBoxLocacoes = new JComboBox<>(new LocacaoDAO().listagemLocacoesCadastradas());
//        comboBoxLocacoes.setBounds(75, 40, 95, 20);
//        comboBoxLocacoes.setSelectedIndex(-1);
//        panelRegistroPagamento.add(comboBoxLocacoes);
//
//        JTextField txtValorPago = new JTextField();
//        txtValorPago.setBounds(75, 70, 95, 20);
//        panelRegistroPagamento.add(txtValorPago);
//
//        JTextField txtDataPagamento = new JTextField();
//        txtDataPagamento.setBounds(305, 40, 80, 20);
//        panelRegistroPagamento.add(txtDataPagamento);
//
//        JComboBox<MetodosPagamento> comboBoxMetodoPagamento = new JComboBox<>(MetodosPagamento.values());
//        comboBoxMetodoPagamento.setBounds(305, 70, 80, 20);
//        panelRegistroPagamento.add(comboBoxMetodoPagamento);
//
//        JLabel lblLocacao = new JLabel("Locacão");
//        lblLocacao.setBounds(10, 40, 65, 15);
//        lblLocacao.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroPagamento.add(lblLocacao);
//
//        JLabel lblValorPago = new JLabel("Valor Pago");
//        lblValorPago.setBounds(10, 70, 65, 15);
//        lblValorPago.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroPagamento.add(lblValorPago);
//
//        JLabel lblDataPagamento = new JLabel("Data de pagamento");
//        lblDataPagamento.setBounds(175, 40, 130, 15);
//        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroPagamento.add(lblDataPagamento);
//
//        JLabel lblMetodoPagamento = new JLabel("Método de pagamento");
//        lblMetodoPagamento.setBounds(175, 70, 130, 15);
//        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroPagamento.add(lblMetodoPagamento);
//
//        JButton btnGerarValorPago = new JButton("<html><div style='text-align:center'>Gerar valor</div></html>");
//        btnGerarValorPago.setBounds(305, 10, 80, 20);
//        frame.add(btnGerarValorPago);
//
//        JButton btnRegistrarPagamento = new JButton("<html><div style='text-align:center'>Registrar Pagamento</div></html>");
//        btnRegistrarPagamento.setBounds(10, 150, 90, 40);
//        frame.add(btnRegistrarPagamento);
//
//        JButton btnEditarPagamento = new JButton("<html><div style='text-align:center'>Editar Pagamento</div></html>");
//        btnEditarPagamento.setBounds(165, 150, 90, 40);
//        frame.add(btnEditarPagamento);
//
//        JButton btnExcluirPagamento = new JButton("<html><div style='text-align:center'>Excluir Pagamento</div></html>");
//        btnExcluirPagamento.setBounds(315, 150, 90, 40);
//        frame.add(btnExcluirPagamento);

        btnGerarValorPago.addActionListener(e -> {
            double valor = new PagamentoController().calcularPagamento(comboBoxLocacoes, txtDataPagamento);
            txtValorPago.setText(String.valueOf(valor));
        });

        btnRegistrarPagamento.addActionListener(e -> {
            new PagamentoController().registrarPagamento(comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
            comboBoxLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listagemLocacoesCadastradas()));
        });
        btnEditarPagamento.addActionListener(e -> new PagamentoController().editarPagamento(txtIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento));
        btnExcluirPagamento.addActionListener(e -> new PagamentoController().excluirPagamento(txtIdPagamento));
    }

    public JFrame getFrame() {
        return frame;
    }
}
