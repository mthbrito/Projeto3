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
import java.util.Comparator;
import java.util.List;

public class VeiculoDAO extends JsonHandler implements IPersistencia<Veiculo, Object> {

    private List<Veiculo> veiculos;

    public VeiculoDAO() {
        this.veiculos = veiculoCadastrados();
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

    public List<Veiculo> veiculoCadastrados() {
        String arquivo = "src/main/java/locadora/json/veiculos.json";
        if (this.isVazio(arquivo)) {
            veiculos = new ArrayList<>();
        } else {
            veiculos = this.getConteudo(arquivo);
        }
        return veiculos;
    }

    public String[] listagemVeiculosDisponiveis() {
        List<Veiculo> veiculos = this.veiculoCadastrados();
        veiculos.sort(Comparator.comparing(Veiculo::getModelo));
        String[] idVeiculos = new String[veiculos.size()];
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getStatus() == StatusVeiculo.DISPONIVEL) {
                idVeiculos[i] = veiculos.get(i).getTipo() + "/"
                        + veiculos.get(i).getPlaca() + "/"
                        + veiculos.get(i).getModelo() + "/"
                        + veiculos.get(i).getAno() + "/"
                        + veiculos.get(i).getStatus();
            }
        }
        return idVeiculos;
    }
}
