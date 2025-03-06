package locadora;

import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.model.*;
import locadora.utils.DataHandler;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static locadora.utils.DataHandler.converterDataArmazenada;
import static locadora.utils.DataHandler.converterDataInserida;

public class App {
    public static void main(String[] args) throws IOException {


        Pagamento pagamento = new Pagamento(1005, 1010, 825.0, "02/03/2025", MetodosPagamento.A_VISTA);
        System.out.println(pagamento);
        Locacao locacao = new LocacaoDAO().ler(pagamento.getIdLocacao());
        System.out.println(locacao);
        Veiculo veiculo = locacao.getVeiculo();
        System.out.println(veiculo);

//        System.out.println(new PagamentoController().isDataPagamentoValida("1011", "25/03/2025"));
//        System.out.println(new LocacaoDAO().ler("1011").getDataDeRetirada());
//        System.out.println(converterDataInserida("25/03/2025"));
//        System.out.println(new PagamentoController().isIdLocacaoValido("1011"));
//        System.out.println(new PagamentoController().isDataPagamentoValida("1011","25/03/2025"));
//        System.out.println(new LocacaoDAO().ler("1011").getDataDeRetirada());
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
