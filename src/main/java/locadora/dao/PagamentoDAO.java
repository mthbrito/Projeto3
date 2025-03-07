package locadora.dao;

import locadora.exception.PagamentoJaExisteException;
import locadora.exception.PagamentoNaoExisteException;
import locadora.model.Pagamento;
import locadora.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO extends JsonHandler implements IPersistencia<Pagamento, Object> {

    private final String json = getCaminhoArquivoJson("\\data\\json\\pagamentos.json");
    private List<Pagamento> pagamentos;

    public PagamentoDAO() {
        this.pagamentos = pagamentosCadastrados();
    }

    @Override
    public void salvar(Pagamento pagamentoNovo) throws PagamentoJaExisteException {
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == pagamentoNovo.getIdPagamento()) {
                throw new PagamentoJaExisteException("Pagamento já existe: " + pagamentoNovo.getIdPagamento());
            }
        }
        pagamentos.add(pagamentoNovo);
        atualizarJson(pagamentos);
        System.out.println("Pagamento salvo!");
    }

    @Override
    public Pagamento ler(Object idPagamento) throws PagamentoNaoExisteException {
        try {
            for (Pagamento pagamentoListado : pagamentos) {
                int pagamento = Integer.parseInt(String.valueOf(idPagamento));
                if (pagamentoListado.getIdPagamento() == pagamento) {
                    return pagamentoListado;
                }
            }
            throw new PagamentoNaoExisteException("Pagamento não existe: " + idPagamento);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public void atualizar(Pagamento pagamentoAtualizado) throws PagamentoNaoExisteException {
        boolean pagamentoAntigo = pagamentos.removeIf(pagamento -> pagamento.getIdPagamento() == pagamentoAtualizado.getIdPagamento());
        if (!pagamentoAntigo) {
            throw new PagamentoNaoExisteException("Pagamento não existe: " + pagamentoAtualizado.getIdPagamento());
        }
        pagamentos.add(pagamentoAtualizado);
        atualizarJson(pagamentos);
        System.out.println("Pagamento atualizado!");
    }

    @Override
    public void deletar(Object idPagamento) throws PagamentoNaoExisteException {
        boolean pagamentoAntigo = pagamentos.removeIf(pagamento -> pagamento.getIdPagamento() == (Integer) idPagamento);
        if (!pagamentoAntigo) {
            throw new PagamentoNaoExisteException("Pagamento não existe: " + idPagamento);
        }
        atualizarJson(pagamentos);
        System.out.println("Pagamento excluído!");
    }

    private void atualizarJson(List<Pagamento> pagamentosAtualizado) {
        atualizarArquivo(json, pagamentosAtualizado);
    }

    public List<Pagamento> pagamentosCadastrados() {
        String arquivo = json;

        if (this.isVazio(arquivo, Pagamento.class)) {
            pagamentos = new ArrayList<>();
        } else {
            pagamentos = this.getConteudo(arquivo, Pagamento.class);
        }
        return pagamentos;
    }

    public Integer[] listaPagamentosCadastrados() {
        List<Pagamento> pagamentosCadastrados = pagamentosCadastrados();
        Integer[] pagamentos = new Integer[pagamentosCadastrados.size()];
        for (int i = 0; i < pagamentosCadastrados.size(); i++) {
            pagamentos[i] = pagamentosCadastrados.get(i).getIdPagamento();
        }
        return pagamentos;
    }

    public String[] atributosPagamentosCadastrados() {
        return new String[]{"ID Pagamento", "ID Locacao", "Valor pago", "Data de pagamento"};
    }

    public Object[][] dadosPagamentosCadastrados() {
        List<Pagamento> pagamentos = pagamentosCadastrados();
        int linhas = pagamentos.size();
        int colunas = 4;
        Object[][] dadosPagamentos = new Object[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            Object[] dados = new Object[colunas];
            dados[0] = pagamentos.get(i).getIdPagamento();
            dados[1] = pagamentos.get(i).getIdLocacao();
            dados[2] = pagamentos.get(i).getValorPago();
            dados[3] = pagamentos.get(i).getMetodoPagamento();
            dadosPagamentos[i] = dados;
        }
        return dadosPagamentos;
    }
}
