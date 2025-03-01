package locadora.view;

import locadora.controller.VeiculoController;
import locadora.model.StatusVeiculo;

import javax.swing.*;
import java.awt.*;

public class VeiculosView {

    private final JFrame frame = new JFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VeiculosView window = new VeiculosView();
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
    public VeiculosView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setBounds(100, 100, 335, 235);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblVeiculos = new JLabel("Veículos");
        lblVeiculos.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblVeiculos.setBounds(120, 10, 60, 15);
        lblVeiculos.setVerticalAlignment(SwingConstants.CENTER);
        lblVeiculos.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblVeiculos);

        JPanel panelVeiculos = new JPanel();
        panelVeiculos.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(panelVeiculos);
        panelVeiculos.setBounds(10, 35, 300, 100);
        panelVeiculos.setLayout(null);

        JRadioButton rdbtnCaminhao = new JRadioButton("Caminhão");
        rdbtnCaminhao.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCaminhao.setBounds(10, 10, 90, 20);
        panelVeiculos.add(rdbtnCaminhao);

        JRadioButton rdbtnCarro = new JRadioButton("Carro");
        rdbtnCarro.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCarro.setBounds(105, 10, 90, 20);
        panelVeiculos.add(rdbtnCarro);

        JRadioButton rdbtnMoto = new JRadioButton("Moto");
        rdbtnMoto.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnMoto.setBounds(200, 10, 90, 20);
        panelVeiculos.add(rdbtnMoto);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnCaminhao);
        group.add(rdbtnCarro);
        group.add(rdbtnMoto);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setColumns(10);
        txtPlaca.setBounds(65, 40, 80, 20);
        panelVeiculos.add(txtPlaca);

        JTextField txtModelo = new JTextField();
        txtModelo.setColumns(10);
        txtModelo.setBounds(65, 70, 80, 20);
        panelVeiculos.add(txtModelo);

        JTextField txtAno = new JTextField();
        txtAno.setColumns(10);
        txtAno.setBounds(210, 40, 80, 20);
        panelVeiculos.add(txtAno);

        JComboBox<StatusVeiculo> comboBoxStatus = new JComboBox<>(StatusVeiculo.values());
        comboBoxStatus.setSelectedIndex(-1);
        comboBoxStatus.setBounds(210, 70, 80, 20);
        panelVeiculos.add(comboBoxStatus);

        JLabel lblPlaca = new JLabel("Placa");
        lblPlaca.setBounds(10, 40, 45, 15);
        lblPlaca.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblPlaca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(10, 70, 45, 15);
        lblModelo.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblModelo);

        JLabel lblAno = new JLabel("Ano");
        lblAno.setBounds(155, 40, 45, 15);
        lblAno.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblAno);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(155, 70, 45, 15);
        lblStatus.setVerticalAlignment(SwingConstants.CENTER);
        panelVeiculos.add(lblStatus);

        JButton btnCadastrarVeiculo = new JButton("<html><div style='text-align:center'>Cadastrar Veículo</div></html>");
        btnCadastrarVeiculo.setBounds(10, 145, 90, 40);
        frame.add(btnCadastrarVeiculo);

        JButton btnEditarVeiculo = new JButton("<html><div style='text-align:center'>Editar Veículo</div></html>");
        btnEditarVeiculo.setBounds(115, 145, 90, 40);
        frame.add(btnEditarVeiculo);

        JButton btnExcluirVeiculo = new JButton("<html><div style='text-align:center'>Excluir Veículo</div></html>");
        btnExcluirVeiculo.setBounds(220, 145, 90, 40);
        frame.add(btnExcluirVeiculo);

        btnCadastrarVeiculo.addActionListener(e -> new VeiculoController().cadastrarVeiculo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto, txtPlaca, txtModelo, txtAno, comboBoxStatus));
        btnEditarVeiculo.addActionListener(e -> new VeiculoController().editarVeiculo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto, txtPlaca, txtModelo, txtAno, comboBoxStatus));
        btnExcluirVeiculo.addActionListener(e -> new VeiculoController().excluirVeiculo(txtPlaca));
    }

}
