package locadora.controller;

import locadora.dao.LocacaoDAO;
import locadora.model.*;

import javax.swing.*;

public class LocacaoController {

    private final LocacaoDAO locacaoDAO = new LocacaoDAO();

    public void registrarLocacao(JComboBox<String> comboBoxClientes, JComboBox<String> comboBoxVeiculos, JTextField txtDataRetirada, JTextField txtDataDevolucao) {
        String dadosClientes = (String) comboBoxClientes.getSelectedItem();
        Cliente cliente = null;
        if(dadosClientes != null) {
            String[] dadosCliente = dadosClientes.split("/");
            cliente = new Cliente(dadosCliente[0], dadosCliente[1], dadosCliente[2], dadosCliente[3]);
        }
        String dadosVeiculos = (String) comboBoxVeiculos.getSelectedItem();
        Veiculo veiculo = null;
        if(dadosVeiculos!= null) {
            String[] dadosVeiculo = dadosVeiculos.split("/");
            switch (dadosVeiculo[0]) {
                case "Caminhão":
                    veiculo = new Caminhao(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
                case "Carro":
                    veiculo = new Carro(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
                case "Moto":
                    veiculo = new Moto(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
            }
        }
        Locacao locacao = new Locacao(cliente, veiculo, txtDataRetirada.getText(), txtDataDevolucao.getText());
        locacaoDAO.salvar(locacao);
    }

    public void editarLocacao(JTextField txtIdLocacao, JComboBox<String> comboBoxClientes, JComboBox<String> comboBoxVeiculos, JTextField txtDataRetirada, JTextField txtDataDevolucao) {
        String dadosClientes = (String) comboBoxClientes.getSelectedItem();
        Cliente cliente = null;
        if(dadosClientes != null) {
            String[] dadosCliente = dadosClientes.split("/");
            cliente = new Cliente(dadosCliente[0], dadosCliente[1], dadosCliente[2], dadosCliente[3]);
        }
        String dadosVeiculos = (String) comboBoxVeiculos.getSelectedItem();
        Veiculo veiculo = null;
        if(dadosVeiculos!= null) {
            String[] dadosVeiculo = dadosVeiculos.split("/");
            switch (dadosVeiculo[0]) {
                case "Caminhão":
                    veiculo = new Caminhao(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
                case "Carro":
                    veiculo = new Carro(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
                case "Moto":
                    veiculo = new Moto(dadosVeiculo[1], dadosVeiculo[2], Integer.parseInt(dadosVeiculo[3]), StatusVeiculo.valueOf(dadosVeiculo[4].toUpperCase()));
                    break;
            }
        }
        Locacao locacao = new Locacao(Integer.parseInt(txtIdLocacao.getText()), cliente, veiculo, txtDataRetirada.getText(), txtDataDevolucao.getText());
        locacaoDAO.atualizar(locacao);
    }

    public void excluirLocacao(JTextField txtIdLocacao) {
        locacaoDAO.deletar(Integer.parseInt(txtIdLocacao.getText()));
    }
}
