package locadora.view;

import locadora.dao.UsuarioDAO;
import locadora.model.Usuario;

import javax.swing.*;
import java.util.List;

public class LoginAdmView extends LoginView {

    public LoginAdmView() {
        super();
        this.getLblTitulo().setText("Login Administrador");
        this.getLblTitulo().setBounds(95, 30, 260, 40);
        call();
    }

    private void call() {
        getBtnAcessar().addActionListener(e -> {
            acessarAdm(getTxtUsuario(), getTxtSenha());
        });
    }

    public void acessarAdm(JTextField txtUsuario, JTextField txtSenha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuariosCadastrados = usuarioDAO.getUsuariosAdm();
        boolean acesso = false;
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getEndereco().equals(txtUsuario.getText()) && usuario.getSenha().equals(txtSenha.getText())) {
                acesso = true;
                break;
            }
        }
        if (acesso) {
            getFrame().dispose();
            AdministradorView administradorView = new AdministradorView();
            administradorView.getFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)!", "Acesso negado!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
