package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.dao.VeiculoDAO;

import javax.swing.*;
import java.awt.*;

public class LocacoesView {

    private final JFrame frame = new JFrame();

    public LocacoesView() {
        initialize();
    }

    private void initialize() {
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(46, 107, 240));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblLocacoes = new JLabel("Locações");
        lblLocacoes.setFont(new Font("Open Sans", Font.BOLD, 24));
        lblLocacoes.setForeground(Color.WHITE);
        lblLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
        lblLocacoes.setVerticalAlignment(SwingConstants.CENTER);
        lblLocacoes.setBounds(165, 7, 120, 40);
        panel.add(lblLocacoes);

        JPanel panelRegistroLocacao = new JPanel();
        panelRegistroLocacao.setLayout(null);
        panelRegistroLocacao.setBackground(new Color(255, 255, 255));
        panelRegistroLocacao.setBounds(20, 50, 390, 140);
        panel.add(panelRegistroLocacao);

        JLabel lblIdLocacao = new JLabel("ID (Locacão)");
        lblIdLocacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblIdLocacao.setVerticalAlignment(SwingConstants.CENTER);
        lblIdLocacao.setBounds(110, 10, 90, 30);
        panelRegistroLocacao.add(lblIdLocacao);

        JTextField txtIdLocacao = new JTextField();
        txtIdLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtIdLocacao.setBounds(210, 10, 90, 30);
        panelRegistroLocacao.add(txtIdLocacao);

        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listagemClientesCadastrados());
        comboBoxClientes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxClientes.setBounds(62, 50, 120, 30);
        comboBoxClientes.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxClientes);

        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listagemVeiculosDisponiveis());
        comboBoxVeiculos.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxVeiculos.setBounds(62, 95, 120, 30);
        comboBoxVeiculos.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxVeiculos);

        JTextField txtDataRetirada = new JTextField();
        txtDataRetirada.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtDataRetirada.setBounds(315, 50, 70, 30);
        panelRegistroLocacao.add(txtDataRetirada);

        JTextField txtDataDevolucao = new JTextField();
        txtDataDevolucao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtDataDevolucao.setBounds(315, 95, 70, 30);
        panelRegistroLocacao.add(txtDataDevolucao);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblCliente.setBounds(10, 50, 60, 30);
        lblCliente.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblCliente);

        JLabel lblVeiculo = new JLabel("Veículo");
        lblVeiculo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblVeiculo.setBounds(10, 95, 60, 30);
        lblVeiculo.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblVeiculo);

        JLabel lblDataRetirada = new JLabel("Data de retirada");
        lblDataRetirada.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblDataRetirada.setBounds(188, 50, 120, 30);
        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblDataRetirada);

        JLabel lblDataDevolucao = new JLabel("Data de devolução");
        lblDataDevolucao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblDataDevolucao.setBounds(188, 95, 130, 30);
        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
        panelRegistroLocacao.add(lblDataDevolucao);

        JButton btnRegistrarLocacao = new JButton("<html><div style='text-align:center'>Registrar Locação</div></html>");
        btnRegistrarLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnRegistrarLocacao.setBounds(20, 200, 90, 50);
        panel.add(btnRegistrarLocacao);

        JButton btnEditarLocacao = new JButton("<html><div style='text-align:center'>Editar Locação</div></html>");
        btnEditarLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnEditarLocacao.setBounds(165, 200, 90, 50);
        panel.add(btnEditarLocacao);

        JButton btnExcluirLocacao = new JButton("<html><div style='text-align:center'>Excluir Locação</div></html>");
        btnExcluirLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnExcluirLocacao.setBounds(320, 200, 90, 50);
        panel.add(btnExcluirLocacao);

