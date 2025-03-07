package locadora.view;

import locadora.controller.ClienteController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.TiposObjetos;

import javax.swing.*;
import java.awt.*;

public class ClientesView {

    private final JFrame frame = new JFrame();
    private final ClienteController clienteController = new ClienteController();

    public ClientesView() {
        initialize();
    }

    public void initialize() {
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(46, 107, 240));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblClientes = new JLabel("Clientes");
        lblClientes.setFont(new Font("Open Sans", Font.BOLD, 24));
        lblClientes.setForeground(Color.WHITE);
        lblClientes.setBounds(125, 30, 200, 40);
        lblClientes.setVerticalAlignment(SwingConstants.CENTER);
        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblClientes);

        JPanel panelClientes = new JPanel();
        panelClientes.setBackground(new Color(255, 251, 219));
        panelClientes.setLayout(null);
        panelClientes.setBackground(Color.WHITE);
        panelClientes.setBounds(20, 90, 390, 90);
        panel.add(panelClientes);

        JTextField txtNome = new JTextField();
        txtNome.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtNome.setBounds(60, 10, 110, 30);
        panelClientes.add(txtNome);

        JTextField txtCpf = new JTextField();
        txtCpf.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtCpf.setBounds(60, 50, 110, 30);
        panelClientes.add(txtCpf);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtTelefone.setBounds(245, 10, 130, 30);
        panelClientes.add(txtTelefone);

        JTextField txtEmail = new JTextField();
        txtEmail.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtEmail.setBounds(245, 50, 130, 30);
        panelClientes.add(txtEmail);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblNome.setBounds(10, 10, 45, 30);
        lblNome.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblCpf.setBounds(10, 50, 45, 30);
        lblCpf.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblTelefone.setBounds(180, 10, 60, 30);
        lblTelefone.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblTelefone);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblEmail.setBounds(180, 50, 60, 30);
        lblEmail.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblEmail);

        JButton btnCadastrarCliente = new JButton("<html><div style='text-align:center'>Cadastrar Cliente</div></html>");
        btnCadastrarCliente.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnCadastrarCliente.setBounds(20, 200, 90, 50);
        btnCadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnCadastrarCliente.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(btnCadastrarCliente);

        JButton btnEditarCliente = new JButton("<html><div style='text-align:center'>Editar Cliente</div></html>");
        btnEditarCliente.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnEditarCliente.setBounds(170, 200, 90, 50);
        btnEditarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnEditarCliente.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(btnEditarCliente);

        JButton btnExcluirCliente = new JButton("<html><div style='text-align:center'>Excluir Cliente</div></html>");
        btnExcluirCliente.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnExcluirCliente.setBounds(320, 200, 90, 50);
        btnExcluirCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnExcluirCliente.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(btnExcluirCliente);

        JLabel lblListas = new JLabel("Listas");
        lblListas.setFont(new Font("Open Sans", Font.BOLD, 14));
        lblListas.setBounds(330, 30, 80, 20);
        lblListas.setForeground(Color.WHITE);
        lblListas.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblListas);

        JComboBox<TiposObjetos> comboBoxListas = new JComboBox<>(TiposObjetos.values());
        comboBoxListas.setFont(new Font("Open Sans", Font.PLAIN, 12));
        comboBoxListas.setBounds(330, 50, 80, 30);
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

        btnCadastrarCliente.addActionListener(e -> {
            clienteController.cadastrarCliente(txtNome, txtCpf, txtTelefone, txtEmail);
        });
        btnEditarCliente.addActionListener(e -> {
            clienteController.editarCliente(txtNome, txtCpf, txtTelefone, txtEmail);
        });
        btnExcluirCliente.addActionListener(e -> {
            clienteController.excluirCliente(txtCpf);
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
