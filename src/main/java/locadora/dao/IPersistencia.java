package locadora.dao;

public interface IPersistencia<T, ID> {

    void salvar(T objeto); //Create
    T ler(ID identificador); //Read
    void atualizar(T Objeto); //Update
    void deletar(ID identificador); //Delete

}
