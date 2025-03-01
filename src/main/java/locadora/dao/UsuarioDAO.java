package locadora.dao;

import com.google.gson.Gson;
import locadora.model.TiposUsuarios;
import locadora.model.Usuario;
import locadora.utils.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends JsonHandler implements IPersistencia<Usuario, Object>{

    private List<Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = usuariosCadastrados();
    }

    @Override
    public void salvar(Usuario novoUsuario) {
        for(Usuario usuarioListado: usuarios) {
            if(usuarioListado.getEndereco().equals(novoUsuario.getEndereco())){
                System.out.println("Usuário já existe!");
                return;
            }
        }
        usuarios.add(novoUsuario);
        atualizarJson(usuarios);
        System.out.println("Usuário salvo!");
    }

    @Override
    public Usuario ler(Object endereco) {
        for(Usuario usuarioListado: usuarios) {
            if(usuarioListado.getEndereco().equals(endereco)){
                return usuarioListado;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Usuario usuarioAtualizado) {
        List<Usuario> usuariosParaRemover = new ArrayList<>();
        for(Usuario usuarioListado: usuarios) {
            if(usuarioListado.getEndereco().equals(usuarioAtualizado.getEndereco())){
                usuariosParaRemover.add(usuarioListado);
            }
        }
        usuarios.removeAll(usuariosParaRemover);
        usuarios.add(usuarioAtualizado);
        atualizarJson(usuarios);
        System.out.println("Usuário atualizado!");
    }

    @Override
    public void deletar(Object endereco) {
        List<Usuario> usuariosParaRemover = new ArrayList<>();
        for(Usuario usuarioListado: usuarios) {
            if(usuarioListado.getEndereco().equals(endereco)){
                usuariosParaRemover.add(usuarioListado);
            }
        }
        usuarios.removeAll(usuariosParaRemover);
        atualizarJson(usuarios);
        System.out.println("Usuário excluído!");
    }

    private List<Usuario> usuariosCadastrados() {
        String arquivo = "src/main/java/locadora/json/usuarios.json";
        if (this.isVazio(arquivo, Usuario.class)) {
            usuarios = new ArrayList<>();
        } else {
            usuarios = this.getConteudo(arquivo, Usuario.class);
            usuarios.forEach(e-> System.out.println(e.getEndereco()));
            System.out.println(usuarios.size());
        }
        return usuarios;
    }

    private void atualizarJson(List<Usuario> usuariosAtualizado) {
        String usuariosAtualizadoJson = new Gson().toJson(usuariosAtualizado);
        try (FileWriter writer = new FileWriter("src/main/java/locadora/json/usuarios.json")) {
            writer.write(usuariosAtualizadoJson);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Usuario> getUsuariosAdm(){
        List<Usuario> usuariosAdm = new ArrayList<>();
        for(Usuario usuario: usuarios){
            String tipo = String.valueOf(usuario.getTipo());
            if(tipo.equals("ADMINISTRADOR")){
                usuariosAdm.add(usuario);
            }
        }
        return usuariosAdm;
    }

    public List<Usuario> getUsuariosGer(){
        List<Usuario> usuariosGer = new ArrayList<>();
        for(Usuario usuario: usuarios){
            String tipo = String.valueOf(usuario.getTipo());
            if(tipo.equals("GERENTE")){
                usuariosGer.add(usuario);
            }
        }
        return usuariosGer;
    }

    public List<Usuario> getUsuariosAtend(){
        List<Usuario> usuariosAtend = new ArrayList<>();
        for(Usuario usuario: usuarios){
            String tipo = String.valueOf(usuario.getTipo());
            if(tipo.equals("ATENDENTE")){
                usuariosAtend.add(usuario);
            }
        }
        return usuariosAtend;
    }

}
