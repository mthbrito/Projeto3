package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.exception.VeiculoJaExisteException;
import locadora.exception.VeiculoNaoExisteException;
import locadora.model.StatusVeiculo;
import locadora.model.Veiculo;
import locadora.utils.JsonHandler;
import locadora.utils.VeiculoDeserializer;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VeiculoDAO extends JsonHandler implements IPersistencia<Veiculo, Object> {

    private List<Veiculo> veiculos;

    public VeiculoDAO() {
        this.veiculos = veiculosCadastrados();
    }

    @Override
    public void salvar(Veiculo veiculoNovo) throws VeiculoJaExisteException {
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(veiculoNovo.getPlaca())) {
                throw new VeiculoJaExisteException("Veiculo já existe: " + veiculoNovo.getPlaca());
            }
        }
        veiculos.add(veiculoNovo);
        atualizarJson(veiculos);
        System.out.println("Veículo salvo!");
    }

    @Override
    public Veiculo ler(Object placa) throws VeiculoNaoExisteException {
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(placa)) {
                return veiculoListado;
            }
        }
        throw new VeiculoNaoExisteException("Veículo não existe: " + placa);
    }

    @Override
    public void atualizar(Veiculo veiculoAtualizado) throws VeiculoNaoExisteException {
        boolean veiculoAntigo = veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(veiculoAtualizado.getPlaca()));
        if (!veiculoAntigo) {
            throw new VeiculoNaoExisteException("Veículo não existe: " + veiculoAtualizado.getPlaca());
        }
        veiculos.add(veiculoAtualizado);
        atualizarJson(veiculos);
        System.out.println("Veículo atualizado!");
    }

    @Override
    public void deletar(Object placa) throws VeiculoNaoExisteException {
        boolean veiculoAntigo = veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
        if (!veiculoAntigo) {
            throw new VeiculoNaoExisteException("Veículo não existe: " + placa);
        }
        atualizarJson(veiculos);
        System.out.println("Veículo excluído!");
    }

    private void atualizarJson(List<Veiculo> veiculosAtualizado) {
        atualizarArquivo("src/main/java/locadora/json/veiculos.json", veiculosAtualizado);
    }

    private boolean isVazio(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Veiculo.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Veiculo[] conteudo = gson.fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler json: " + arquivo);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private List<Veiculo> getConteudo(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Veiculo.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Veiculo[] conteudo = gson.fromJson(reader, type);
            return new ArrayList<>(Arrays.asList(conteudo));
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public List<Veiculo> veiculosCadastrados() {
        String arquivo = "src/main/java/locadora/json/veiculos.json";
        if (this.isVazio(arquivo)) {
            veiculos = new ArrayList<>();
        } else {
            veiculos = this.getConteudo(arquivo);
        }
        return veiculos;
    }

    public String[] atributosVeiculosCadastrados() {
        return new String[]{"Tipo", "Placa", "Modelo", "Ano", "Status"};
    }

    public String[] listaVeiculosCadastrados() {
        List<Veiculo> veiculosCadastrados = veiculosCadastrados();
        String[] veiculos = new String[veiculosCadastrados.size()];
        for (int i = 0; i < veiculosCadastrados.size(); i++) {
            veiculos[i] = veiculosCadastrados.get(i).getPlaca();
        }
        return veiculos;
    }

    public String[] listaVeiculosDisponiveis() {
        List<Veiculo> veiculosCadastrados = veiculosCadastrados();
        List<Veiculo> veiculosDisponiveis = new ArrayList<>();
        veiculosCadastrados.forEach(veiculo -> {
            if (veiculo.getStatus().equals(StatusVeiculo.DISPONIVEL)) {
                veiculosDisponiveis.add(veiculo);
            }
        });
        String[] veiculos = new String[veiculosDisponiveis.size()];
        for (int i = 0; i < veiculosDisponiveis.size(); i++) {
            veiculos[i] = veiculosDisponiveis.get(i).getPlaca();
        }
        return veiculos;
    }

    public Object[][] dadosVeiculosCadastrados() {
        List<Veiculo> veiculos = veiculosCadastrados();
        int linhas = veiculos.size();
        int colunas = 5;
        Object[][] dadosVeiculos = new Object[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            Object[] dados = new Object[colunas];
            dados[0] = veiculos.get(i).getTipo();
            dados[1] = veiculos.get(i).getPlaca();
            dados[2] = veiculos.get(i).getModelo();
            dados[3] = veiculos.get(i).getAno();
            dados[4] = veiculos.get(i).getStatus();
            dadosVeiculos[i] = dados;
        }
        return dadosVeiculos;
    }
}
