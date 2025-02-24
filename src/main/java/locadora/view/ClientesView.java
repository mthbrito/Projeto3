package locadora.view;

import locadora.controller.ClienteController;

import javax.swing.*;
import java.awt.*;

public class ClientesView {

    private final JFrame frame = new JFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientesView window = new ClientesView();
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
    public ClientesView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setBounds(100, 100, 335, 205);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblClientes = new JLabel("Clientes");
        lblClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblClientes.setBounds(120, 10, 60, 15);
        lblClientes.setVerticalAlignment(SwingConstants.CENTER);
        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblClientes);

        JPanel panelClientes = new JPanel();
        panelClientes.setLayout(null);
        panelClientes.setBackground(Color.WHITE);
        panelClientes.setBounds(10, 35, 300, 70);
        frame.getContentPane().add(panelClientes);

        JTextField txtNome = new JTextField();
        txtNome.setColumns(10);
        txtNome.setBounds(45, 10, 90, 20);
        panelClientes.add(txtNome);

        JTextField txtCpf = new JTextField();
        txtCpf.setColumns(10);
        txtCpf.setBounds(45, 40, 90, 20);
        panelClientes.add(txtCpf);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setColumns(10);
        txtTelefone.setBounds(200, 10, 90, 20);
        panelClientes.add(txtTelefone);

        JTextField txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(200, 40, 90, 20);
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
        lblTelefone.setBounds(145, 10, 50, 15);
        lblTelefone.setVerticalAlignment(SwingConstants.CENTER);
        panelClientes.add(lblTelefone);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(145, 40, 50, 15);
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

        btnCadastrarCliente.addActionListener(e -> new ClienteController().cadastrarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnEditarCliente.addActionListener(e -> new ClienteController().editarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnExcluirCliente.addActionListener(e -> new ClienteController().excluirCliente(txtCpf));
    }

}
