package locadora.view;

import locadora.utils.RelatorioGerencial;

import javax.swing.*;
import java.awt.*;

public class GerenteView {

    private JFrame frame;

    public GerenteView(){
        initialize();
    }

    private void initialize(){

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblTitulo = new JLabel("Gerente");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 30, 200, 40);
        panel.add(lblTitulo);

        JLabel lblRecursos = new JLabel("Recursos");
        lblRecursos.setHorizontalAlignment(SwingConstants.CENTER);
        lblRecursos.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblRecursos.setForeground(Color.WHITE);
        lblRecursos.setBounds(20, 80, 190, 30);
        panel.add(lblRecursos);

        JLabel lblRelatorios = new JLabel("Relatórios");
        lblRelatorios.setHorizontalAlignment(SwingConstants.CENTER);
        lblRelatorios.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblRelatorios.setForeground(Color.WHITE);
        lblRelatorios.setBounds(225, 80, 190, 30);
        panel.add(lblRelatorios);

        JPanel subPanelRecursos = new JPanel();
        subPanelRecursos.setBackground(Color.WHITE);
        subPanelRecursos.setBounds(20,120, 190,120);
        subPanelRecursos.setLayout(null);
        panel.add(subPanelRecursos);

        JComboBox<String> comboBoxGerenciarRecursos = new JComboBox<>(new String[]{"Cliente", "Veículo"});
        comboBoxGerenciarRecursos.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxGerenciarRecursos.setSelectedIndex(-1);
        comboBoxGerenciarRecursos.setBounds(20, 20, 150, 30);
        subPanelRecursos.add(comboBoxGerenciarRecursos);

        JButton btnGerenciarRecursos = new JButton("Gerenciar");
        btnGerenciarRecursos.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnGerenciarRecursos.setBounds(20, 70, 150, 30);
        subPanelRecursos.add(btnGerenciarRecursos);

        JPanel subPanelRelatorios = new JPanel();
        subPanelRelatorios.setBackground(Color.WHITE);
        subPanelRelatorios.setBounds(225,120, 190,120);
        subPanelRelatorios.setLayout(null);
        panel.add(subPanelRelatorios);

        JComboBox<String> comboBoxGerarRelatorios = new JComboBox<>(new String[]{"Cliente e suas locações", "Veículos locados", "Faturamento mensal"});
        comboBoxGerarRelatorios.setFont(new Font("Open Sans", Font.PLAIN, 14));
        comboBoxGerarRelatorios.setSelectedIndex(-1);
        comboBoxGerarRelatorios.setBounds(15, 20, 160, 30);
        subPanelRelatorios.add(comboBoxGerarRelatorios);

        JButton btnGerarRelatorio = new JButton("Gerar");
        btnGerarRelatorio.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnGerarRelatorio.setBounds(20, 70, 150, 30);
        subPanelRelatorios.add(btnGerarRelatorio);

//        frame = new JFrame();
//        frame.setBounds(100, 100, 450, 300);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(25, 25, 112));
//        panel.setBounds(0, 0, 450, 300);
//        panel.setLayout(null);
//        frame.getContentPane().add(panel);
//
//        JLabel lblGerarRelatorio = new JLabel("Gerar relatórios");
//        lblGerarRelatorio.setBounds(125, 10, 120, 20);
//        lblGerarRelatorio.setFont(new Font("Open Sans", Font.PLAIN, 14));
//        lblGerarRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
//        panel.add(lblGerarRelatorio);
//
//        JButton btnGerarRelatorioClienteLocacoes = new JButton("<html><div style='text-align:center'>Cliente e suas locações</div></html>");
//        btnGerarRelatorioClienteLocacoes.setBounds(125, 50, 120, 40);
//        btnGerarRelatorioClienteLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
//        panel.add(btnGerarRelatorioClienteLocacoes);
//
//        JButton btnGerarRelatorioVeiculosLocados = new JButton("<html><div style='text-align:center'>Veículos locados</div></html>");
//        btnGerarRelatorioVeiculosLocados.setBounds(125, 100, 120, 40);
//        btnGerarRelatorioVeiculosLocados.setFont(new Font("Open Sans", Font.PLAIN, 14));
//        panel.add(btnGerarRelatorioVeiculosLocados);
//
//        JButton btnGerarRelatorioFaturamentoMensal = new JButton("<html><div style='text-align:center'>Faturamento mensal</div></html>");
//        btnGerarRelatorioFaturamentoMensal.setBounds(140, 150, 120, 40);
//        btnGerarRelatorioFaturamentoMensal.setFont(new Font("Open Sans", Font.PLAIN, 14));
//        panel.add(btnGerarRelatorioFaturamentoMensal);
//
//        btnGerarRelatorioClienteLocacoes.addActionListener(e -> {
//            new RelatorioGerencial().gerarRelatorioClientesLocacoes();
//            System.out.println("Relatório sobre clientes e suas locações disponível!");
//        });
//        btnGerarRelatorioVeiculosLocados.addActionListener(e -> {
//            new RelatorioGerencial().gerarRelatorioVeiculosLocados();
//            System.out.println("Relatório sobre veículos locados disponível!");
//        });
//        btnGerarRelatorioFaturamentoMensal.addActionListener(e -> {
//            new RelatorioGerencial().gerarFaturamentoMensal();
//            System.out.println("Relatório sobre faturamento mensal disponível!");
//        });

        btnGerenciarRecursos.addActionListener(e -> {
            String opcao = comboBoxGerenciarRecursos.getSelectedItem() != null? (String)comboBoxGerenciarRecursos.getSelectedItem() : "";
            switch (opcao) {
                case "Cliente":
                    ClientesView telaClientes = new ClientesView();
                    telaClientes.getFrame().setVisible(true);
                    break;
                case "Veículo":
                    VeiculosView telaVeiculos = new VeiculosView();
                    telaVeiculos.getFrame().setVisible(true);
                    break;
                case "":
                    JOptionPane.showMessageDialog(null, "Insira uma opção!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        });

        btnGerarRelatorio.addActionListener(e -> {
            String opcao = comboBoxGerarRelatorios.getSelectedItem() != null? (String)comboBoxGerarRelatorios.getSelectedItem() : "";
            switch (opcao) {
                case "Cliente e suas locações":
                    new RelatorioGerencial().gerarRelatorioClientesLocacoes();
                    System.out.println("Relatório sobre clientes e suas locações disponível!");
                    break;
                case "Veículos locados":
                    new RelatorioGerencial().gerarRelatorioVeiculosLocados();
                    System.out.println("Relatório sobre veículos locados disponível!");
                    break;
                case "Faturamento mensal":
                    new RelatorioGerencial().gerarFaturamentoMensal();
                    System.out.println("Relatório sobre faturamento mensal disponível!");
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