//        frame.setBounds(100, 100, 430, 240);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//
//        JLabel lblLocacoes = new JLabel("Locações");
//        lblLocacoes.setHorizontalAlignment(SwingConstants.CENTER);
//        lblLocacoes.setVerticalAlignment(SwingConstants.CENTER);
//        lblLocacoes.setFont(new Font("Tahoma", Font.BOLD, 14));
//        lblLocacoes.setBounds(165, 10, 70, 20);
//        frame.getContentPane().add(lblLocacoes);
//
//        JPanel panelRegistroLocacao = new JPanel();
//        panelRegistroLocacao.setLayout(null);
//        panelRegistroLocacao.setBackground(new Color(255, 255, 255));
//        panelRegistroLocacao.setBounds(10, 40, 395, 100);
//        frame.getContentPane().add(panelRegistroLocacao);
//
//        JLabel lblIdLocacao = new JLabel("ID (Locacão)");
//        lblIdLocacao.setVerticalAlignment(SwingConstants.CENTER);
//        lblIdLocacao.setBounds(120, 10, 80, 20);
//        panelRegistroLocacao.add(lblIdLocacao);
//
//        JTextField txtIdLocacao = new JTextField();
//        txtIdLocacao.setBounds(210, 10, 70, 20);
//        panelRegistroLocacao.add(txtIdLocacao);
//
//        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listagemClientesCadastrados());
//        comboBoxClientes.setBounds(65, 40, 125, 20);
//        comboBoxClientes.setSelectedIndex(-1);
//        panelRegistroLocacao.add(comboBoxClientes);
//
//        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listagemVeiculosDisponiveis());
//        comboBoxVeiculos.setBounds(65, 70, 125, 20);
//        comboBoxVeiculos.setSelectedIndex(-1);
//        panelRegistroLocacao.add(comboBoxVeiculos);
//
//        JTextField txtDataRetirada = new JTextField();
//        txtDataRetirada.setBounds(315, 40, 70, 20);
//        panelRegistroLocacao.add(txtDataRetirada);
//
//        JTextField txtDataDevolucao = new JTextField();
//        txtDataDevolucao.setBounds(315, 70, 70, 20);
//        panelRegistroLocacao.add(txtDataDevolucao);
//
//        JLabel lblCliente = new JLabel("Cliente");
//        lblCliente.setBounds(10, 40, 45, 15);
//        lblCliente.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroLocacao.add(lblCliente);
//
//        JLabel lblVeiculo = new JLabel("Veículo");
//        lblVeiculo.setBounds(10, 70, 45, 15);
//        lblVeiculo.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroLocacao.add(lblVeiculo);
//
//        JLabel lblDataRetirada = new JLabel("Data de retirada");
//        lblDataRetirada.setBounds(200, 40, 120, 15);
//        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroLocacao.add(lblDataRetirada);
//
//        JLabel lblDataDevolucao = new JLabel("Data de devolução");
//        lblDataDevolucao.setBounds(200, 70, 120, 15);
//        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
//        panelRegistroLocacao.add(lblDataDevolucao);
//
//        JButton btnRegistrarLocacao = new JButton("<html><div style='text-align:center'>Registrar Locação</div></html>");
//        btnRegistrarLocacao.setBounds(10, 150, 90, 40);
//        frame.add(btnRegistrarLocacao);
//
//        JButton btnEditarLocacao = new JButton("<html><div style='text-align:center'>Editar Locação</div></html>");
//        btnEditarLocacao.setBounds(165, 150, 90, 40);
//        frame.add(btnEditarLocacao);
//
//        JButton btnExcluirLocacao = new JButton("<html><div style='text-align:center'>Excluir Locação</div></html>");
//        btnExcluirLocacao.setBounds(315, 150, 90, 40);
//        frame.add(btnExcluirLocacao);

        btnRegistrarLocacao.addActionListener(e -> {
            new LocacaoController().registrarLocacao(comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao);
            comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listagemVeiculosDisponiveis()));

        });
        btnEditarLocacao.addActionListener(e -> new LocacaoController().editarLocacao(txtIdLocacao, comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao));
        btnExcluirLocacao.addActionListener(e -> new LocacaoController().excluirLocacao(txtIdLocacao));

    }
    public JFrame getFrame() {
        return frame;
    }
}
