package locadora.view;

import locadora.dao.UsuarioDAO;
import locadora.model.Usuario;

import javax.swing.*;
import java.util.List;

public class LoginGerView extends LoginView{

    public LoginGerView() {
        super();
        getLblTitulo().setText("Login Gerente");
        call();
    }

    private void call(){
        getBtnAcessar().addActionListener(e -> acessarGer(getTxtUsuario(), getTxtSenha()));
    }

    public void acessarGer(JTextField txtUsuario, JTextField txtSenha){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuariosCadastrados = usuarioDAO.getUsuariosGer();
        boolean acesso = false;
        for(Usuario usuario: usuariosCadastrados) {
            if(usuario.getEndereco().equals(txtUsuario.getText()) && usuario.getSenha().equals(txtSenha.getText())) {
                acesso = true;
                break;
            }
        }
        if(acesso) {
            GerenteView gerenteView = new GerenteView();
            gerenteView.getFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)!", "Acesso negado!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
