package locadora.controller;

import locadora.dao.UsuarioDAO;
import locadora.exception.UsuarioJaExisteException;
import locadora.exception.UsuarioNaoExisteException;
import locadora.model.TiposUsuarios;
import locadora.model.Usuario;

import javax.swing.*;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(JComboBox<TiposUsuarios> comboBoxFuncao, JTextField txtEndereco, JTextField txtSenha) {
        TiposUsuarios tipoUsuario = (TiposUsuarios) comboBoxFuncao.getSelectedItem();
        String endereco = txtEndereco.getText().trim();
        String senha = txtSenha.getText().trim();

        if (isEntradasValidas(tipoUsuario, endereco, senha)) {
            try {
                Usuario usuario = new Usuario(tipoUsuario, endereco, senha);
                usuarioDAO.salvar(usuario);
                JOptionPane.showMessageDialog(null, "Usuário salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (UsuarioJaExisteException e) {
                JOptionPane.showMessageDialog(null, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Usuario lerUsuario(JComboBox<Integer> comboBoxFuncao, JTextField txtEndereco) {
        TiposUsuarios tipoUsuario = (TiposUsuarios) comboBoxFuncao.getSelectedItem();
        String endereco = txtEndereco.getText().trim();

        Usuario usuario = null;
        if (isTipoUsuarioValido(tipoUsuario) && isEnderecoValido(endereco)) {
            try {
                usuario = usuarioDAO.ler(tipoUsuario, endereco);
                JOptionPane.showMessageDialog(null, "Usuário encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                return usuario;
            } catch (UsuarioNaoExisteException e) {
                JOptionPane.showMessageDialog(null, "Usuário não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                return usuario;
            }
        }
        return usuario;
    }


    public void editarUsuario(JComboBox<TiposUsuarios> comboBoxFuncao, JTextField txtEndereco, JTextField txtSenha) {
        TiposUsuarios tipoUsuario = (TiposUsuarios) comboBoxFuncao.getSelectedItem();
        String endereco = txtEndereco.getText().trim();
        String senha = txtSenha.getText().trim();

        if (isEntradasValidas(tipoUsuario, endereco, senha)) {
            try {
                Usuario usuario = new Usuario(tipoUsuario, endereco, senha);
                usuarioDAO.atualizar(usuario);
                JOptionPane.showMessageDialog(null, "Usuário atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (UsuarioNaoExisteException e) {
                JOptionPane.showMessageDialog(null, "Usuário não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void excluirUsuario(JComboBox<TiposUsuarios> comboBoxFuncao, JTextField txtEndereco) {
        TiposUsuarios tipoUsuario = (TiposUsuarios) comboBoxFuncao.getSelectedItem();
        String endereco = txtEndereco.getText().trim();

        if (isTipoUsuarioValido(tipoUsuario) && isEnderecoValido(endereco)) {
            try {
                usuarioDAO.deletar(tipoUsuario, endereco);
                JOptionPane.showMessageDialog(null, "Usuário excluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (UsuarioNaoExisteException e) {
                JOptionPane.showMessageDialog(null, "Usuário não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            StringBuilder erros = new StringBuilder();
            if (!isTipoUsuarioValido(tipoUsuario)) erros.append("-Tipo de usuário inválido!\n");
            if (!isEnderecoValido(endereco)) erros.append("-Endereço inválido!\n");
            JOptionPane.showMessageDialog(null, erros, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isTipoUsuarioValido(TiposUsuarios tipoUsuario) {
        return tipoUsuario != null;
    }

    public boolean isEnderecoValido(String endereco) {
        if (endereco != null && !endereco.isEmpty()) {
            String regex = "^[a-zA-Z][a-zA-Z0-9]{2,9}$";
            return endereco.matches(regex);
        }
        return false;
    }

    public boolean isSenhaValida(String senha) {
        if (senha != null && !senha.isEmpty()) {
            String regex = "^[a-zA_Z0-9]{3,10}";
            return senha.matches(regex);
        }
        return false;
    }

    public boolean isEntradasValidas(TiposUsuarios tipoUsuario, String endereco, String senha) {
        StringBuilder erros = new StringBuilder();
        if (!isTipoUsuarioValido(tipoUsuario)) erros.append("-Tipo de usuário inválido!\n");
        if (!isEnderecoValido(endereco)) erros.append("-Endereço inválido!\n");
        if (!isSenhaValida(senha)) erros.append("-Senha inválida!\n");

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(null, "Erros encontrados:\n" + erros, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
