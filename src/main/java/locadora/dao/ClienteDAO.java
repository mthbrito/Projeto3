package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Cliente;
import locadora.model.Veiculo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IPersistencia<Cliente> {

    private final List<Cliente> clientes;

    public ClienteDAO() {
        clientes = new ArrayList<>();
    }

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
        atualizarJson(clientes);
    }

    @Override
    public Cliente ler(Cliente cliente) {
        for (Cliente clienteListado: clientes) {
            if (clienteListado.getCpf().equals(cliente.getCpf())) {
                return clienteListado;
            }
        }
        return null;
    }

    @Override
    public void deletar(Cliente cliente) {
        List<Cliente> paraRemover = new ArrayList<>();
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(cliente.getCpf())) {
                paraRemover.add(clienteListado);
            }
        }
        clientes.removeAll(paraRemover);
        atualizarJson(clientes);
    }

    private void atualizarJson(List<Cliente> clientesAtualizado) {
        String clientesAtualizadoJson = new Gson().toJson(clientesAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/clientes.json")) {
            writer.write(clientesAtualizadoJson);
            System.out.println("adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
