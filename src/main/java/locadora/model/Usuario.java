package locadora.model;

public class Usuario {

    private TiposUsuarios tipo;
    private String endereco;
    private String senha;

    public Usuario(TiposUsuarios tipo, String usuario, String senha) {
        this.tipo = tipo;
        this.endereco = usuario;
        this.senha = senha;
    }

    public TiposUsuarios getTipo() {
        return tipo;
    }

    public void setTipo(TiposUsuarios tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tipo=" + tipo +
                ", endereco='" + endereco + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
