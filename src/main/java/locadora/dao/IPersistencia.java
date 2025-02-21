package locadora.dao;

import java.io.IOException;

public interface IPersistencia<T> {

    void salvar(T objeto);
    T ler(T objeto);
    void deletar(T objeto);

}
