package locadora.dao;

import locadora.exception.PagamentoJaExisteException;
import locadora.exception.UsuarioJaExisteException;
import locadora.exception.UsuarioNaoExisteException;
import locadora.model.Usuario;
import locadora.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends JsonHandler implements IPersistencia<Usuario, Object> {

    private List<Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = usuariosCadastrados();
    }

    @Override
    public void salvar(Usuario usuarioNovo) throws UsuarioJaExisteException {
        for (Usuario usuarioListado : usuarios) {
            if (usuarioListado.getEndereco().equals(usuarioNovo.getEndereco())) {
                throw new PagamentoJaExisteException("Usuário já existe: " + usuarioNovo.getEndereco());
            }
        }
        usuarios.add(usuarioNovo);
        atualizarJson(usuarios);
        System.out.println("Usuário salvo!");
    }

    @Override
    public Usuario ler(Object endereco) throws UsuarioNaoExisteException {
        for (Usuario usuarioListado : usuarios) {
            if (usuarioListado.getEndereco().equals(endereco)) {
                return usuarioListado;
            }
        }
        throw new UsuarioNaoExisteException("Usuário não existe: " + endereco);
    }

    @Override
    public void atualizar(Usuario usuarioAtualizado) throws UsuarioNaoExisteException {
        boolean usuarioAntigo = usuarios.removeIf(usuario -> usuario.getEndereco().equals(usuarioAtualizado.getEndereco()));
        if (!usuarioAntigo) {
            throw new UsuarioNaoExisteException("Usuário não existe: " + usuarioAtualizado.getEndereco());
        }
        usuarios.add(usuarioAtualizado);
        atualizarJson(usuarios);
        System.out.println("Usuário atualizado!");
    }

    @Override
    public void deletar(Object endereco) throws UsuarioNaoExisteException {
        boolean usuarioAntigo = usuarios.removeIf(usuario -> usuario.getEndereco().equals(endereco));
        if (!usuarioAntigo) {
            throw new UsuarioNaoExisteException("Usuário não existe: " + endereco);
        }
        atualizarJson(usuarios);
        System.out.println("Usuário excluído!");
    }

    private void atualizarJson(List<Usuario> usuariosAtualizado) {
        atualizarArquivo("src/main/java/locadora/json/usuarios.json", usuariosAtualizado);
    }

    public List<Usuario> usuariosCadastrados() {
        String arquivo = "src/main/java/locadora/json/usuarios.json";
        if (this.isVazio(arquivo, Usuario.class)) {
            usuarios = new ArrayList<>();
        } else {
            usuarios = this.getConteudo(arquivo, Usuario.class);
        }
        return usuarios;
    }

    public List<Usuario> getUsuariosAdm() {
        List<Usuario> usuariosAdm = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            String tipo = String.valueOf(usuario.getTipo());
            if (tipo.equals("ADMINISTRADOR")) {
                usuariosAdm.add(usuario);
            }
        }
        return usuariosAdm;
    }

    public List<Usuario> getUsuariosGer() {
        List<Usuario> usuariosGer = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            String tipo = String.valueOf(usuario.getTipo());
            if (tipo.equals("GERENTE")) {
                usuariosGer.add(usuario);
            }
        }
        return usuariosGer;
    }

    public List<Usuario> getUsuariosAtend() {
        List<Usuario> usuariosAtend = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            String tipo = String.valueOf(usuario.getTipo());
            if (tipo.equals("ATENDENTE")) {
                usuariosAtend.add(usuario);
            }
        }
        return usuariosAtend;
    }

}
