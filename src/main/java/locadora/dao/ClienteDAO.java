package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Cliente;
import locadora.utils.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends JsonHandler implements IPersistencia<Cliente> {

    private List<Cliente> clientes;

    public ClienteDAO() {
        this.clientes = clientesCadastrados();
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

    private List<Cliente> clientesCadastrados() {
        String arquivo = "src/main/java/locadora/json/clientes.json";
        if(this.isVazio(arquivo, Cliente.class)) {
            clientes = new ArrayList<>();
        } else {
            clientes = this.getConteudo(arquivo, Cliente.class);
        }
        return clientes;
    }

    private void atualizarJson(List<Cliente> clientesAtualizado) {
        String clientesAtualizadoJson = new Gson().toJson(clientesAtualizado);
        try(FileWriter writer = new FileWriter("src/main/java/locadora/json/clientes.json")) {
            writer.write(clientesAtualizadoJson);
            System.out.println("Cliente adicionado");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
