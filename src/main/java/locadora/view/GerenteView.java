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

        JLabel lblGerarRelatorio = new JLabel("Gerar relatórios");
        lblGerarRelatorio.setBounds(125, 10, 120, 20);
        lblGerarRelatorio.setFont(new Font("Open Sans", Font.PLAIN, 14));
        lblGerarRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblGerarRelatorio);

        JButton btnGerarRelatorioClienteLocacoes = new JButton("<html><div style='text-align:center'>Cliente e suas locações</div></html>");
        btnGerarRelatorioClienteLocacoes.setBounds(125, 50, 120, 40);
        btnGerarRelatorioClienteLocacoes.setFont(new Font("Open Sans", Font.PLAIN, 14));
        panel.add(btnGerarRelatorioClienteLocacoes);

        JButton btnGerarRelatorioVeiculosLocados = new JButton("<html><div style='text-align:center'>Veículos locados</div></html>");
        btnGerarRelatorioVeiculosLocados.setBounds(125, 100, 120, 40);
        btnGerarRelatorioVeiculosLocados.setFont(new Font("Open Sans", Font.PLAIN, 14));
        panel.add(btnGerarRelatorioVeiculosLocados);

        JButton btnGerarRelatorioFaturamentoMensal = new JButton("<html><div style='text-align:center'>Faturamento mensal</div></html>");
        btnGerarRelatorioFaturamentoMensal.setBounds(140, 150, 120, 40);
        btnGerarRelatorioFaturamentoMensal.setFont(new Font("Open Sans", Font.PLAIN, 14));
        panel.add(btnGerarRelatorioFaturamentoMensal);

        btnGerarRelatorioClienteLocacoes.addActionListener(e -> {
            new RelatorioGerencial().gerarRelatorioClientesLocacoes();
            System.out.println("Relatório sobre clientes e suas locações disponível!");
        });
        btnGerarRelatorioVeiculosLocados.addActionListener(e -> {
            new RelatorioGerencial().gerarRelatorioVeiculosLocados();
            System.out.println("Relatório sobre veículos locados disponível!");
        });
        btnGerarRelatorioFaturamentoMensal.addActionListener(e -> {
            new RelatorioGerencial().gerarFaturamentoMensal();
            System.out.println("Relatório sobre faturamento mensal disponível!");
        });

    }

    public JFrame getFrame() {
        return frame;
    }
}
