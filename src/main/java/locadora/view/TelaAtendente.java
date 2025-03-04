package locadora.view;

import locadora.controller.ClienteController;
import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.MetodosPagamento;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;

public class TelaAtendente {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaAtendente window = new TelaAtendente();
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
    public TelaAtendente() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panelRegistroLocacao = new JPanel();
        panelRegistroLocacao.setLayout(null);
        panelRegistroLocacao.setBackground(new Color(255, 255, 255));
        panelRegistroLocacao.setBounds(10, 50, 410, 95);
        frame.getContentPane().add(panelRegistroLocacao);

        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listaClientesCadastrados());
        comboBoxClientes.setBounds(75, 11, 90, 20);
        comboBoxClientes.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxClientes);

        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listaVeiculosDisponiveis());
        comboBoxVeiculos.setBounds(75, 36, 90, 20);
        comboBoxVeiculos.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxVeiculos);

        JTextField txtDataRetirada = new JTextField();
        txtDataRetirada.setColumns(10);
        txtDataRetirada.setBounds(300, 11, 100, 20);
        panelRegistroLocacao.add(txtDataRetirada);

        JTextField txtDataDevolucao = new JTextField();
        txtDataDevolucao.setColumns(10);
        txtDataDevolucao.setBounds(300, 36, 100, 20);
        panelRegistroLocacao.add(txtDataDevolucao);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setToolTipText("");
        lblCliente.setBounds(10, 13, 100, 14);
        panelRegistroLocacao.add(lblCliente);

        JLabel lblVeiculo = new JLabel("Veículo");
        lblVeiculo.setBounds(10, 37, 100, 14);
        panelRegistroLocacao.add(lblVeiculo);

        JLabel lblDataRetirada = new JLabel("Data de retirada");
        lblDataRetirada.setBounds(190, 13, 120, 14);
        panelRegistroLocacao.add(lblDataRetirada);

        JLabel lblDataDevolucao = new JLabel("Data de devolução");
        lblDataDevolucao.setBounds(190, 37, 120, 14);
        panelRegistroLocacao.add(lblDataDevolucao);

        JButton btnRegistrarLocacao = new JButton("Registrar Locação");
        btnRegistrarLocacao.setBounds(140, 62, 140, 23);
        panelRegistroLocacao.add(btnRegistrarLocacao);

        JPanel panelRegistroPagamento = new JPanel();
        panelRegistroPagamento.setLayout(null);
        panelRegistroPagamento.setBackground(Color.WHITE);
        panelRegistroPagamento.setBounds(10, 205, 410, 95);
        frame.getContentPane().add(panelRegistroPagamento);

        JComboBox<Integer> comboBoxIdLocacoes = new JComboBox<>(new LocacaoDAO().listaLocacoesCadastradas());
        comboBoxIdLocacoes.setSelectedIndex(-1);
        comboBoxIdLocacoes.setBounds(75, 11, 90, 20);
        panelRegistroPagamento.add(comboBoxIdLocacoes);

        JTextField txtValorPago = new JTextField();
        txtValorPago.setColumns(10);
        txtValorPago.setBounds(75, 36, 90, 20);
        panelRegistroPagamento.add(txtValorPago);

        JTextField txtDataPagamento = new JTextField();
        txtDataPagamento.setColumns(10);
        txtDataPagamento.setBounds(300, 11, 100, 20);
        panelRegistroPagamento.add(txtDataPagamento);

        JComboBox<MetodosPagamento> comboBoxMetodoPagamento = new JComboBox<>(MetodosPagamento.values());
        comboBoxMetodoPagamento.setSelectedIndex(-1);
        comboBoxMetodoPagamento.setBounds(300, 36, 100, 20);
        panelRegistroPagamento.add(comboBoxMetodoPagamento);

        JLabel lblIdLocacao = new JLabel("Locação");
        lblIdLocacao.setBounds(10, 13, 100, 14);
        panelRegistroPagamento.add(lblIdLocacao);

        JLabel lblValorPago = new JLabel("Valor pago");
        lblValorPago.setBounds(10, 37, 100, 14);
        panelRegistroPagamento.add(lblValorPago);

        JLabel lblDataPagamento = new JLabel("Data de pagamento");
        lblDataPagamento.setBounds(170, 13, 130, 14);
        panelRegistroPagamento.add(lblDataPagamento);

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento");
        lblMetodoPagamento.setBounds(170, 37, 130, 14);
        panelRegistroPagamento.add(lblMetodoPagamento);

        JButton btnRegistrarPagamento = new JButton("Registrar Pagamento");
        btnRegistrarPagamento.setBounds(130, 62, 160, 23);
        panelRegistroPagamento.add(btnRegistrarPagamento);

        JLabel lblRegistroLocacao = new JLabel("Registro de Locações");
        lblRegistroLocacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistroLocacao.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRegistroLocacao.setBounds(130, 11, 180, 24);
        frame.getContentPane().add(lblRegistroLocacao);

        JLabel lblRegistroPagamento = new JLabel("Registro de Pagamentos");
        lblRegistroPagamento.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistroPagamento.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRegistroPagamento.setBounds(130, 168, 180, 24);
        frame.getContentPane().add(lblRegistroPagamento);

        btnRegistrarLocacao.addActionListener(e -> new LocacaoController().registrarLocacao(comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao));
//        btnRegistrarPagamento.addActionListener(e -> new PagamentoController().registrarPagamento(comboBoxIdLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento));

    }
}
