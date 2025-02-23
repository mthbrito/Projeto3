package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Pagamento;
import locadora.utils.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO extends JsonHandler implements IPersistencia<Pagamento> {

    private List<Pagamento> pagamentos;

    public PagamentoDAO() {
        pagamentos = this.pagamentosCadastrados();
    }

    @Override
    public void salvar(Pagamento pagamento) {
        pagamentos.add(pagamento);
        atualizarJson(pagamentos);
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
    public void atualizar(Pagamento Objeto) {

    }

    @Override
    public void deletar(Object idPagamento) {
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == (Integer)idPagamento) {
                pagamentos.remove(pagamentoListado);
            }
        }
        atualizarJson(pagamentos);
    }

    private List<Pagamento> pagamentosCadastrados() {
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
            System.out.println("Pagamento adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
