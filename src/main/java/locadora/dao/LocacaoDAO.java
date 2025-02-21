package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Locacao;
import locadora.model.Veiculo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO implements IPersistencia<Locacao>{

    private List<Locacao> locacoes;

    public LocacaoDAO() {
        locacoes = new ArrayList<>();
    }

    @Override
    public void salvar(Locacao locacao) {
        locacoes.add(locacao);
        atualizarJson(locacoes);
    }

    @Override
    public Locacao ler(Locacao locacao) {
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getCliente().equals(locacao.getCliente())) {
                return locacaoListada;
            }
        }
        return null;
    }

    @Override
    public void deletar(Locacao locacao) {
        List<Locacao> paraRemover = new ArrayList<>();
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getCliente().equals(locacao.getCliente())) {
                paraRemover.add(locacaoListada);
            }
        }
        locacoes.removeAll(paraRemover);
        atualizarJson(locacoes);
    }

    private void atualizarJson(List<Locacao> locacoesAtualizado) {
        String locacoesAtualizadoJson = new Gson().toJson(locacoesAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/dao/locacoes.json")) {
            writer.write(locacoesAtualizadoJson);
            System.out.println("adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
