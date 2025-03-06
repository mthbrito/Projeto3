package locadora.view;

import locadora.controller.PagamentoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.MetodosPagamento;
import locadora.model.Pagamento;
import locadora.model.TiposObjetos;

import javax.swing.*;
import java.awt.*;

import static locadora.controller.PagamentoController.resetarEntradas;

public class PagamentosView {

    private final JFrame frame = new JFrame();

    public PagamentosView() {
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

        JLabel lblAcao = new JLabel("Ação");
        lblAcao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblAcao.setVerticalAlignment(SwingConstants.CENTER);
        lblAcao.setBounds(10, 10, 70, 30);
        panelRegistroPagamento.add(lblAcao);

        JComboBox<String> comboBoxAcao = new JComboBox<>(new String[]{"REGISTRAR", "EDITAR", "EXCLUIR"});
        comboBoxAcao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxAcao.setBounds(75, 10, 95, 30);
        comboBoxAcao.setSelectedIndex(-1);
        panelRegistroPagamento.add(comboBoxAcao);

        JLabel lblIdPagamento = new JLabel("ID (Pagamento)");
        lblIdPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblIdPagamento.setVerticalAlignment(SwingConstants.CENTER);
        lblIdPagamento.setBounds(175, 10, 120, 30);
        lblIdPagamento.setVisible(false);
        panelRegistroPagamento.add(lblIdPagamento);

        JComboBox<Integer> comboBoxIdPagamento = new JComboBox<>(new PagamentoDAO().listaPagamentosCadastrados());
        comboBoxIdPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxIdPagamento.setBounds(305, 10, 80, 30);
        comboBoxIdPagamento.setSelectedIndex(-1);
        comboBoxIdPagamento.setVisible(false);
        panelRegistroPagamento.add(comboBoxIdPagamento);

        JComboBox<Integer> comboBoxLocacoes = new JComboBox<>(new LocacaoDAO().listaLocacoesNaoPagas());
        comboBoxLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxLocacoes.setBounds(75, 50, 95, 30);
        comboBoxLocacoes.setSelectedIndex(-1);
        comboBoxLocacoes.setVisible(false);
        panelRegistroPagamento.add(comboBoxLocacoes);

        JTextField txtValorPago = new JTextField();
        txtValorPago.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtValorPago.setBounds(75, 90, 95, 30);
        txtValorPago.setVisible(false);
        panelRegistroPagamento.add(txtValorPago);

        JTextField txtDataPagamento = new JTextField();
        txtDataPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        txtDataPagamento.setBounds(305, 50, 80, 30);
        txtDataPagamento.setVisible(false);
        panelRegistroPagamento.add(txtDataPagamento);

        JComboBox<MetodosPagamento> comboBoxMetodoPagamento = new JComboBox<>(MetodosPagamento.values());
        comboBoxMetodoPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxMetodoPagamento.setBounds(305, 90, 80, 30);
        comboBoxMetodoPagamento.setSelectedIndex(-1);
        comboBoxMetodoPagamento.setVisible(false);
        panelRegistroPagamento.add(comboBoxMetodoPagamento);

        JLabel lblLocacao = new JLabel("Locacão");
        lblLocacao.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblLocacao.setBounds(10, 50, 70, 30);
        lblLocacao.setVerticalAlignment(SwingConstants.CENTER);
        lblLocacao.setVisible(false);
        panelRegistroPagamento.add(lblLocacao);

        JLabel lblValorPago = new JLabel("Valor pago");
        lblValorPago.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblValorPago.setBounds(10, 90, 70, 30);
        lblValorPago.setVerticalAlignment(SwingConstants.CENTER);
        lblValorPago.setVisible(false);
        panelRegistroPagamento.add(lblValorPago);

        JLabel lblDataPagamento = new JLabel("Data de pagamento");
        lblDataPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblDataPagamento.setBounds(175, 50, 140, 30);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        lblDataPagamento.setVisible(false);
        panelRegistroPagamento.add(lblDataPagamento);

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento");
        lblMetodoPagamento.setFont(new Font("Open Sans", Font.PLAIN, 12));
        lblMetodoPagamento.setBounds(175, 90, 140, 30);
        lblDataPagamento.setVerticalAlignment(SwingConstants.CENTER);
        lblMetodoPagamento.setVisible(false);
        panelRegistroPagamento.add(lblMetodoPagamento);

        JButton btnGerarValor = new JButton("<html><div style='text-align:center'>Gerar valor</div></html>");
        btnGerarValor.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnGerarValor.setBounds(20, 200, 100, 50);
        btnGerarValor.setVisible(false);
        panel.add(btnGerarValor);

        JButton btnConfirmar = new JButton("<html><div style='text-align:center'>Confirmar</div></html>");
        btnConfirmar.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnConfirmar.setBounds(165, 200, 100, 50);
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
            if (lista != null) {
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

        comboBoxAcao.addItemListener(e -> {
            String acao = String.valueOf(comboBoxAcao.getSelectedItem());
            switch (acao) {
                case "REGISTRAR":
                    lblIdPagamento.setVisible(false);
                    comboBoxIdPagamento.setVisible(false);
                    lblLocacao.setVisible(true);
                    comboBoxLocacoes.setVisible(true);
                    lblValorPago.setVisible(true);
                    txtValorPago.setVisible(true);
                    lblDataPagamento.setVisible(true);
                    txtDataPagamento.setVisible(true);
                    lblMetodoPagamento.setVisible(true);
                    comboBoxMetodoPagamento.setVisible(true);
                    btnGerarValor.setVisible(true);
                    btnConfirmar.setVisible(true);
                    comboBoxLocacoes.setModel(new DefaultComboBoxModel<>(new LocacaoDAO().listaLocacoesNaoPagas()));
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    break;
                case "EDITAR":
                    lblIdPagamento.setVisible(true);
                    comboBoxIdPagamento.setVisible(true);
                    lblLocacao.setVisible(true);
                    comboBoxLocacoes.setVisible(true);
                    lblValorPago.setVisible(true);
                    txtValorPago.setVisible(true);
                    lblDataPagamento.setVisible(true);
                    txtDataPagamento.setVisible(true);
                    lblMetodoPagamento.setVisible(true);
                    comboBoxMetodoPagamento.setVisible(true);
                    btnGerarValor.setVisible(true);
                    btnConfirmar.setVisible(true);
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    break;
                case "EXCLUIR":
                    lblIdPagamento.setVisible(true);
                    comboBoxIdPagamento.setVisible(true);
                    lblLocacao.setVisible(false);
                    comboBoxLocacoes.setVisible(false);
                    lblValorPago.setVisible(false);
                    txtValorPago.setVisible(false);
                    lblDataPagamento.setVisible(false);
                    txtDataPagamento.setVisible(false);
                    lblMetodoPagamento.setVisible(false);
                    comboBoxMetodoPagamento.setVisible(false);
                    btnGerarValor.setVisible(false);
                    btnConfirmar.setVisible(true);
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    break;
                default:
                    break;
            }
        });

        comboBoxIdPagamento.addItemListener(e -> {
            String acao = String.valueOf(comboBoxAcao.getSelectedItem());
            if (acao.equals("EDITAR")) {
                String idPagamento = String.valueOf(comboBoxIdPagamento.getSelectedItem());
                Pagamento pagamento = new PagamentoDAO().ler(idPagamento);
                int idlocacao = pagamento.getIdLocacao();
                comboBoxLocacoes.setModel(new DefaultComboBoxModel<>(new Integer[]{idlocacao}));
            }
        });

        btnGerarValor.addActionListener(e -> {
            double valor = new PagamentoController().calcularPagamento(comboBoxLocacoes, txtDataPagamento);
            txtValorPago.setText(String.valueOf(valor));
        });

        btnConfirmar.addActionListener(e -> {
            String acao = String.valueOf(comboBoxAcao.getSelectedItem());
            PagamentoController pagamentoController = new PagamentoController();
            switch (acao) {
                case "REGISTRAR":
                    pagamentoController.registrarPagamento(comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    break;
                case "EDITAR":
                    pagamentoController.editarPagamento(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
                    break;
                case "EXCLUIR":
                    pagamentoController.excluirPagamento(comboBoxIdPagamento);
                    resetarEntradas(comboBoxIdPagamento, comboBoxLocacoes, txtValorPago, txtDataPagamento, comboBoxMetodoPagamento);
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
