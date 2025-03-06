package locadora.controller;

import locadora.dao.VeiculoDAO;
import locadora.exception.VeiculoJaExisteException;
import locadora.exception.VeiculoNaoExisteException;
import locadora.model.*;

import javax.swing.*;

public class VeiculoController {

    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrarVeiculo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto, JTextField txtPlaca, JTextField txtModelo, JTextField txtAno, JComboBox<StatusVeiculo> comboBoxStatus) {
        String tipo = getTipo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto);
        String placa = txtPlaca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String ano = txtAno.getText().trim();
        StatusVeiculo status = (StatusVeiculo) comboBoxStatus.getSelectedItem();

        if (isEntradasValidas(tipo, placa, modelo, ano, status)) {
            salvarVeiculo(tipo, placa, modelo, ano, status);
        }
    }

    public void editarVeiculo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto, JTextField txtPlaca, JTextField txtModelo, JTextField txtAno, JComboBox<StatusVeiculo> comboBoxStatus) {
        String tipo = getTipo(rdbtnCaminhao, rdbtnCarro, rdbtnMoto);
        String placa = txtPlaca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String ano = txtAno.getText().trim();
        StatusVeiculo status = (StatusVeiculo) comboBoxStatus.getSelectedItem();

        if (isEntradasValidas(tipo, placa, modelo, ano, status)) {
            atualizarVeiculo(tipo, placa, modelo, ano, status);
        }
    }

    public void excluirVeiculo(JTextField txtPlaca) {
        String placa = txtPlaca.getText().trim();

        if (isPlacaValida(placa)) {
            deletarVeiculo(placa);
        }
    }

    private String getTipo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto) {
        if (rdbtnCaminhao.isSelected()) return "Caminhão";
        if (rdbtnCarro.isSelected()) return "Carro";
        if (rdbtnMoto.isSelected()) return "Moto";
        return "";
    }

    private Veiculo getVeiculo(String tipo, String placa, String modelo, String ano, StatusVeiculo status) {
        Veiculo veiculo = null;
        switch (tipo) {
            case "Caminhão":
                veiculo = new Caminhao(placa, modelo, Integer.parseInt(ano), status);
                break;
            case "Carro":
                veiculo = new Carro(placa, modelo, Integer.parseInt(ano), status);
                break;
            case "Moto":
                veiculo = new Moto(placa, modelo, Integer.parseInt(ano), status);
                break;
        }
        return veiculo;
    }

    private boolean isTipoValido(String tipo) {
        return !tipo.isEmpty();
    }

    private boolean isPlacaValida(String placa) {
        if (placa != null && !placa.isEmpty()) {
            String regex = "^[a-zA-Z]{3}[0-9]{4}$";
            return placa.matches(regex);
        }
        return false;
    }

    private boolean isModeloValido(String modelo) {
        if (modelo != null && !modelo.isEmpty()) {
            String regex = "^[a-zA-Z0-9]{3,10}$";
            return modelo.matches(regex);
        }
        return false;
    }

    private boolean isAnoValido(String ano) {
        if (ano != null && !ano.isEmpty()) {
            String regex = "^(19[0-9][0-9]|20[0-2][0-9])$";
            return ano.matches(regex);
        }
        return false;
    }

    private boolean isStatusValido(StatusVeiculo status) {
        return status != null;
    }

    private boolean isEntradasValidas(String tipo, String placa, String modelo, String ano, StatusVeiculo status) {
        StringBuilder erros = new StringBuilder();
        if (!isTipoValido(tipo)) erros.append("- Tipo inválido!\n");
        if (!isPlacaValida(placa)) erros.append("- Placa inválida!\n");
        if (!isModeloValido(modelo)) erros.append("- Modelo inválido!\n");
        if (!isAnoValido(ano)) erros.append("- Ano inválido!\n");
        if (!isStatusValido(status)) erros.append("- Status inválido!\n");

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(null, "Erros encontrados:\n" + erros, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void salvarVeiculo(String tipo, String placa, String modelo, String ano, StatusVeiculo status) {
        try {
            Veiculo veiculo = getVeiculo(tipo, placa, modelo, ano, status);
            veiculoDAO.salvar(veiculo);
            JOptionPane.showMessageDialog(null, "Veículo salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (VeiculoJaExisteException e) {
            JOptionPane.showMessageDialog(null, "Veículo já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Veiculo lerVeiculo(String placa) {
        try {
            Veiculo veiculo = veiculoDAO.ler(placa);
            JOptionPane.showMessageDialog(null, "Veículo encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return veiculo;
        } catch (VeiculoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Veículo não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void atualizarVeiculo(String tipo, String placa, String modelo, String ano, StatusVeiculo status) {
        try {
            Veiculo veiculo = getVeiculo(tipo, placa, modelo, ano, status);
            veiculoDAO.atualizar(veiculo);
            JOptionPane.showMessageDialog(null, "Veículo atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (VeiculoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Veículo não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarVeiculo(String placa) {
        try {
            veiculoDAO.deletar(placa);
            JOptionPane.showMessageDialog(null, "Veículo excluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (VeiculoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Veículo não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
