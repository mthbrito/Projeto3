package locadora.controller;

import locadora.dao.ClienteDAO;
import locadora.model.Cliente;

import javax.swing.*;

public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        System.out.println((validarNome(nome)));
        System.out.println(validarCpf(cpf));
        System.out.println(validarTelefone(telefone));
        System.out.println(validarEmail(email));
        if (validarNome(nome) && validarCpf(cpf) && validarTelefone(telefone) && validarEmail(email)) {
            Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), txtEmail.getText());
            clienteDAO.salvar(cliente);
        } else {
            JOptionPane.showMessageDialog(null, "Certifique-se de não haver valores vazios e/ou de erro no formato de entrada!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void editarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), txtEmail.getText());
        clienteDAO.atualizar(cliente);
    }

    public void excluirCliente(JTextField txtCpf) {
        clienteDAO.deletar(txtCpf.getText());
    }

    public boolean validarNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            String regex = "^[\\p{L}\\s]+$";
            return nome.matches(regex);
        }
        return false;
    }

    public boolean validarCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            //Formato "000.000.000-00" -> \d{3}\.\d{3}\.\d{3}-\d{2}
            //Formato "000000000-00" -> \d{9}-\d{2}
            //Formato "00000000000" -> \d{11}
            String regex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{9}-\\d{2}|\\d{11})$";
            return cpf.matches(regex);
        }
        return false;
    }

    public boolean validarTelefone(String telefone) {
        if (telefone != null && !telefone.isEmpty()) {
            //Formato "(00)00000-0000" -> \(\d{2}\)\d{5}-\d{4}
            //Formato "(00)000000000" -> \(\d{2}\)\d{5}\d{4}
            //Formato "00000000000 -> \d{11}
            String regex = "^(\\(\\d{2}\\)\\d{5}-\\d{4}|\\(\\d{2}\\)\\d{5}\\d{4}|\\d{11})$";
            return telefone.matches(regex);
        }
        return false;
    }

    public boolean validarEmail(String email) {
        if (email != null && !email.isEmpty()) {
            String regex = "^[a-zA-Z][a-zA-Z0-9._-]{2,}@[a-zA-Z]{3,}\\.[a-zA-Z]{2,3}$";
            return email.matches(regex);
        }
        return false;
    }
}
