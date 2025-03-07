package locadora.dao;

import locadora.exception.ClienteJaExisteException;
import locadora.exception.ClienteNaoExisteException;
import locadora.model.Cliente;
import locadora.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends JsonHandler implements IPersistencia<Cliente, Object> {

    private final String json = getCaminhoArquivoJson("\\data\\json\\clientes.json");
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
        atualizarArquivo(json, clientesAtualizado);
    }

    public List<Cliente> clientesCadastrados() {
        String arquivo = json;
        if (this.isVazio(arquivo, Cliente.class)) {
            clientes = new ArrayList<>();
        } else {
            clientes = this.getConteudo(arquivo, Cliente.class);
        }
        return clientes;
    }

    public String[] listaClientesCadastrados() {
        List<Cliente> clientesCadastrados = clientesCadastrados();
        String[] clientes = new String[clientesCadastrados.size()];
        for (int i = 0; i < clientesCadastrados.size(); i++) {
            clientes[i] = clientesCadastrados.get(i).getCpf();
        }
        return clientes;
    }

    public String[] atributosClientesCadastrados() {
        return new String[]{"Nome", "CPF", "Telefone", "Email"};
    }

    public String[][] dadosClientesCadastrados() {
        List<Cliente> clientes = clientesCadastrados();
        int linhas = clientes.size();
        int colunas = 4;
        String[][] dadosClientes = new String[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            String[] dados = new String[4];
            dados[0] = clientes.get(i).getNome();
            dados[1] = clientes.get(i).getCpf();
            dados[2] = clientes.get(i).getTelefone();
            dados[3] = clientes.get(i).getEmail();
            dadosClientes[i] = dados;
        }
        return dadosClientes;
    }
}
