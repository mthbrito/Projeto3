package locadora.view;

import javax.swing.*;
import java.awt.*;

public abstract class LoginView {

    private JFrame frame;
    private JPanel panel;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblSenha;
    private JTextField txtSenha;
    private JButton btnAcessar;

    public LoginView() {
        initialize();
    }

    protected void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(46, 107, 240));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        lblTitulo = new JLabel("Login");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 30, 200, 40);
        panel.add(lblTitulo);

        lblUsuario = new JLabel("Usuário");
        lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsuario.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(90, 100, 60, 30);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtUsuario.setBounds(160, 100, 200, 30);
        panel.add(txtUsuario);

        lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
        lblSenha.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(90, 150, 60, 30);
        panel.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setFont(new Font("Open Sans", Font.PLAIN, 14));
        txtSenha.setBounds(160, 150, 200, 30);
        panel.add(txtSenha);

        btnAcessar = new JButton("Acessar");
        btnAcessar.setFont(new Font("Opens Sans", Font.PLAIN, 14));
        btnAcessar.setBounds(175, 210, 100, 30);
        panel.add(btnAcessar);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JLabel getLblSenha() {
        return lblSenha;
    }

    public JTextField getTxtSenha() {
        return txtSenha;
    }

    public JButton getBtnAcessar() {
        return btnAcessar;
    }
}
