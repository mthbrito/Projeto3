package locadora.view;

import locadora.controller.ClienteController;
import locadora.controller.VeiculoController;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class TelaGerente {

    private final JFrame frame = new JFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaGerente window = new TelaGerente();
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
    public TelaGerente() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panelCadastroVeiculos = new JPanel();
        panelCadastroVeiculos.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(panelCadastroVeiculos);
        panelCadastroVeiculos.setBounds(10, 40, 280, 117);
        panelCadastroVeiculos.setLayout(null);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setColumns(10);
        txtPlaca.setBounds(60, 35, 70, 20);
        panelCadastroVeiculos.add(txtPlaca);

        JTextField txtModelo = new JTextField();
        txtModelo.setColumns(10);
        txtModelo.setBounds(60, 60, 70, 20);
        panelCadastroVeiculos.add(txtModelo);

        JTextField txtAno = new JTextField();
        txtAno.setColumns(10);
        txtAno.setBounds(200, 35, 70, 20);
        panelCadastroVeiculos.add(txtAno);

        JTextField txtStatus = new JTextField();
        txtStatus.setColumns(10);
        txtStatus.setBounds(200, 60, 70, 20);
        panelCadastroVeiculos.add(txtStatus);

        JLabel lblPlaca = new JLabel("Placa");
        lblPlaca.setBounds(10, 37, 45, 14);
        panelCadastroVeiculos.add(lblPlaca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(10, 61, 45, 14);
        panelCadastroVeiculos.add(lblModelo);

        JLabel lblAno = new JLabel("Ano");
        lblAno.setBounds(150, 37, 45, 14);
        panelCadastroVeiculos.add(lblAno);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(150, 61, 45, 14);
        panelCadastroVeiculos.add(lblStatus);

        JButton btnCadastrarVeiculo = new JButton("Cadastrar Veículo");
        btnCadastrarVeiculo.setBounds(75, 86, 140, 23);
        panelCadastroVeiculos.add(btnCadastrarVeiculo);

        JRadioButton rdbtnCaminhao = new JRadioButton("Caminhão");
        rdbtnCaminhao.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCaminhao.setBounds(10, 7, 85, 23);
        panelCadastroVeiculos.add(rdbtnCaminhao);

        JRadioButton rdbtnCarro = new JRadioButton("Carro");
        rdbtnCarro.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCarro.setBounds(97, 7, 85, 23);
        panelCadastroVeiculos.add(rdbtnCarro);

        JRadioButton rdbtnMoto = new JRadioButton("Moto");
        rdbtnMoto.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnMoto.setBounds(184, 7, 85, 23);
        panelCadastroVeiculos.add(rdbtnMoto);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnCaminhao);
        group.add(rdbtnCarro);
        group.add(rdbtnMoto);

        JPanel panelCadastroCliente = new JPanel();
        panelCadastroCliente.setLayout(null);
        panelCadastroCliente.setBackground(Color.WHITE);
        panelCadastroCliente.setBounds(10, 205, 280, 95);
        frame.getContentPane().add(panelCadastroCliente);

        JTextField txtNome = new JTextField();
        txtNome.setColumns(10);
        txtNome.setBounds(60, 11, 70, 20);
        panelCadastroCliente.add(txtNome);

        JTextField txtCpf = new JTextField();
        txtCpf.setColumns(10);
        txtCpf.setBounds(60, 36, 70, 20);
        panelCadastroCliente.add(txtCpf);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setColumns(10);
        txtTelefone.setBounds(200, 11, 70, 20);
        panelCadastroCliente.add(txtTelefone);

        JTextField txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(200, 36, 70, 20);
        panelCadastroCliente.add(txtEmail);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 13, 45, 14);
        panelCadastroCliente.add(lblNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(10, 37, 45, 14);
        panelCadastroCliente.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(145, 13, 50, 14);
        panelCadastroCliente.add(lblTelefone);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(145, 37, 50, 14);
        panelCadastroCliente.add(lblEmail);

        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnCadastrarCliente.setBounds(75, 62, 140, 23);
        panelCadastroCliente.add(btnCadastrarCliente);

        JLabel lblCadastroVeiculos = new JLabel("Cadastro de Veículos");
        lblCadastroVeiculos.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCadastroVeiculos.setBounds(85, 14, 150, 14);
        frame.getContentPane().add(lblCadastroVeiculos);

        JLabel lblCadastroClientes = new JLabel("Cadastro de clientes");
        lblCadastroClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCadastroClientes.setBounds(85, 180, 150, 14);
        frame.getContentPane().add(lblCadastroClientes);

        JButton btnGerarRelatorio = new JButton("Gerar Relatório");
        btnGerarRelatorio.setBounds(304, 40, 120, 23);
        frame.getContentPane().add(btnGerarRelatorio);

        btnCadastrarCliente.addActionListener(e -> new ClienteController().CadastrarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnCadastrarVeiculo.addActionListener(e -> new VeiculoController().CadastrarVeiculo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto, txtPlaca, txtModelo, txtAno, txtStatus));
    }

}

