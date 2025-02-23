package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.model.Locacao;
import locadora.model.Veiculo;
import locadora.utils.VeiculoDeserializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LocacaoDAO implements IPersistencia<Locacao> {

    private final List<Locacao> locacoes;

    public LocacaoDAO() {
        this.locacoes = locacoesCadastradas();
    }

    @Override
    public void salvar(Locacao locacao) {
        locacoes.add(locacao);
        atualizarJson(locacoes);
    }

    @Override
    public Locacao ler(Object idLocacao) {
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getCliente().equals(idLocacao)) {
                return locacaoListada;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Locacao Objeto) {

    }

    @Override
    public void deletar(Object idLocacao) {
        List<Locacao> paraRemover = new ArrayList<>();
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getCliente().equals(idLocacao)) {
                paraRemover.add(locacaoListada);
            }
        }
        locacoes.removeAll(paraRemover);
        atualizarJson(locacoes);
    }

    private boolean isVazio(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Locacao.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Locacao[] conteudo = gson.fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NullPointerException e) {
            return false;
        }
    }

    private List<Locacao> getConteudo(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Locacao.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Locacao[] conteudo = gson.fromJson(reader, type);
            return new ArrayList<>(Arrays.asList(conteudo));
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }
    }

    private List<Locacao> locacoesCadastradas() {
        String arquivo = "src/main/java/locadora/json/locacoes.json";
        List<Locacao> locacoes;
        if (this.isVazio(arquivo)) {
            locacoes = new ArrayList<>();
        } else {
            locacoes = this.getConteudo(arquivo);
        }
        return locacoes;
    }

    private void atualizarJson(List<Locacao> locacoesAtualizado) {
        String locacoesAtualizadoJson = new Gson().toJson(locacoesAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/locacoes.json")) {
            writer.write(locacoesAtualizadoJson);
            System.out.println("Locação adicionada");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String[] listagemLocacoesCadastradas() {
        List<Locacao> locacoes = this.locacoesCadastradas();
        locacoes.sort(Comparator.comparing(Locacao::getIdLocacao));
        String[] idLocacoes = new String[locacoes.size()];
        for (int i = 0; i < locacoes.size(); i++) {
            idLocacoes[i] = locacoes.get(i).getIdLocacao() + "/"
                    + locacoes.get(i).getCliente().getNome() + "/"
                    + locacoes.get(i).getVeiculo().getModelo() + "/"
                    + locacoes.get(i).getDataDeRetirada() + "/"
                    + locacoes.get(i).getDataDeRetirada();
        }
        return idLocacoes;
    }
}
