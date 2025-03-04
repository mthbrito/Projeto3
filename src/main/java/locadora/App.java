package locadora;

import locadora.controller.LocacaoController;
import locadora.dao.ClienteDAO;
import locadora.model.Cliente;
import locadora.utils.DataHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

import static locadora.utils.DataHandler.converterDataInserida;

public class App {
    public static void main(String[] args) throws IOException {

//        System.out.println(new LocacaoController().isDataDevolucaoValida("29/02/2024"));
//
//        ClienteDAO cd = new ClienteDAO();
//        List<Object> dados = new ArrayList<>();
//        List<List<Object>> dadosClientes = new ArrayList<>();
//        cd.clientesCadastrados().forEach(cliente -> {
//            dados.add(cliente.getNome());
//            dados.add(cliente.getCpf());
//            dados.add(cliente.getTelefone());
//            dados.add(cliente.getEmail());
//            dadosClientes.add(dados);
//        });
//
//        System.out.println(dadosClientes);

        ClienteDAO clienteDAO = new ClienteDAO();
//        List<Cliente> clientes = clienteDAO.clientesCadastrados();
//        int linhas = clientes.size();
//        int colunas = 4;
//        String[][] dadosClientes = new String[linhas][colunas];
//        for (int i = 0; i < linhas; i++) {
//            String[] dados = new String[4];
//            dados[0] = clientes.get(i).getNome();
//            dados[1] = clientes.get(i).getCpf();
//            dados[2] = clientes.get(i).getTelefone();
//            dados[3] = clientes.get(i).getEmail();
//            dadosClientes[i] = dados;
//        }
//        String[] nomesColunas = {"Nome", "CPF", "Telefone", "Email"};
//        JTable tblClientes = new JTable(dadosClientes, nomesColunas);


    }
}
