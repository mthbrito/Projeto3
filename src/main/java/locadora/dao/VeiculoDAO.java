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
    public void salvar(Veiculo veiculo) {
        for(Veiculo veiculoListado : veiculos) {
            if(veiculoListado.getPlaca().equals(veiculo.getPlaca())){
                System.out.println("Veículo já existe!");
                return;
            }
        }
        veiculos.add(veiculo);
        atualizarJson(veiculos);
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
    public void atualizar(Veiculo Objeto) {

    }

    @Override
    public void deletar(Object identificador) {

    }

    @Override
    public void deletar(Veiculo veiculo) {
        for (Veiculo veiculoListado : veiculos) {
            if (veiculoListado.getPlaca().equals(veiculo.getPlaca())) {
                veiculos.remove(veiculoListado);
            }
        }
        atualizarJson(veiculos);
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


//    private boolean isVazio(String arquivo) {
//        try (FileReader reader = new FileReader(arquivo)) {
//            Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
//            List<Map<String, Object>> conteudo = new Gson().fromJson(reader, type);
//            return conteudo == null || conteudo.isEmpty();
//        } catch (IOException | NullPointerException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    private List<Map<String, Object>> getConteudo(String arquivo) {
//        try (FileReader reader = new FileReader(arquivo)) {
//            Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
//            return new Gson().fromJson(reader, type);
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    private List<Veiculo> veiculoCadastrados() {
//        String arquivo = "src/main/java/locadora/json/veiculos.json";
//        List<Veiculo> ListaVeiculos;
//        if (this.isVazio(arquivo)) {
//            ListaVeiculos = new ArrayList<>();
//        } else {
//            List<Map<String, Object>> dadosVeiculos = this.getConteudo(arquivo);
//            ListaVeiculos = new ArrayList<>();
//            for (Map<String, Object> dadoVeiculo : dadosVeiculos) {
//                Object tipo = dadoVeiculo.get("tipo");
//                String placa = (String) dadoVeiculo.get("placa");
//                String modelo = (String) dadoVeiculo.get("modelo");
//                int ano = ((Number) dadoVeiculo.get("ano")).intValue();
//                String status = (String) dadoVeiculo.get("status");
//                if (tipo.equals("Caminhao")) {
//                    Veiculo veiculo = new Caminhao(placa, modelo, ano, status);
//                    ListaVeiculos.add(veiculo);
//                } else if (tipo.equals("Carro")) {
//                    Veiculo veiculo = new Carro(placa, modelo, ano, status);
//                    ListaVeiculos.add(veiculo);
//                } else if (tipo.equals("Moto")) {
//                    Veiculo veiculo = new Moto(placa, modelo, ano, status);
//                    ListaVeiculos.add(veiculo);
//                }
//            }
//        }
//        return ListaVeiculos;


    private void atualizarJson(List<Veiculo> veiculosAtualizado) {
        String veiculosAtualizadoJson = new Gson().toJson(veiculosAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/veiculos.json")) {
            writer.write(veiculosAtualizadoJson);
            System.out.println("Veículo adicionado");
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
