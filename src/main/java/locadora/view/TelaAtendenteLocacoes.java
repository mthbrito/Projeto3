package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.dao.VeiculoDAO;

import javax.swing.*;
import java.awt.*;

public class TelaAtendenteLocacoes {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaAtendenteLocacoes window = new TelaAtendenteLocacoes();
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
    public TelaAtendenteLocacoes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 430, 210);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblLocacoes = new JLabel("Locações");
        lblLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
        lblLocacoes.setVerticalAlignment(SwingConstants.CENTER);
        lblLocacoes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLocacoes.setBounds(165, 10, 70, 20);
        frame.getContentPane().add(lblLocacoes);

        JPanel panelRegistroLocacao = new JPanel();
        panelRegistroLocacao.setLayout(null);
        panelRegistroLocacao.setBackground(new Color(255, 255, 255));
        panelRegistroLocacao.setBounds(10, 40, 395, 70);
        frame.getContentPane().add(panelRegistroLocacao);

        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listagemClientesCadastrados());
        comboBoxClientes.setBounds(65, 10, 125, 20);
        comboBoxClientes.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxClientes);

        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listagemVeiculosDisponiveis());
        comboBoxVeiculos.setBounds(65, 40, 125, 20);
        comboBoxVeiculos.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxVeiculos);

        JTextField txtDataRetirada = new JTextField();
        txtDataRetirada.setBounds(315, 10, 70, 20);
        panelRegistroLocacao.add(txtDataRetirada);

        JTextField txtDataDevolucao = new JTextField();
        txtDataDevolucao.setBounds(315, 40, 70, 20);
        panelRegistroLocacao.add(txtDataDevolucao);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 10, 45, 15);
        lblCliente.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblCliente);

        JLabel lblVeiculo = new JLabel("Veículo");
        lblVeiculo.setBounds(10, 40, 45, 15);
        lblVeiculo.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblVeiculo);

        JLabel lblDataRetirada = new JLabel("Data de retirada");
        lblDataRetirada.setBounds(200, 10, 120, 15);
        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblDataRetirada);

        JLabel lblDataDevolucao = new JLabel("Data de devolução");
        lblDataDevolucao.setBounds(200, 40, 120, 15);
        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblDataDevolucao);

        JButton btnRegistrarLocacao = new JButton("<html><div style='text-align:center'>Registrar Locação</div></html>");
        btnRegistrarLocacao.setBounds(10, 120, 90, 40);
        frame.add(btnRegistrarLocacao);

        JButton btnEditarLocacao = new JButton("<html><div style='text-align:center'>Editar Locação</div></html>");
        btnEditarLocacao.setBounds(165, 120, 90, 40);
        frame.add(btnEditarLocacao);

        JButton btnExcluirLocacao = new JButton("<html><div style='text-align:center'>Excluir Locação</div></html>");
        btnExcluirLocacao.setBounds(315, 120, 90, 40);
        frame.add(btnExcluirLocacao);

        btnRegistrarLocacao.addActionListener(e -> new LocacaoController().registrarLocacao(comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao));
        btnEditarLocacao.addActionListener(e -> new LocacaoController().editarLocacao(comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao));
        btnExcluirLocacao.addActionListener(e -> new LocacaoController().excluirLocacao(comboBoxClientes));

    }
}
