package locadora.view;

import locadora.dao.UsuarioDAO;
import locadora.model.Usuario;

import javax.swing.*;
import java.util.List;

public class LoginAtendView extends LoginView {

    public LoginAtendView() {
        super();
        getLblTitulo().setText("Login Atendente");
        call();
    }

    private void call() {
        getBtnAcessar().addActionListener(e -> acessarAtend(getTxtUsuario(), getTxtSenha()));
    }

    public void acessarAtend(JTextField txtUsuario, JTextField txtSenha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuariosCadastrados = usuarioDAO.getUsuariosAtend();
        boolean acesso = false;
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getEndereco().equals(txtUsuario.getText()) && usuario.getSenha().equals(txtSenha.getText())) {
                acesso = true;
                break;
            }
        }
        if (acesso) {
            getFrame().dispose();
            AtendenteView atendenteView = new AtendenteView();
            atendenteView.getFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)!", "Acesso negado!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
