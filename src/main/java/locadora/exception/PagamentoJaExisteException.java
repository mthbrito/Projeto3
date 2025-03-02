package locadora.exception;

public class PagamentoJaExisteException extends RuntimeException {
    public PagamentoJaExisteException(String message) {
        super(message);
    }
}
