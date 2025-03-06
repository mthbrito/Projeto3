package locadora.controller;

import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.exception.ClienteNaoExisteException;
import locadora.exception.LocacaoJaExisteException;
import locadora.exception.LocacaoNaoExisteException;
import locadora.exception.VeiculoNaoExisteException;
import locadora.model.*;

import javax.swing.*;
import java.time.LocalDate;

import static locadora.utils.DataHandler.converterDataInserida;

public class LocacaoController {

    private final LocacaoDAO locacaoDAO = new LocacaoDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public static void resetarEntradas(JComboBox<Integer> comboBoxIdLocacoes, JComboBox<String> comboBoxClientes, JComboBox<String> comboBoxVeiculos, JTextField txtDataRetirada, JTextField txtDataDevolucao) {
        comboBoxIdLocacoes.setSelectedIndex(-1);
        comboBoxClientes.setSelectedIndex(-1);
        comboBoxVeiculos.setSelectedIndex(-1);
        txtDataRetirada.setText(null);
        txtDataDevolucao.setText(null);
    }

    public void registrarLocacao(JComboBox<String> comboBoxClientes, JComboBox<String> comboBoxVeiculos, JTextField txtDataRetirada, JTextField txtDataDevolucao) {
        Cliente cliente = getCliente(String.valueOf(comboBoxClientes.getSelectedItem()));
        Veiculo veiculo = getVeiculo(String.valueOf(comboBoxVeiculos.getSelectedItem()));
        String dataRetirada = txtDataRetirada.getText().trim();
        String dataDevolucao = txtDataDevolucao.getText().trim();

        if (isEntradasValidas(cliente, veiculo, dataRetirada, dataDevolucao)) {
            assert veiculo != null;
            salvarLocacao(cliente, veiculo, dataRetirada, dataDevolucao);
        }
    }

    public void editarLocacao(JComboBox<Integer> comboBoxlocacoes, JComboBox<String> comboBoxClientes, JComboBox<String> comboBoxVeiculos, JTextField txtDataRetirada, JTextField txtDataDevolucao) {
        String idLocacao = String.valueOf(comboBoxlocacoes.getSelectedItem());
        Cliente cliente = getCliente(String.valueOf(comboBoxClientes.getSelectedItem()));
        Veiculo veiculo = getVeiculo(String.valueOf(comboBoxVeiculos.getSelectedItem()));
        String dataRetirada = txtDataRetirada.getText().trim();
        String dataDevolucao = txtDataDevolucao.getText().trim();

        if (isIdLocacaoValida(idLocacao) && isEntradasValidas(cliente, veiculo, dataRetirada, dataDevolucao)) {
            atualizarLocacao(idLocacao, cliente, veiculo, dataRetirada, dataDevolucao);
        }
    }

    public void excluirLocacao(JComboBox<Integer> comboBoxIdLocacoes) {
        String idLocacao = String.valueOf(comboBoxIdLocacoes.getSelectedItem());

        if (isIdLocacaoValida(idLocacao)) {
            deletarLocacao(idLocacao);
        }
    }

    private Cliente getCliente(String cpf) {
        try {
            return clienteDAO.ler(cpf);
        } catch (ClienteNaoExisteException e) {
            return null;
        }
    }

    private Veiculo getVeiculo(String placa) {
        try {
            return veiculoDAO.ler(placa);
        } catch (VeiculoNaoExisteException e) {
            return null;
        }
    }

    private boolean isIdLocacaoValida(String idLocacao) {
        return idLocacao != null;
    }

    private boolean isClienteValido(Cliente cliente) {
        return cliente != null;
    }

    private boolean isVeiculoValido(Veiculo veiculo) {
        return veiculo != null;
    }

    private boolean isDataRetiradaValida(String dataRetirada) {
        if (dataRetirada != null && !dataRetirada.isEmpty()) {
            try {
                converterDataInserida(dataRetirada);
                return true;
            } catch (RuntimeException e) {
                return false;
            }
        }
        return false;
    }

