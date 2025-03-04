package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.TiposObjetos;

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

        JComboBox<TiposObjetos> comboBoxTiposObjetos = new JComboBox<>(TiposObjetos.values());
        comboBoxTiposObjetos.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxTiposObjetos.setBounds(62, 10, 120, 30);
        comboBoxTiposObjetos.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxTiposObjetos);

        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listaClientesCadastrados());
        comboBoxClientes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxClientes.setBounds(62, 50, 120, 30);
        comboBoxClientes.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxClientes);

        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listaVeiculosDisponiveis());
        comboBoxVeiculos.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxVeiculos.setBounds(62, 95, 120, 30);
        comboBoxVeiculos.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxVeiculos);

        JComboBox<Integer> comboBoxIdLocacoes = new JComboBox<>(new LocacaoDAO().listaLocacoesCadastradas());
        comboBoxIdLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxIdLocacoes.setBounds(315, 10, 70, 30);
        comboBoxIdLocacoes.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxIdLocacoes);

        JTextField txtDataRetirada = new JTextField();
        txtDataRetirada.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtDataRetirada.setBounds(315, 50, 70, 30);
        panelRegistroLocacao.add(txtDataRetirada);

        JTextField txtDataDevolucao = new JTextField();
        txtDataDevolucao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtDataDevolucao.setBounds(315, 95, 70, 30);
        panelRegistroLocacao.add(txtDataDevolucao);

        JLabel lblListas = new JLabel("Listas");
        lblListas.setHorizontalAlignment(SwingConstants.CENTER);
        lblListas.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblListas.setVerticalAlignment(SwingConstants.CENTER);
        lblListas.setBounds(10, 10, 60, 30);
        panelRegistroLocacao.add(lblListas);

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

        JLabel lblIdLocacao = new JLabel("ID (Locacão)");
        lblIdLocacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdLocacao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblIdLocacao.setVerticalAlignment(SwingConstants.CENTER);
        lblIdLocacao.setBounds(188, 10, 120, 30);
        panelRegistroLocacao.add(lblIdLocacao);

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

//        comboBoxTiposObjetos.addActionListener(e -> {
//
//            JTable tableObjetos = new JTable()
//        });


        comboBoxClientes.addActionListener(e -> {
            JTable tableClientes = new JTable(new ClienteDAO().dadosClientesCadastrados(), new ClienteDAO().atributosClientesCadastrados());
            JScrollPane scrollpaneClientes = new JScrollPane(tableClientes);
            scrollpaneClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            JOptionPane optionPane = new JOptionPane(scrollpaneClientes, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Clientes");
            dialog.setSize(new Dimension(600, 300));
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        comboBoxVeiculos.addActionListener(e -> {
            JTable tableVeiculos = new JTable(new VeiculoDAO().dadosVeiculosCadastrados(), new VeiculoDAO().atributosVeiculosCadastrados());
            JScrollPane scrollpaneVeiculos = new JScrollPane(tableVeiculos);
            scrollpaneVeiculos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            JOptionPane optionPane = new JOptionPane(scrollpaneVeiculos, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Veículos");
            dialog.setSize(new Dimension(600, 300));
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        comboBoxIdLocacoes.addActionListener(e -> {
            JTable tableLocacoes = new JTable(new LocacaoDAO().dadosLocacoesCadastradas(), new LocacaoDAO().atributosLocacoesCadastradas());
            JScrollPane scrollpaneLocacoes = new JScrollPane(tableLocacoes);
            scrollpaneLocacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollpaneLocacoes.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JOptionPane optionPane = new JOptionPane(scrollpaneLocacoes, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Locações");
            dialog.setSize(new Dimension(600, 300));
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        btnRegistrarLocacao.addActionListener(e -> {
            new LocacaoController().registrarLocacao(comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao);
            comboBoxIdLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesCadastradas()));
            comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listaVeiculosDisponiveis()));
        });
        btnEditarLocacao.addActionListener(e -> new LocacaoController().editarLocacao(comboBoxIdLocacoes, comboBoxClientes, comboBoxVeiculos, txtDataRetirada, txtDataDevolucao));
        btnExcluirLocacao.addActionListener(e -> {
            new LocacaoController().excluirLocacao(comboBoxIdLocacoes);
            comboBoxIdLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesCadastradas()));
            comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listaVeiculosDisponiveis()));
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
