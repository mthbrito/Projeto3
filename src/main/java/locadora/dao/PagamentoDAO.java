package locadora.dao;

import locadora.exception.PagamentoJaExisteException;
import locadora.exception.PagamentoNaoExisteException;
import locadora.model.Pagamento;
import locadora.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO extends JsonHandler implements IPersistencia<Pagamento, Object> {

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
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == (Integer) idPagamento) {
                return pagamentoListado;
            }
        }
        throw new PagamentoNaoExisteException("Pagamento não existe: " + idPagamento);
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
        atualizarArquivo("src/main/java/locadora/json/pagamentos.json", pagamentosAtualizado);
    }

    public List<Pagamento> pagamentosCadastrados() {
        String arquivo = "src/main/java/locadora/json/pagamentos.json";

        if (this.isVazio(arquivo, Pagamento.class)) {
            pagamentos = new ArrayList<>();
        } else {
            pagamentos = this.getConteudo(arquivo, Pagamento.class);
        }
        return pagamentos;
    }
}
