package locadora.controller;

import locadora.dao.UsuarioDAO;
import locadora.model.TiposUsuarios;
import locadora.model.Usuario;

import javax.swing.*;

public class UsuarioController {

    public void cadastrarUsuario(JComboBox<TiposUsuarios> comboBoxFuncao, JTextField txtUsuario, JTextField txtSenha) {
        Usuario usuario = new Usuario((TiposUsuarios) comboBoxFuncao.getSelectedItem(), txtUsuario.getText(), txtSenha.getText());
        new UsuarioDAO().salvar(usuario);
    }

    public void editarUsuario(JComboBox<TiposUsuarios> comboBoxFuncao, JTextField txtUsuario, JTextField txtSenha) {
        Usuario usuario = new Usuario((TiposUsuarios) comboBoxFuncao.getSelectedItem(), txtUsuario.getText(), txtSenha.getText());
        new UsuarioDAO().atualizar(usuario);
    }

    public void excluirUsuario(JTextField txtUsuario) {
        new UsuarioDAO().deletar(txtUsuario.getText());
    }
}
