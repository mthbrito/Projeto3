package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import locadora.model.Veiculo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements IPersistencia<Veiculo>{

    private final List<Veiculo> veiculos;

    public VeiculoDAO(){
        veiculos = new ArrayList<>();
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

    private void atualizarJson(List<Veiculo> veiculosAtualizado){
        String veiculosAtualizadoJson = new Gson().toJson(veiculosAtualizado);
        try(FileWriter writer = new FileWriter("src/main/java/locadora/dao/veiculos.json")) {
            writer.write(veiculosAtualizadoJson);
            System.out.println("adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
