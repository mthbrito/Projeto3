package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.exception.LocacaoJaExisteException;
import locadora.exception.LocacaoNaoExisteException;
import locadora.model.Locacao;
import locadora.model.Pagamento;
import locadora.model.Veiculo;
import locadora.utils.JsonHandler;
import locadora.utils.VeiculoDeserializer;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocacaoDAO extends JsonHandler implements IPersistencia<Locacao, Object> {

    private List<Locacao> locacoes;

    public LocacaoDAO() {
        this.locacoes = locacoesCadastradas();
    }

    @Override
    public void salvar(Locacao locacaoNova) throws LocacaoJaExisteException {
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getIdLocacao() == locacaoNova.getIdLocacao()) {
                throw new LocacaoJaExisteException("Locação já existe: " + locacaoNova.getIdLocacao());
            }
        }
        locacoes.add(locacaoNova);
        atualizarJson(locacoes);
        System.out.println("Locação salva!");
    }

    @Override
    public Locacao ler(Object idLocacao) throws LocacaoNaoExisteException {
        for (Locacao locacaoListada : locacoes) {
            if (locacaoListada.getIdLocacao() == (Integer) idLocacao) {
                return locacaoListada;
            }
        }
        throw new LocacaoNaoExisteException("Locação não existe: " + idLocacao);
    }

    @Override
    public void atualizar(Locacao locacaoAtualizada) throws LocacaoNaoExisteException {
        boolean locacaoAntiga = locacoes.removeIf(locacao -> locacao.getIdLocacao() == locacaoAtualizada.getIdLocacao());
        if (!locacaoAntiga) {
            throw new LocacaoNaoExisteException("Locação não existe: " + locacaoAtualizada.getIdLocacao());
        }
        locacoes.add(locacaoAtualizada);
        atualizarJson(locacoes);
        System.out.println("Locação atualizada!");
    }

    @Override
    public void deletar(Object idLocacao) throws LocacaoNaoExisteException {
        boolean locacaoAntiga = locacoes.removeIf(locacao -> locacao.getIdLocacao() == (Integer) idLocacao);
        if (!locacaoAntiga) {
            throw new LocacaoNaoExisteException("Locação não existe: " + idLocacao);
        }
        atualizarJson(locacoes);
        System.out.println("Locação excluída!");
    }

    private void atualizarJson(List<Locacao> locacoesAtualizado) {
        atualizarArquivo("src/main/java/locadora/json/locacoes.json", locacoesAtualizado);
    }

    private boolean isVazio(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Locacao.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Locacao[] conteudo = gson.fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler json: " + arquivo);
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

    public List<Locacao> locacoesCadastradas() {
        String arquivo = "src/main/java/locadora/json/locacoes.json";
        if (this.isVazio(arquivo)) {
            locacoes = new ArrayList<>();
        } else {
            locacoes = this.getConteudo(arquivo);
        }
        return locacoes;
    }

    public String[] atributosLocacoesCadastradas() {
        return new String[]{"ID Locação", "Nome", "CPF", "Placa", "Status", "Data de retirada", "Data de devolução"};
    }

    public Integer[] listaLocacoesCadastradas() {
        List<Locacao> locacoesCadastradas = locacoesCadastradas();
        Integer[] locacoes = new Integer[locacoesCadastradas.size()];
        for (int i = 0; i < locacoesCadastradas.size(); i++) {
            locacoes[i] = locacoesCadastradas.get(i).getIdLocacao();
        }
        return locacoes;
    }

    public Object[][] dadosLocacoesCadastradas() {
        List<Locacao> locacoes = locacoesCadastradas();
        int linhas = locacoes.size();
        int colunas = 7;
        Object[][] dadosLocacoes = new Object[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            Object[] dados = new Object[colunas];
            dados[0] = locacoes.get(i).getIdLocacao();
            dados[1] = locacoes.get(i).getCliente().getNome();
            dados[2] = locacoes.get(i).getCliente().getCpf();
            dados[3] = locacoes.get(i).getVeiculo().getPlaca();
            dados[4] = locacoes.get(i).getVeiculo().getStatus();
            dados[5] = locacoes.get(i).getDataDeRetirada();
            dados[6] = locacoes.get(i).getDataDeDevolucao();
            dadosLocacoes[i] = dados;
        }
        return dadosLocacoes;
    }

//    public String[] listagemLocacoesCadastradas() {
//        List<Locacao> locacoes = this.locacoesCadastradas();
//        locacoes.sort(Comparator.comparing(Locacao::getIdLocacao));
//        String[] idLocacoes = new String[locacoes.size()];
//        for (int i = 0; i < locacoes.size(); i++) {
//            if (!isLocacaoPaga(locacoes.get(i).getIdLocacao())) {
//                idLocacoes[i] = locacoes.get(i).getIdLocacao() + "/"
//                        + locacoes.get(i).getCliente().getNome() + "/"
//                        + locacoes.get(i).getVeiculo().getModelo() + "/"
//                        + locacoes.get(i).getDataDeRetirada() + "/"
//                        + locacoes.get(i).getDataDeRetirada();
//            }
//        }
//        return idLocacoes;
//    }

    private boolean isLocacaoPaga(int idLocacao) {
        for (Pagamento pagamento : new PagamentoDAO().pagamentosCadastrados()) {
            if (pagamento.getIdLocacao() == idLocacao) {
                return true;
            }
        }
        return false;
    }


}
