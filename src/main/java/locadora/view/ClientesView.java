package locadora.view;

import locadora.controller.ClienteController;

import javax.swing.*;
import java.awt.*;

public class ClientesView {

    private final JFrame frame = new JFrame();

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
        panelClientes.setBackground(new Color(255,251,219));
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

//        frame.setBounds(100, 100, 335, 205);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//        frame.getContentPane().setBackground(new Color(46, 107, 240));
//
//        JLabel lblClientes = new JLabel("Clientes");
//        lblClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
//        lblClientes.setForeground(Color.WHITE);
//        lblClientes.setBounds(120, 10, 60, 15);
//        lblClientes.setVerticalAlignment(SwingConstants.CENTER);
//        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
//        frame.getContentPane().add(lblClientes);
//
//        JPanel panelClientes = new JPanel();
//        panelClientes.setLayout(null);
//        panelClientes.setBackground(Color.WHITE);
//        panelClientes.setBounds(10, 35, 300, 70);
//        frame.getContentPane().add(panelClientes);
//
//        JTextField txtNome = new JTextField();
//        txtNome.setColumns(10);
//        txtNome.setBounds(45, 10, 90, 20);
//        panelClientes.add(txtNome);
//
//        JTextField txtCpf = new JTextField();
//        txtCpf.setColumns(10);
//        txtCpf.setBounds(45, 40, 90, 20);
//        panelClientes.add(txtCpf);
//
//        JTextField txtTelefone = new JTextField();
//        txtTelefone.setColumns(10);
//        txtTelefone.setBounds(200, 10, 90, 20);
//        panelClientes.add(txtTelefone);
//
//        JTextField txtEmail = new JTextField();
//        txtEmail.setColumns(10);
//        txtEmail.setBounds(200, 40, 90, 20);
//        panelClientes.add(txtEmail);
//
//        JLabel lblNome = new JLabel("Nome");
//        lblNome.setBounds(10, 10, 45, 15);
//        lblNome.setVerticalAlignment(SwingConstants.CENTER);
//        panelClientes.add(lblNome);
//
//        JLabel lblCpf = new JLabel("CPF");
//        lblCpf.setBounds(10, 40, 45, 15);
//        lblCpf.setVerticalAlignment(SwingConstants.CENTER);
//        panelClientes.add(lblCpf);
//
//        JLabel lblTelefone = new JLabel("Telefone");
//        lblTelefone.setBounds(145, 10, 50, 15);
//        lblTelefone.setVerticalAlignment(SwingConstants.CENTER);
//        panelClientes.add(lblTelefone);
//
//        JLabel lblEmail = new JLabel("E-mail");
//        lblEmail.setBounds(145, 40, 50, 15);
//        lblEmail.setVerticalAlignment(SwingConstants.CENTER);
//        panelClientes.add(lblEmail);
//
//        JButton btnCadastrarCliente = new JButton("<html><div style='text-align:center'>Cadastrar Cliente</div></html>");
//        btnCadastrarCliente.setBounds(10, 115, 90, 40);
//        btnCadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
//        btnCadastrarCliente.setVerticalAlignment(SwingConstants.CENTER);
//        frame.add(btnCadastrarCliente);
//
//        JButton btnEditarCliente = new JButton("<html><div style='text-align:center'>Editar Cliente</div></html>");
//        btnEditarCliente.setBounds(115, 115, 90, 40);
//        btnEditarCliente.setHorizontalAlignment(SwingConstants.CENTER);
//        btnEditarCliente.setVerticalAlignment(SwingConstants.CENTER);
//        frame.add(btnEditarCliente);
//
//        JButton btnExcluirCliente = new JButton("<html><div style='text-align:center'>Excluir Cliente</div></html>");
//        btnExcluirCliente.setBounds(220, 115, 90, 40);
//        btnExcluirCliente.setHorizontalAlignment(SwingConstants.CENTER);
//        btnExcluirCliente.setVerticalAlignment(SwingConstants.CENTER);
//        frame.add(btnExcluirCliente);

        btnCadastrarCliente.addActionListener(e -> new ClienteController().cadastrarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnEditarCliente.addActionListener(e -> new ClienteController().editarCliente(txtNome, txtCpf, txtTelefone, txtEmail));
        btnExcluirCliente.addActionListener(e -> new ClienteController().excluirCliente(txtCpf));
    }

    public JFrame getFrame() {
        return frame;
    }
}