    private boolean isDataDevolucaoValida(String dataRetirada, String dataDevolucao) {
        if (isDataRetiradaValida(dataRetirada) && dataDevolucao != null && !dataDevolucao.isEmpty()) {
            try {
                LocalDate retirada = converterDataInserida(dataRetirada);
                LocalDate devolucao = converterDataInserida(dataDevolucao);
                return !devolucao.isBefore(retirada);
            } catch (RuntimeException e) {
                return false;
            }
        }
        return false;
    }

    private boolean isEntradasValidas(Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao) {
        StringBuilder erros = new StringBuilder();
        if (!isClienteValido(cliente)) erros.append("- Cliente inválido!\n");
        if (!isVeiculoValido(veiculo)) erros.append("- Veículo inválido!\n");
        if (!isDataRetiradaValida(dataRetirada)) erros.append("- Data de retirada inválida!\n");
        if (!isDataDevolucaoValida(dataRetirada, dataDevolucao)) erros.append("- Data de devolução inválida!\n");

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(null, "Erros encontrados:\n" + erros, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void salvarLocacao(Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao) {
        try {
            veiculo.setStatus(StatusVeiculo.LOCADO);
            Locacao locacao = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao);
            locacaoDAO.salvar(locacao);
            veiculoDAO.atualizar(veiculo);
            JOptionPane.showMessageDialog(null, "Locação salva!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (LocacaoJaExisteException e) {
            JOptionPane.showMessageDialog(null, "Locação já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Locacao lerLocacao(String idLocacao) {
        try {
            Locacao locacao = locacaoDAO.ler(Integer.parseInt(idLocacao));
            JOptionPane.showMessageDialog(null, "Locação encontrada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return locacao;
        } catch (LocacaoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Locação não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void atualizarLocacao(String idLocacao, Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao) {
        try {
            Locacao locacaoAntiga = lerLocacao(idLocacao);
            Veiculo veiculoAntigo = locacaoAntiga.getVeiculo();
            if (veiculoAntigo.getStatus() == StatusVeiculo.LOCADO) {
                veiculoAntigo.setStatus(StatusVeiculo.DISPONIVEL);
                locacaoDAO.atualizar(locacaoAntiga);
                veiculoDAO.atualizar(veiculoAntigo);
            }
            veiculo.setStatus(StatusVeiculo.LOCADO);
            Locacao locacao = new Locacao(Integer.parseInt(idLocacao), cliente, veiculo, dataRetirada, dataDevolucao);
            locacaoDAO.atualizar(locacao);
            veiculoDAO.atualizar(veiculo);
            JOptionPane.showMessageDialog(null, "Locação atualizada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (LocacaoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Locação não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarLocacao(String idLocacao) {
        try {
            Locacao locacaoAntiga = lerLocacao(idLocacao);
            Veiculo veiculoAntigo = locacaoAntiga.getVeiculo();
            if (veiculoAntigo.getStatus() == StatusVeiculo.LOCADO) {
                veiculoAntigo.setStatus(StatusVeiculo.DISPONIVEL);
                veiculoDAO.atualizar(veiculoAntigo);
            }
            locacaoDAO.deletar(Integer.parseInt(idLocacao));
            JOptionPane.showMessageDialog(null, "Locação excluída!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (LocacaoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Locação não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Object[] getListaObjeto(JComboBox<TiposObjetos> comboBoxTiposObjetos) {
        TiposObjetos objeto = (TiposObjetos) comboBoxTiposObjetos.getSelectedItem();
        if (objeto != null) {
            switch (objeto) {
                case CLIENTE:
                    return clienteDAO.listaClientesCadastrados();
                case VEICULO:
                    return veiculoDAO.listaVeiculosCadastrados();
                case LOCACAO:
                    return locacaoDAO.listaLocacoesCadastradas();
                case PAGAMENTO:
                    return pagamentoDAO.listaPagamentosCadastrados();
                default:
                    return new Object[0];
            }
        }
        return new Object[0];
    }
}
