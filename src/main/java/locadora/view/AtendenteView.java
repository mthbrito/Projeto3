package locadora.view;

import javax.swing.*;
import java.awt.*;

public class AtendenteView {

    private JFrame frame;

    public AtendenteView() {
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

        JLabel lblTitulo = new JLabel("Atendente");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 30, 200, 40);
        panel.add(lblTitulo);

        JLabel lblOperacoes = new JLabel("Operação");
        lblOperacoes.setHorizontalAlignment(SwingConstants.CENTER);
        lblOperacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblOperacoes.setForeground(Color.WHITE);
        lblOperacoes.setBounds(120, 80, 190, 30);
        panel.add(lblOperacoes);

        JPanel subPanelOperacoes = new JPanel();
        subPanelOperacoes.setBackground(Color.WHITE);
        subPanelOperacoes.setBounds(120, 120, 190, 120);
        subPanelOperacoes.setLayout(null);
        panel.add(subPanelOperacoes);

        JComboBox<String> comboBoxOperacoes = new JComboBox<>(new String[]{"Locação", "Pagamento"});
        comboBoxOperacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxOperacoes.setSelectedIndex(-1);
        comboBoxOperacoes.setBounds(20, 20, 150, 30);
        subPanelOperacoes.add(comboBoxOperacoes);

        JButton btnGerenciarOperacoes = new JButton("Gerenciar");
        btnGerenciarOperacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnGerenciarOperacoes.setBounds(20, 70, 150, 30);
        subPanelOperacoes.add(btnGerenciarOperacoes);

        btnGerenciarOperacoes.addActionListener(e -> {
            String opcao = comboBoxOperacoes.getSelectedItem() != null ? (String) comboBoxOperacoes.getSelectedItem() : "";
            switch (opcao) {
                case "Locação":
                    LocacoesView telaLocacoes = new LocacoesView();
                    telaLocacoes.getFrame().setVisible(true);
                    break;
                case "Pagamento":
                    PagamentosView telaPagamentos = new PagamentosView();
                    telaPagamentos.getFrame().setVisible(true);
                    break;
                case "":
                    JOptionPane.showMessageDialog(null, "Insira uma opção!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
