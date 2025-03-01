package locadora.view;

import locadora.controller.VeiculoController;
import locadora.model.StatusVeiculo;

import javax.swing.*;
import java.awt.*;

public class VeiculosView {

    private final JFrame frame = new JFrame();

    public VeiculosView() {
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

        JLabel lblVeiculos = new JLabel("Veículos");
        lblVeiculos.setFont(new Font("Open Sans", Font.BOLD, 24));
        lblVeiculos.setForeground(Color.WHITE);
        lblVeiculos.setBounds(125, 10, 200, 40);
        lblVeiculos.setVerticalAlignment(SwingConstants.CENTER);
        lblVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblVeiculos);

        JPanel panelVeiculos = new JPanel();
        panelVeiculos.setBackground(new Color(255, 255, 255));
        panelVeiculos.setBounds(20, 50, 390, 140);
        panelVeiculos.setLayout(null);
        panel.add(panelVeiculos);

        JRadioButton rdbtnCaminhao = new JRadioButton("Caminhão");
        rdbtnCaminhao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        rdbtnCaminhao.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCaminhao.setBounds(10, 10, 120, 30);
        panelVeiculos.add(rdbtnCaminhao);

        JRadioButton rdbtnCarro = new JRadioButton("Carro");
        rdbtnCarro.setFont(new Font("Open Sans", Font.PLAIN, 14));
        rdbtnCarro.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCarro.setBounds(135, 10, 120, 30);
        panelVeiculos.add(rdbtnCarro);

        JRadioButton rdbtnMoto = new JRadioButton("Moto");
        rdbtnMoto.setFont(new Font("Open Sans", Font.PLAIN, 14));
        rdbtnMoto.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnMoto.setBounds(260, 10, 120, 30);
        panelVeiculos.add(rdbtnMoto);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnCaminhao);
        group.add(rdbtnCarro);
        group.add(rdbtnMoto);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtPlaca.setColumns(10);
        txtPlaca.setBounds(70, 50, 120, 30);
        panelVeiculos.add(txtPlaca);

        JTextField txtModelo = new JTextField();
        txtModelo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtModelo.setColumns(10);
        txtModelo.setBounds(70, 95, 120, 30);
        panelVeiculos.add(txtModelo);

        JTextField txtAno = new JTextField();
        txtAno.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtAno.setColumns(10);
        txtAno.setBounds(260, 50, 120, 30);
        panelVeiculos.add(txtAno);

        JComboBox<StatusVeiculo> comboBoxStatus = new JComboBox<>(StatusVeiculo.values());
//        JComboBox<String> comboBoxStatus = new JComboBox<>(new String[]{"Disponível"}) ;
        comboBoxStatus.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxStatus.setSelectedIndex(-1);
        comboBoxStatus.setBounds(260, 95, 120, 30);
        panelVeiculos.add(comboBoxStatus);

        JLabel lblPlaca = new JLabel("Placa");
        lblPlaca.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblPlaca.setBounds(10, 50, 50, 30);
        lblPlaca.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblPlaca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblModelo.setBounds(10, 95, 50, 30);
        lblModelo.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblModelo);

        JLabel lblAno = new JLabel("Ano");
        lblAno.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblAno.setBounds(205, 50, 45, 30);
        lblAno.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblAno);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblStatus.setBounds(205, 95, 45, 30);
        lblStatus.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblStatus);

        JButton btnCadastrarVeiculo = new JButton("<html><div style='text-align:center'>Cadastrar Veículo</div></html>");
        btnCadastrarVeiculo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnCadastrarVeiculo.setBounds(20, 200, 90, 50);
        panel.add(btnCadastrarVeiculo);

        JButton btnEditarVeiculo = new JButton("<html><div style='text-align:center'>Editar Veículo</div></html>");
        btnEditarVeiculo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnEditarVeiculo.setBounds(170, 200, 90, 50);
        panel.add(btnEditarVeiculo);

        JButton btnExcluirVeiculo = new JButton("<html><div style='text-align:center'>Excluir Veículo</div></html>");
        btnExcluirVeiculo.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnExcluirVeiculo.setBounds(320, 200, 90, 50);
        panel.add(btnExcluirVeiculo);

