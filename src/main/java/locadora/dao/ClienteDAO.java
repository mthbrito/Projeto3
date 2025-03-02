package locadora.dao;

import locadora.exception.ClienteJaExisteException;
import locadora.exception.ClienteNaoExisteException;
import locadora.model.Cliente;
import locadora.utils.JsonHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteDAO extends JsonHandler implements IPersistencia<Cliente, Object> {

    private List<Cliente> clientes;

    public ClienteDAO() {
        this.clientes = clientesCadastrados();
    }

    @Override
    public void salvar(Cliente clienteNovo) throws ClienteJaExisteException {
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(clienteNovo.getCpf())) {
                throw new ClienteJaExisteException("Cliente já existe: " + clienteNovo.getCpf());
            }
        }
        clientes.add(clienteNovo);
        atualizarJson(clientes);
        System.out.println("Cliente salvo!");
    }

    @Override
    public Cliente ler(Object cpf) throws ClienteNaoExisteException {
        for (Cliente clienteListado : clientes) {
            if (clienteListado.getCpf().equals(cpf)) {
                return clienteListado;
            }
        }
        throw new ClienteNaoExisteException("Cliente não existe: " + cpf);
    }

    @Override
    public void atualizar(Cliente clienteAtualizado) throws ClienteNaoExisteException {
        boolean clienteAntigo = clientes.removeIf(cliente -> cliente.getCpf().equals(clienteAtualizado.getCpf()));
        if (!clienteAntigo) {
            throw new ClienteNaoExisteException("Cliente não existe: " + clienteAtualizado.getCpf());
        }
        clientes.add(clienteAtualizado);
        atualizarJson(clientes);
        System.out.println("Cliente atualizado!");
    }

    @Override
    public void deletar(Object cpf) throws ClienteNaoExisteException {
        boolean clienteAntigo = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
        if (!clienteAntigo) {
            throw new ClienteNaoExisteException("Cliente não existe: " + cpf);
        }
        atualizarJson(clientes);
        System.out.println("Cliente excluído!");
    }

    private void atualizarJson(List<Cliente> clientesAtualizado) {
        atualizarArquivo("src/main/java/locadora/json/clientes.json", clientesAtualizado);
    }

    public List<Cliente> clientesCadastrados() {
        String arquivo = "src/main/java/locadora/json/clientes.json";
        if (this.isVazio(arquivo, Cliente.class)) {
            clientes = new ArrayList<>();
        } else {
            clientes = this.getConteudo(arquivo, Cliente.class);
        }
        return clientes;
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
