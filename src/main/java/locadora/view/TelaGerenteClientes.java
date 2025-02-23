package locadora.view;

import locadora.controller.ClienteController;
import locadora.controller.VeiculoController;
import locadora.model.StatusVeiculo;

import javax.swing.*;
import java.awt.*;

public class TelaGerenteClientes {

    private final JFrame frame = new JFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaGerenteClientes window = new TelaGerenteClientes();
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
    public TelaGerenteClientes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setBounds(100, 100, 335, 205);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCadastroClientes = new JLabel("Clientes");
        lblCadastroClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCadastroClientes.setBounds(85, 10, 60, 15);
        lblCadastroClientes.setVerticalAlignment(SwingConstants.CENTER);
        lblCadastroClientes.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblCadastroClientes);

        JPanel panelClientes = new JPanel();
        panelClientes.setLayout(null);
        panelClientes.setBackground(Color.WHITE);
        panelClientes.setBounds(10, 35, 300, 70);
        frame.getContentPane().add(panelClientes);

        JTextField txtNome = new JTextField();
        txtNome.setColumns(10);
        txtNome.setBounds(55, 10, 80, 20);
        panelClientes.add(txtNome);

        JTextField txtCpf = new JTextField();
        txtCpf.setColumns(10);
        txtCpf.setBounds(55, 40, 80, 20);
        panelClientes.add(txtCpf);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setColumns(10);
        txtTelefone.setBounds(210, 10, 80, 20);
        panelClientes.add(txtTelefone);

        JTextField txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(210, 40, 80, 20);
        panelClientes.add(txtEmail);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 10, 45, 15);
        lblNome.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(10, 40, 45, 15);
        lblCpf.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(155, 10, 50, 15);
        lblTelefone.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblTelefone);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(155, 40, 50, 15);
        lblEmail.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblEmail);

        JButton btnCadastrarCliente = new JButton("<html><div style='text-align:center'>Cadastrar Cliente</div></html>");
        btnCadastrarCliente.setBounds(10, 115, 90, 40);
        btnCadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnCadastrarCliente.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(btnCadastrarCliente);

        JButton btnEditarCliente = new JButton("<html><div style='text-align:center'>Editar Cliente</div></html>");
        btnEditarCliente.setBounds(115, 115, 90, 40);
        btnEditarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnEditarCliente.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(btnEditarCliente);

        JButton btnExcluirCliente = new JButton("<html><div style='text-align:center'>Excluir Cliente</div></html>");
        btnExcluirCliente.setBounds(220, 115, 90, 40);
        btnExcluirCliente.setHorizontalAlignment(SwingConstants.CENTER);
        btnExcluirCliente.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(btnExcluirCliente);

        btnCadastrarCliente.addActionListener(e -> new ClienteController().CadastrarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnEditarCliente.addActionListener(e -> new ClienteController().EditarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnExcluirCliente.addActionListener(e -> new ClienteController().ExcluirCliente(txtCpf));
    }

}
