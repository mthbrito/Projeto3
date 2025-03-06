package locadora.view;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.TiposObjetos;

import javax.swing.*;
import java.awt.*;

import static locadora.controller.LocacaoController.resetarEntradas;

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

        JComboBox<String> comboBoxAcao = new JComboBox<>(new String[]{"REGISTRAR","EDITAR","EXCLUIR"});
        comboBoxAcao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxAcao.setBounds(62, 10, 120, 30);
        comboBoxAcao.setSelectedIndex(-1);
        panelRegistroLocacao.add(comboBoxAcao);

        JComboBox<String> comboBoxClientes = new JComboBox<>(new ClienteDAO().listaClientesCadastrados());
        comboBoxClientes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxClientes.setBounds(62, 50, 120, 30);
        comboBoxClientes.setSelectedIndex(-1);
        comboBoxClientes.setVisible(false);
        panelRegistroLocacao.add(comboBoxClientes);

        JComboBox<String> comboBoxVeiculos = new JComboBox<>(new VeiculoDAO().listaVeiculosDisponiveis());
        comboBoxVeiculos.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxVeiculos.setBounds(62, 95, 120, 30);
        comboBoxVeiculos.setSelectedIndex(-1);
        comboBoxVeiculos.setVisible(false);
        panelRegistroLocacao.add(comboBoxVeiculos);

        JComboBox<Integer> comboBoxIdLocacoes = new JComboBox<>(new LocacaoDAO().listaLocacoesNaoPagas());
        comboBoxIdLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxIdLocacoes.setBounds(305, 10, 80, 30);
        comboBoxIdLocacoes.setSelectedIndex(-1);
        comboBoxIdLocacoes.setVisible(false);
        panelRegistroLocacao.add(comboBoxIdLocacoes);

        JTextField txtDataRetirada = new JTextField();
        txtDataRetirada.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtDataRetirada.setBounds(305, 50, 80, 30);
        txtDataRetirada.setVisible(false);
        panelRegistroLocacao.add(txtDataRetirada);

        JTextField txtDataDevolucao = new JTextField();
        txtDataDevolucao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtDataDevolucao.setBounds(305, 95, 80, 30);
        txtDataDevolucao.setVisible(false);
        panelRegistroLocacao.add(txtDataDevolucao);

        JLabel lblAcao = new JLabel("Ação");
        lblAcao.setHorizontalAlignment(SwingConstants.CENTER);
        lblAcao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblAcao.setVerticalAlignment(SwingConstants.CENTER);
        lblAcao.setBounds(10, 10, 60, 30);
        panelRegistroLocacao.add(lblAcao);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblCliente.setBounds(10, 50, 60, 30);
        lblCliente.setVerticalAlignment(SwingConstants.CENTER);
        lblCliente.setVisible(false);
        panelRegistroLocacao.add(lblCliente);

        JLabel lblVeiculo = new JLabel("Veículo");
        lblVeiculo.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblVeiculo.setBounds(10, 95, 60, 30);
        lblVeiculo.setVerticalAlignment(SwingConstants.CENTER);
        lblVeiculo.setVisible(false);
        panelRegistroLocacao.add(lblVeiculo);

        JLabel lblIdLocacao = new JLabel("ID (Locacão)");
        lblIdLocacao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblIdLocacao.setVerticalAlignment(SwingConstants.CENTER);
        lblIdLocacao.setBounds(188, 10, 120, 30);
        lblIdLocacao.setVisible(false);
        panelRegistroLocacao.add(lblIdLocacao);

        JLabel lblDataRetirada = new JLabel("Data de retirada");
        lblDataRetirada.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblDataRetirada.setBounds(188, 50, 120, 30);
        lblDataRetirada.setVerticalAlignment(SwingConstants.CENTER);
        lblDataRetirada.setVisible(false);
        panelRegistroLocacao.add(lblDataRetirada);

        JLabel lblDataDevolucao = new JLabel("Data de devolução");
        lblDataDevolucao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblDataDevolucao.setBounds(188, 95, 130, 30);
        lblDataDevolucao.setVerticalAlignment(SwingConstants.CENTER);
        lblDataDevolucao.setVisible(false);
        panelRegistroLocacao.add(lblDataDevolucao);

        JButton btnConfirmar = new JButton("<html><div style='text-align:center'>Confirmar</div></html>");
        btnConfirmar.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnConfirmar.setBounds(165, 200, 90, 50);
        btnConfirmar.setVisible(false);
        panel.add(btnConfirmar);

        JLabel lblListas = new JLabel("Listas");
        lblListas.setFont(new Font("Open Sans", Font.BOLD, 14));
        lblListas.setBounds(320, 200, 80, 20);
        lblListas.setForeground(Color.WHITE);
        lblListas.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblListas);

        JComboBox<TiposObjetos> comboBoxListas = new JComboBox<>(TiposObjetos.values());
        comboBoxListas.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxListas.setBounds(320, 220, 80, 30);
        comboBoxListas.setSelectedIndex(-1);
        panel.add(comboBoxListas);

        comboBoxListas.addActionListener(e -> {
            TiposObjetos lista = (TiposObjetos) comboBoxListas.getSelectedItem();
            if(lista != null) {
                JScrollPane scrollpane;
                JOptionPane optionPane;
                JDialog dialog;
                switch (lista) {
                    case CLIENTE:
                        JTable tableClientes = new JTable(new ClienteDAO().dadosClientesCadastrados(), new ClienteDAO().atributosClientesCadastrados());
                        scrollpane = new JScrollPane(tableClientes);
                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        optionPane = new JOptionPane(scrollpane, JOptionPane.INFORMATION_MESSAGE);
                        dialog = optionPane.createDialog("Clientes");
                        dialog.setSize(new Dimension(600, 300));
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        break;
                    case VEICULO:
                        JTable tableVeiculos = new JTable(new VeiculoDAO().dadosVeiculosCadastrados(), new VeiculoDAO().atributosVeiculosCadastrados());
                        scrollpane = new JScrollPane(tableVeiculos);
                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        optionPane = new JOptionPane(scrollpane, JOptionPane.INFORMATION_MESSAGE);
                        dialog = optionPane.createDialog("Veículos");
                        dialog.setSize(new Dimension(600, 300));
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        break;
                    case LOCACAO:
                        JTable tableLocacoes = new JTable(new LocacaoDAO().dadosLocacoesCadastradas(), new LocacaoDAO().atributosLocacoesCadastradas());
                        scrollpane = new JScrollPane(tableLocacoes);
                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        optionPane = new JOptionPane(scrollpane, JOptionPane.INFORMATION_MESSAGE);
                        dialog = optionPane.createDialog("Locações");
                        dialog.setSize(new Dimension(600, 300));
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        break;
                    case PAGAMENTO:
                        JTable tablePagamentos = new JTable(new PagamentoDAO().dadosPagamentosCadastrados(), new PagamentoDAO().atributosPagamentosCadastrados());
                        scrollpane = new JScrollPane(tablePagamentos);
                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        optionPane = new JOptionPane(scrollpane, JOptionPane.INFORMATION_MESSAGE);
                        dialog = optionPane.createDialog("Pagamentos");
                        dialog.setSize(new Dimension(600, 300));
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        });

        comboBoxAcao.addItemListener(e-> {
            String acao = String.valueOf(comboBoxAcao.getSelectedItem());
            switch (acao) {
                case "REGISTRAR":
                    lblIdLocacao.setVisible(false);
                    comboBoxIdLocacoes.setVisible(false);
                    lblCliente.setVisible(true);
                    comboBoxClientes.setVisible(true);
                    lblVeiculo.setVisible(true);
                    comboBoxVeiculos.setVisible(true);
                    lblDataDevolucao.setVisible(true);
                    txtDataDevolucao.setVisible(true);
                    lblDataRetirada.setVisible(true);
                    txtDataRetirada.setVisible(true);
                    btnConfirmar.setVisible(true);
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                case "EDITAR":
                    lblIdLocacao.setVisible(true);
                    comboBoxIdLocacoes.setVisible(true);
                    lblCliente.setVisible(true);
                    comboBoxClientes.setVisible(true);
                    lblVeiculo.setVisible(true);
                    comboBoxVeiculos.setVisible(true);
                    lblDataDevolucao.setVisible(true);
                    txtDataDevolucao.setVisible(true);
                    lblDataRetirada.setVisible(true);
                    txtDataRetirada.setVisible(true);
                    btnConfirmar.setVisible(true);
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                case "EXCLUIR":
                    lblIdLocacao.setVisible(true);
                    comboBoxIdLocacoes.setVisible(true);
                    lblCliente.setVisible(false);
                    comboBoxClientes.setVisible(false);
                    lblVeiculo.setVisible(false);
                    comboBoxVeiculos.setVisible(false);
                    lblDataDevolucao.setVisible(false);
                    txtDataDevolucao.setVisible(false);
                    lblDataRetirada.setVisible(false);;
                    txtDataRetirada.setVisible(false);
                    btnConfirmar.setVisible(true);
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                default:
                    break;

            }
        });

//        comboBoxClientes.addActionListener(e -> {
//            JTable tableClientes = new JTable(new ClienteDAO().dadosClientesCadastrados(), new ClienteDAO().atributosClientesCadastrados());
//            JScrollPane scrollpaneClientes = new JScrollPane(tableClientes);
//            scrollpaneClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            JOptionPane optionPane = new JOptionPane(scrollpaneClientes, JOptionPane.INFORMATION_MESSAGE);
//            JDialog dialog = optionPane.createDialog("Clientes");
//            dialog.setSize(new Dimension(600, 300));
//            dialog.setLocationRelativeTo(null);
//            dialog.setVisible(true);
//        });

//        comboBoxVeiculos.addActionListener(e -> {
//            JTable tableVeiculos = new JTable(new VeiculoDAO().dadosVeiculosCadastrados(), new VeiculoDAO().atributosVeiculosCadastrados());
//            JScrollPane scrollpaneVeiculos = new JScrollPane(tableVeiculos);
//            scrollpaneVeiculos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            JOptionPane optionPane = new JOptionPane(scrollpaneVeiculos, JOptionPane.INFORMATION_MESSAGE);
//            JDialog dialog = optionPane.createDialog("Veículos");
//            dialog.setSize(new Dimension(600, 300));
//            dialog.setLocationRelativeTo(null);
//            dialog.setVisible(true);
//        });

//        comboBoxIdLocacoes.addActionListener(e -> {
//            JTable tableLocacoes = new JTable(new LocacaoDAO().dadosLocacoesCadastradas(), new LocacaoDAO().atributosLocacoesCadastradas());
//            JScrollPane scrollpaneLocacoes = new JScrollPane(tableLocacoes);
//            scrollpaneLocacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            JOptionPane optionPane = new JOptionPane(scrollpaneLocacoes, JOptionPane.INFORMATION_MESSAGE);
//            JDialog dialog = optionPane.createDialog("Locações");
//            dialog.setSize(new Dimension(600, 300));
//            dialog.setLocationRelativeTo(null);
//            dialog.setVisible(true);
//        });

        btnConfirmar.addActionListener(e -> {
            String acao = String.valueOf(comboBoxAcao.getSelectedItem());
            LocacaoController locacaoController = new LocacaoController();
            switch (acao) {
                case "REGISTRAR":
                    locacaoController.registrarLocacao(comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    comboBoxIdLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesNaoPagas()));
                    comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listaVeiculosDisponiveis()));
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                case "EDITAR":
                    locacaoController.editarLocacao(comboBoxIdLocacoes, comboBoxClientes, comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    comboBoxIdLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesNaoPagas()));
                    comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listaVeiculosDisponiveis()));
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                case "EXCLUIR":
                    locacaoController.excluirLocacao(comboBoxIdLocacoes);
                    comboBoxIdLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesNaoPagas()));
                    comboBoxVeiculos.setModel(new DefaultComboBoxModel<>(new VeiculoDAO().listaVeiculosDisponiveis()));
                    resetarEntradas(comboBoxIdLocacoes,comboBoxClientes,comboBoxVeiculos,txtDataRetirada,txtDataDevolucao);
                    break;
                default:
                    break;
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
