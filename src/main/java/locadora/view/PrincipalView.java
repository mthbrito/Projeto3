package locadora.view;

import javax.swing.*;
import java.awt.*;

public class PrincipalView {

    private final JFrame frame = new JFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrincipalView window = new PrincipalView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        });
    }

    public PrincipalView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Login");
        JMenuItem telaAdm = new JMenuItem("Administrador");
        JMenuItem telaGer = new JMenuItem("Gerente");
        JMenuItem telaAtend = new JMenuItem("Atendente");

        menu.add(telaAdm);
        menu.add(telaGer);
        menu.add(telaAtend);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JLabel lblTitulo = new JLabel("Locadora Sobra Rodas");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Bebas Neue", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 30, 200, 20);
        panel.add(lblTitulo);

        JLabel lblSubTitulo = new JLabel("Sempre um veículo à disposição!");
        lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubTitulo.setFont(new Font("Roboto", Font.ITALIC, 14));
        lblSubTitulo.setForeground(Color.WHITE);
        lblSubTitulo.setBounds(75, 60, 300, 20);
        panel.add(lblSubTitulo);

        ImageIcon icone = new ImageIcon("src/main/java/locadora/image/carro_preto.png");
        Image imagem = icone.getImage();
        Image novaImagem = imagem.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon novoIcone = new ImageIcon(novaImagem);

        JLabel lblIcone = new JLabel("");
        lblIcone.setIcon(novoIcone);
        lblIcone.setBounds(125, 75, 200, 150);
        panel.add(lblIcone);

        telaAdm.addActionListener(e -> {
            LoginAdmView telaLoginAdm = new LoginAdmView();
            telaLoginAdm.getFrame().setVisible(true);
        });

        telaGer.addActionListener(e -> {
            LoginGerView telaLoginGer = new LoginGerView();
            telaLoginGer.getFrame().setVisible(true);
        });

        telaAtend.addActionListener(e -> {
            LoginAtendView telaLoginAtend = new LoginAtendView();
            telaLoginAtend.getFrame().setVisible(true);
        });
    }
}
