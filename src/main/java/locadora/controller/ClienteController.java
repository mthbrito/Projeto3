package locadora.controller;

import locadora.dao.ClienteDAO;
import locadora.model.Cliente;

import javax.swing.*;

public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void CadastrarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), txtEmail.getText());
        clienteDAO.salvar(cliente);
    }

    public void EditarCliente(JTextField txtNome, JTextField txtCpf, JTextField txtTelefone, JTextField txtEmail) {
        Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), txtEmail.getText());
        clienteDAO.atualizar(cliente);
    }

    public void ExcluirCliente(JTextField txtCpf) {
        clienteDAO.deletar(txtCpf.getText());
    }
}
