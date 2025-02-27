package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Locacao;
import locadora.model.Pagamento;
import locadora.utils.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO extends JsonHandler implements IPersistencia<Pagamento, Object> {

    private List<Pagamento> pagamentos;

    public PagamentoDAO() {
        pagamentos = this.pagamentosCadastrados();
    }

    @Override
    public void salvar(Pagamento pagamentoNovo) {
        for(Pagamento pagamentoListado : pagamentos) {
            if(pagamentoListado.getIdPagamento() == pagamentoNovo.getIdPagamento()){
                System.out.println("Pagamento já existe!");
                return;
            }
        }
        pagamentos.add(pagamentoNovo);
        atualizarJson(pagamentos);
        System.out.println("Pagamento salvo!");
    }

    @Override
    public Pagamento ler(Object idPagamento) {
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == (Integer)idPagamento) {
                return pagamentoListado;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Pagamento pagamentoAtualizado) {
        List<Pagamento> pagamentosParaRemover = new ArrayList<>();
        for(Pagamento pagamentoListado : pagamentos) {
            if(pagamentoListado.getIdPagamento() == pagamentoAtualizado.getIdPagamento()){
                pagamentosParaRemover.add(pagamentoListado);
            }
        }
        pagamentos.removeAll(pagamentosParaRemover);
        pagamentos.add(pagamentoAtualizado);
        atualizarJson(pagamentos);
        System.out.println("Pagamento atualizado!");
    }

    @Override
    public void deletar(Object idPagamento) {
        List<Pagamento> pagamentosParaRemover = new ArrayList<>();
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == (Integer)idPagamento) {
                pagamentosParaRemover.add(pagamentoListado);
            }
        }
        pagamentos.removeAll(pagamentosParaRemover);
        atualizarJson(pagamentos);
        System.out.println("Pagamento excluído!");
    }

    protected List<Pagamento> pagamentosCadastrados() {
        String arquivo = "src/main/java/locadora/json/pagamentos.json";
        if (this.isVazio(arquivo, Pagamento.class)) {
            pagamentos = new ArrayList<>();
        } else {
            pagamentos = this.getConteudo(arquivo, Pagamento.class);
        }
        return pagamentos;
    }

    private void atualizarJson(List<Pagamento> pagamentosAtualizado) {
        String pagamentosAtualizadoJson = new Gson().toJson(pagamentosAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/pagamentos.json")) {
            writer.write(pagamentosAtualizadoJson);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
