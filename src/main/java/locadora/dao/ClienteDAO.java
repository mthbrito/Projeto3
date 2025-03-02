package locadora.dao;

import com.google.gson.Gson;
import locadora.model.Cliente;
import locadora.utils.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteDAO extends JsonHandler implements IPersistencia<Cliente, Object> {

    private List<Cliente> clientes;

    public ClienteDAO() {
        this.clientes = clientesCadastrados();
    }

    @Override
    public void salvar(Cliente clienteNovo) {
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(clienteNovo.getCpf())) {
                System.out.println("Cliente já existe!");
                return;
            }
        }
        clientes.add(clienteNovo);
        atualizarJson(clientes);
        System.out.println("Cliente salvo!");
    }

    @Override
    public Cliente ler(Object cpf) {
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(cpf)) {
                return clienteListado;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Cliente clienteAtualizado) {
        List<Cliente> clientesParaRemover = new ArrayList<>();
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(clienteAtualizado.getCpf())) {
                clientesParaRemover.add(clienteListado);
            }
        }
        clientes.removeAll(clientesParaRemover);
        clientes.add(clienteAtualizado);
        atualizarJson(clientes);
        System.out.println("Cliente atualizado!");
    }

    @Override
    public void deletar(Object cpf) {
        List<Cliente> clientesParaRemover = new ArrayList<>();
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(cpf)) {
                clientesParaRemover.add(clienteListado);
            }
        }
        clientes.removeAll(clientesParaRemover);
        atualizarJson(clientes);
        System.out.println("Cliente excluído!");
    }

    private List<Cliente> clientesCadastrados() {
        String arquivo = "src/main/java/locadora/json/clientes.json";
        if (this.isVazio(arquivo, Cliente.class)) {
            clientes = new ArrayList<>();
        } else {
            clientes = this.getConteudo(arquivo, Cliente.class);
        }
        return clientes;
    }

    private void atualizarJson(List<Cliente> clientesAtualizado) {
        String clientesAtualizadoJson = new Gson().toJson(clientesAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/clientes.json")) {
            writer.write(clientesAtualizadoJson);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String[] listagemClientesCadastrados() {
        List<Cliente> clientes = this.clientesCadastrados();
        clientes.sort(Comparator.comparing(Cliente::getNome));
        String[] idClientes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            idClientes[i] = clientes.get(i).getNome() + "/"
                    + clientes.get(i).getCpf() + "/"
                    + clientes.get(i).getTelefone() + "/"
                    + clientes.get(i).getEmail();
        }
        return idClientes;
    }
}
