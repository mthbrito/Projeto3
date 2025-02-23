package locadora.controller;

import locadora.dao.VeiculoDAO;
import locadora.model.*;

import javax.swing.*;

public class VeiculoController {

    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void CadastrarVeiculo(JRadioButton rdbtnCaminhao, JRadioButton rdbtnCarro, JRadioButton rdbtnMoto, JTextField txtPlaca, JTextField txtModelo, JTextField txtAno, JTextField txtStatus) {
        Veiculo veiculo = null;
        if(rdbtnCaminhao.isSelected()) {
            veiculo = new Caminhao(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), txtStatus.getText());
        } else if(rdbtnCarro.isSelected()) {
            veiculo = new Carro(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), txtStatus.getText());
        } else if(rdbtnMoto.isSelected()) {
            veiculo = new Moto(txtPlaca.getText(), txtModelo.getText(), Integer.parseInt(txtAno.getText()), txtStatus.getText());
        }
        veiculoDAO.salvar(veiculo);
    }


}
