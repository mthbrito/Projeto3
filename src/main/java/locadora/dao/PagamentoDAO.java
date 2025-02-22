package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Locacao;
import locadora.model.Pagamento;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO implements IPersistencia<Pagamento>{

    private List<Pagamento> pagamentos;

    public PagamentoDAO() {
        pagamentos = new ArrayList<>();
    }

    @Override
    public void salvar(Pagamento pagamento) {
        pagamentos.add(pagamento);
        atualizarJson(pagamentos);
    }

    @Override
    public Pagamento ler(Pagamento pagamento) {
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == pagamento.getIdPagamento()) {
                return pagamentoListado;
            }
        }
        return null;
    }

    @Override
    public void deletar(Pagamento pagamento) {
        List<Pagamento> paraRemover = new ArrayList<>();
        for (Pagamento pagamentoListado : pagamentos) {
            if (pagamentoListado.getIdPagamento() == pagamento.getIdPagamento()) {
                paraRemover.add(pagamentoListado);
            }
        }
        pagamentos.removeAll(paraRemover);
        atualizarJson(pagamentos);
    }

    private void atualizarJson(List<Pagamento> pagamentosAtualizado) {
        String pagamentosAtualizadoJson = new Gson().toJson(pagamentosAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/pagamentos.json")) {
            writer.write(pagamentosAtualizadoJson);
            System.out.println("adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
