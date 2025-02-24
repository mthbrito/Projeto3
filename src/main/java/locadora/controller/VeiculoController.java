package locadora.controller;

import locadora.dao.VeiculoDAO;
import locadora.model.*;

import javax.swing.*;

public class VeiculoController {

    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrarVeiculo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto, JTextField txtPlaca, JTextField txtModelo, JTextField txtAno, JComboBox<StatusVeiculo> comboBoxStatus) {
        Veiculo veiculo = null;
        StatusVeiculo status = (StatusVeiculo) comboBoxStatus.getSelectedItem();
        if(rdbtnCaminhao.isSelected()) {
            veiculo = new Caminhao(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else if(rdbtnCarro.isSelected()) {
            veiculo = new Carro(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else if(rdbtnMoto.isSelected()) {
            veiculo = new Moto(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else {
            System.out.println("Selecione o tipo de veículo!");
            return;
        }
        veiculoDAO.salvar(veiculo);
    }

    public void editarVeiculo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto, JTextField txtPlaca, JTextField txtModelo, JTextField txtAno, JComboBox<StatusVeiculo> comboBoxStatus) {
        Veiculo veiculo = null;
        StatusVeiculo status = (StatusVeiculo) comboBoxStatus.getSelectedItem();
        if(rdbtnCaminhao.isSelected()) {
            veiculo = new Caminhao(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else if(rdbtnCarro.isSelected()) {
            veiculo = new Carro(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else if(rdbtnMoto.isSelected()) {
            veiculo = new Moto(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), status);
        } else {
            System.out.println("Selecione o tipo de veículo!");
            return;
        }
        veiculoDAO.atualizar(veiculo);
    }

    public void excluirVeiculo(JTextField txtPlaca) {
        veiculoDAO.deletar(txtPlaca.getText());
    }
}