//        frame.setBounds(100, 100, 335, 235);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//        frame.getContentPane().setBackground(new Color(46, 107, 240));
//
//        JLabel lblVeiculos = new JLabel("Veículos");
//        lblVeiculos.setFont(new Font("Tahoma", Font.BOLD, 14));
//        lblVeiculos.setForeground(Color.WHITE);
//        lblVeiculos.setBounds(120, 10, 60, 15);
//        lblVeiculos.setVerticalAlignment(SwingConstants.CENTER);
//        lblVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
//        frame.getContentPane().add(lblVeiculos);
//
//        JPanel panelVeiculos = new JPanel();
//        panelVeiculos.setBackground(new Color(255, 255, 255));
//        frame.getContentPane().add(panelVeiculos);
//        panelVeiculos.setBounds(10, 35, 300, 100);
//        panelVeiculos.setLayout(null);
//
//        JRadioButton rdbtnCaminhao = new JRadioButton("Caminhão");
//        rdbtnCaminhao.setHorizontalAlignment(SwingConstants.CENTER);
//        rdbtnCaminhao.setBounds(10, 10, 90, 20);
//        panelVeiculos.add(rdbtnCaminhao);
//
//        JRadioButton rdbtnCarro = new JRadioButton("Carro");
//        rdbtnCarro.setHorizontalAlignment(SwingConstants.CENTER);
//        rdbtnCarro.setBounds(105, 10, 90, 20);
//        panelVeiculos.add(rdbtnCarro);
//
//        JRadioButton rdbtnMoto = new JRadioButton("Moto");
//        rdbtnMoto.setHorizontalAlignment(SwingConstants.CENTER);
//        rdbtnMoto.setBounds(200, 10, 90, 20);
//        panelVeiculos.add(rdbtnMoto);
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(rdbtnCaminhao);
//        group.add(rdbtnCarro);
//        group.add(rdbtnMoto);
//
//        JTextField txtPlaca = new JTextField();
//        txtPlaca.setColumns(10);
//        txtPlaca.setBounds(65, 40, 80, 20);
//        panelVeiculos.add(txtPlaca);
//
//        JTextField txtModelo = new JTextField();
//        txtModelo.setColumns(10);
//        txtModelo.setBounds(65, 70, 80, 20);
//        panelVeiculos.add(txtModelo);
//
//        JTextField txtAno = new JTextField();
//        txtAno.setColumns(10);
//        txtAno.setBounds(210, 40, 80, 20);
//        panelVeiculos.add(txtAno);
//
//        JComboBox<StatusVeiculo> comboBoxStatus = new JComboBox<>(StatusVeiculo.values());
//        comboBoxStatus.setSelectedIndex(-1);
//        comboBoxStatus.setBounds(210, 70, 80, 20);
//        panelVeiculos.add(comboBoxStatus);
//
//        JLabel lblPlaca = new JLabel("Placa");
//        lblPlaca.setBounds(10, 40, 45, 15);
//        lblPlaca.setVerticalAlignment(SwingConstants.CENTER);
//        panelVeiculos.add(lblPlaca);
//
//        JLabel lblModelo = new JLabel("Modelo");
//        lblModelo.setBounds(10, 70, 45, 15);
//        lblModelo.setVerticalAlignment(SwingConstants.CENTER);
//        panelVeiculos.add(lblModelo);
//
//        JLabel lblAno = new JLabel("Ano");
//        lblAno.setBounds(155, 40, 45, 15);
//        lblAno.setVerticalAlignment(SwingConstants.CENTER);
//        panelVeiculos.add(lblAno);
//
//        JLabel lblStatus = new JLabel("Status");
//        lblStatus.setBounds(155, 70, 45, 15);
//        lblStatus.setVerticalAlignment(SwingConstants.CENTER);
//        panelVeiculos.add(lblStatus);
//
//        JButton btnCadastrarVeiculo = new JButton("<html><div style='text-align:center'>Cadastrar Veículo</div></html>");
//        btnCadastrarVeiculo.setBounds(10, 145, 90, 40);
//        frame.add(btnCadastrarVeiculo);
//
//        JButton btnEditarVeiculo = new JButton("<html><div style='text-align:center'>Editar Veículo</div></html>");
//        btnEditarVeiculo.setBounds(115, 145, 90, 40);
//        frame.add(btnEditarVeiculo);
//
//        JButton btnExcluirVeiculo = new JButton("<html><div style='text-align:center'>Excluir Veículo</div></html>");
//        btnExcluirVeiculo.setBounds(220, 145, 90, 40);
//        frame.add(btnExcluirVeiculo);
//
        btnCadastrarVeiculo.addActionListener(e -> new VeiculoController().cadastrarVeiculo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto, txtPlaca, txtModelo, txtAno, comboBoxStatus));
        btnEditarVeiculo.addActionListener(e -> new VeiculoController().editarVeiculo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto, txtPlaca, txtModelo, txtAno, comboBoxStatus));
        btnExcluirVeiculo.addActionListener(e -> new VeiculoController().excluirVeiculo(txtPlaca));
    }

    public JFrame getFrame() {
        return frame;
    }
}
