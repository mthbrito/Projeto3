package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import locadora.model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VeiculoDAO implements IPersistencia<Veiculo>{

    private final List<Veiculo> veiculos;

    public VeiculoDAO() {
        veiculos = veiculoCadastrados();
    }

    @Override
    public void salvar(Veiculo veiculo) {
        veiculos.add(veiculo);
        atualizarJson(veiculos);
    }

    @Override
    public Veiculo ler(Veiculo veiculo) {
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(veiculo.getPlaca())) {
                return veiculoListado;
            }
        }
        return null;
    }

    @Override
    public void deletar(Veiculo veiculo) {
        List<Veiculo> paraRemover = new ArrayList<>();
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(veiculo.getPlaca())) {
                paraRemover.add(veiculoListado);
            }
        }
        veiculos.removeAll(paraRemover);
        atualizarJson(veiculos);
    }

    private boolean isVazio(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> conteudo = new Gson().fromJson(reader, type);
            return conteudo == null || conteudo.isEmpty();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Map<String, Object>> getConteudo(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Veiculo> veiculoCadastrados() {
        String arquivo = "src/main/java/locadora/json/veiculos.json";
        List<Veiculo> ListaVeiculos;
        if (this.isVazio(arquivo)) {
            ListaVeiculos = new ArrayList<>();
        } else {
            List<Map<String, Object>> dadosVeiculos = this.getConteudo(arquivo);
            ListaVeiculos = new ArrayList<>();
            for (Map<String, Object> dadoVeiculo : dadosVeiculos) {
                Object tipo = dadoVeiculo.get("tipo");
                String placa = (String) dadoVeiculo.get("placa");
                String modelo = (String) dadoVeiculo.get("modelo");
                int ano = ((Number) dadoVeiculo.get("ano")).intValue();
                String status = (String) dadoVeiculo.get("status");
                if (tipo.equals("Caminhao")) {
                    Veiculo veiculo = new Caminhao(placa, modelo, ano, status);
                    ListaVeiculos.add(veiculo);
                } else if (tipo.equals("Carro")) {
                    Veiculo veiculo = new Carro(placa, modelo, ano, status);
                    ListaVeiculos.add(veiculo);
                } else if (tipo.equals("Moto")) {
                    Veiculo veiculo = new Moto(placa, modelo, ano, status);
                    ListaVeiculos.add(veiculo);
                }
            }
        }
        return ListaVeiculos;
    }

    protected void atualizarJson(List<Veiculo> veiculosAtualizado){
        String veiculosAtualizadoJson = new Gson().toJson(veiculosAtualizado);
        try(FileWriter writer = new FileWriter("src/main/java/locadora/json/veiculos.json")) {
            writer.write(veiculosAtualizadoJson);
            System.out.println("Veículo adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }





}
