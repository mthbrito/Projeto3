package locadora.view;

import locadora.controller.UsuarioController;
import locadora.model.TiposUsuarios;

import javax.swing.*;
import java.awt.*;

import static locadora.controller.UsuarioController.resetarEntradas;

public class AdministradorView {

    private final UsuarioController usuarioController = new UsuarioController();
    private JFrame frame;

    public AdministradorView() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblTitulo = new JLabel("Administrador");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 30, 200, 40);
        panel.add(lblTitulo);

        JLabel lblFuncao = new JLabel("Função");
        lblFuncao.setHorizontalAlignment(SwingConstants.CENTER);
        lblFuncao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblFuncao.setForeground(Color.WHITE);
        lblFuncao.setBounds(20, 100, 60, 30);
        panel.add(lblFuncao);

        JComboBox<TiposUsuarios> comboBoxFuncao = new JComboBox<>(TiposUsuarios.values());
        comboBoxFuncao.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxFuncao.setSelectedIndex(-1);
        comboBoxFuncao.setBounds(90, 100, 150, 30);
        panel.add(comboBoxFuncao);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(20, 150, 60, 30);
        panel.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtUsuario.setBounds(90, 150, 150, 30);
        panel.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
        lblSenha.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(20, 200, 60, 30);
        panel.add(lblSenha);

        JTextField txtSenha = new JTextField();
        txtSenha.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtSenha.setBounds(90, 200, 150, 30);
        panel.add(txtSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnCadastrar.setBounds(280, 100, 120, 30);
        panel.add(btnCadastrar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnEditar.setBounds(280, 150, 120, 30);
        panel.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnExcluir.setBounds(280, 200, 120, 30);
        panel.add(btnExcluir);

        comboBoxFuncao.addActionListener(e -> resetarEntradas(txtUsuario, txtSenha));
        btnCadastrar.addActionListener(e -> usuarioController.cadastrarUsuario(comboBoxFuncao, txtUsuario, txtSenha));
        btnEditar.addActionListener(e -> usuarioController.editarUsuario(comboBoxFuncao, txtUsuario, txtSenha));
        btnExcluir.addActionListener(e -> usuarioController.excluirUsuario(comboBoxFuncao, txtUsuario));
    }

    public JFrame getFrame() {
        return frame;
    }
}
