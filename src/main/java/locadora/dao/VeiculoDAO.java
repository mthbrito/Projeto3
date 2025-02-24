package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.model.StatusVeiculo;
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

public class VeiculoDAO implements IPersistencia<Veiculo, Object> {

    private final List<Veiculo> veiculos;

    public VeiculoDAO() {
        veiculos = veiculoCadastrados();
    }

    @Override
    public void salvar(Veiculo veiculoNovo) {
        for(Veiculo veiculoListado : veiculos) {
            if(veiculoListado.getPlaca().equals(veiculoNovo.getPlaca())){
                System.out.println("Veículo já existe!");
                return;
            }
        }
        veiculos.add(veiculoNovo);
        atualizarJson(veiculos);
        System.out.println("Veículo salvo!");
    }

    @Override
    public Veiculo ler(Object placa) {
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(placa)) {
                return veiculoListado;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Veiculo veiculoAtualizado) {
        List<Veiculo> veiculosParaRemover =  new ArrayList<>();
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(veiculoAtualizado.getPlaca())) {
                veiculosParaRemover.add(veiculoListado);
            }
        }
        veiculos.removeAll(veiculosParaRemover);
        veiculos.add(veiculoAtualizado);
        atualizarJson(veiculos);
        System.out.println("Veículo atualizado!");
    }

    @Override
    public void deletar(Object placa) {
        List<Veiculo> veiculosParaRemover =  new ArrayList<>();
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(placa)) {
                veiculosParaRemover.add(veiculoListado);
            }
        }
        veiculos.removeAll(veiculosParaRemover);
        atualizarJson(veiculos);
        System.out.println("Veículo excluído!");
    }

    private boolean isVazio(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(Veiculo.class).getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(Veiculo.class, new VeiculoDeserializer()).create();
            Veiculo[] conteudo = gson.fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
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

    private List<Veiculo> veiculoCadastrados() {
        String arquivo = "src/main/java/locadora/json/veiculos.json";
        List<Veiculo> veiculos;
        if (this.isVazio(arquivo)) {
            veiculos = new ArrayList<>();
        } else {
            veiculos = this.getConteudo(arquivo);
        }
        return veiculos;
    }

    private void atualizarJson(List<Veiculo> veiculosAtualizado) {
        String veiculosAtualizadoJson = new Gson().toJson(veiculosAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/veiculos.json")) {
            writer.write(veiculosAtualizadoJson);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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
