package locadora.controller;

import locadora.dao.ClienteDAO;
import locadora.exception.ClienteJaExisteException;
import locadora.exception.ClienteNaoExisteException;
import locadora.model.Cliente;

import javax.swing.*;

public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();

        if (isEntradasValidas(nome, cpf, telefone, email)) {
            salvarCliente(nome, cpf, telefone, email);
        }
    }

    public void editarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();

        if (isEntradasValidas(nome, cpf, telefone, email)) {
            atualizarCliente(nome, cpf, telefone, email);
        }
    }

    public void excluirCliente(JTextField txtCpf) {
        String cpf = txtCpf.getText().trim();

        if (isCpfValido(cpf)) {
            deletarCliente(cpf);
        }
    }

    private boolean isNomeValido(String nome) {
        if (nome != null && !nome.isEmpty()) {
            String regex = "^[\\p{L}\\s]+$";
            return nome.matches(regex);
        }
        return false;
    }

    private boolean isCpfValido(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            //Formato "000.000.000-00" -> \d{3}\.\d{3}\.\d{3}-\d{2}
            String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
            return cpf.matches(regex);
        }
        return false;
    }

    private boolean isTelefoneValido(String telefone) {
        if (telefone != null && !telefone.isEmpty()) {
            //Formato "(00)00000-0000" -> \(\d{2}\)\d{5}-\d{4}
            String regex = "^\\(\\d{2}\\)\\d{5}-\\d{4}$";
            return telefone.matches(regex);
        }
        return false;
    }

    private boolean isEmailValido(String email) {
        if (email != null && !email.isEmpty()) {
            String regex = "^[a-zA-Z][a-zA-Z0-9._-]{2,10}@[a-zA-Z]{3,7}\\.[a-zA-Z]{2,3}$";
            return email.matches(regex);
        }
        return false;
    }

    private boolean isEntradasValidas(String nome, String cpf, String telefone, String email) {
        StringBuilder erros = new StringBuilder();
        if (!isNomeValido(nome)) erros.append("- Nome inválido!\n");
        if (!isCpfValido(cpf)) erros.append("- CPF inválido!\n");
        if (!isTelefoneValido(telefone)) erros.append("- Telefone inválido!\n");
        if (!isEmailValido(email)) erros.append("- Email inválido!\n");

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(null, "Erros encontrados:\n" + erros, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void salvarCliente(String nome, String cpf, String telefone, String email) {
        try {
            Cliente cliente = new Cliente(nome, cpf, telefone, email);
            clienteDAO.salvar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClienteJaExisteException e) {
            JOptionPane.showMessageDialog(null, "Cliente já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCliente(String nome, String cpf, String telefone, String email) {
        try {
            Cliente cliente = new Cliente(nome, cpf, telefone, email);
            clienteDAO.atualizar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClienteNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Cliente não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarCliente(String cpf) {
        try {
            clienteDAO.deletar(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClienteNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Cliente não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
