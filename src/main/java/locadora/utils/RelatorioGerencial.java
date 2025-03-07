package locadora.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.model.Locacao;
import locadora.model.Pagamento;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class RelatorioGerencial {

    private final String caminhoArquivo = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\data\\pdf";

    public void gerarRelatorioVeiculosLocados() {
        List<Locacao> locacoes = new LocacaoDAO().locacoesCadastradas();
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, Files.newOutputStream(Paths.get(caminhoArquivo + "\\veiculosLocados.pdf")));
            documento.open();

            // Fonte personalizada para o título
            Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fonteNegrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Criando o título
            Paragraph titulo = new Paragraph("Veículos locados no mês\n\n", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Adicionando informações sobre as locações
            for (Locacao locacao : locacoes) {
                documento.add(new Paragraph("ID (Locação): " + locacao.getIdLocacao(), fonteNegrito));
                documento.add(new Paragraph("Cliente: " + locacao.getCliente().getNome(), fonteNormal));
                documento.add(new Paragraph("Veículo: " + locacao.getVeiculo().getTipo(), fonteNormal));
                documento.add(new Paragraph("Modelo: " + locacao.getVeiculo().getModelo(), fonteNormal));
                documento.add(new Paragraph("Placa: " + locacao.getVeiculo().getPlaca(), fonteNormal));
                documento.add(new Paragraph("Ano: " + locacao.getVeiculo().getAno(), fonteNormal));
                documento.add(new Paragraph("Data de retirada: " + locacao.getDataDeRetirada(), fonteNormal));
                documento.add(new Paragraph("Data de devolução: " + locacao.getDataDeDevolucao(), fonteNormal));

                // Adicionando espaçamento entre locações
                documento.add(Chunk.NEWLINE);
            }
            JOptionPane.showMessageDialog(null, "Relatório gerado, cheque a pasta data/pdf", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }

    public void gerarFaturamentoMensal() {
        List<Pagamento> pagamentos = new PagamentoDAO().pagamentosCadastrados();
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, Files.newOutputStream(Paths.get(caminhoArquivo + "\\faturamentoMensal.pdf")));
            documento.open();

            // Fonte personalizada para o título
            Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fonteNegrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Criando o título
            Paragraph titulo = new Paragraph("Faturamento mensal\n\n", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            double total = 0;
            // Adicionando informações sobre as locações
            for (Pagamento pagamento : pagamentos) {
                documento.add(new Paragraph("ID (Pagamento): " + pagamento.getIdPagamento(), fonteNegrito));
                documento.add(new Paragraph("Valor pago: R$" + pagamento.getValorPago(), fonteNormal));
                total += pagamento.getValorPago();
                // Adicionando espaçamento entre locações
                documento.add(Chunk.NEWLINE);
            }
            documento.add(new Paragraph("Total: R$" + total, fonteNormal));
            JOptionPane.showMessageDialog(null, "Relatório gerado, cheque a pasta data/pdf", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }

    public void gerarRelatorioClientesLocacoes() {
        List<Locacao> locacoes = new LocacaoDAO().locacoesCadastradas();
        locacoes.sort(Comparator.comparing(locacao -> locacao.getCliente().getCpf()));
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, Files.newOutputStream(Paths.get(caminhoArquivo + "\\clientesLocacoes.pdf")));
            documento.open();

            // Fonte personalizada para o título
            Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fonteNegrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Criando o título
            Paragraph titulo = new Paragraph("Clientes e suas locações\n\n", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Adicionando informações sobre as locações
            for (Locacao locacao : locacoes) {
                documento.add(new Paragraph("ID (Locação): " + locacao.getIdLocacao(), fonteNegrito));
                documento.add(new Paragraph("Cliente: " + locacao.getCliente().getNome() + "/" + locacao.getCliente().getCpf(), fonteNormal));
                documento.add(new Paragraph("Veículo: " + locacao.getVeiculo().getTipo(), fonteNormal));
                documento.add(new Paragraph("Modelo: " + locacao.getVeiculo().getModelo(), fonteNormal));
                documento.add(new Paragraph("Placa: " + locacao.getVeiculo().getPlaca(), fonteNormal));
                documento.add(new Paragraph("Ano: " + locacao.getVeiculo().getAno(), fonteNormal));
                documento.add(new Paragraph("Data de retirada: " + locacao.getDataDeRetirada(), fonteNormal));
                documento.add(new Paragraph("Data de devolução: " + locacao.getDataDeDevolucao(), fonteNormal));

                // Adicionando espaçamento entre locações
                documento.add(Chunk.NEWLINE);
            }
            JOptionPane.showMessageDialog(null, "Relatório gerado, cheque a pasta data/pdf", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
}


