package locadora.exception;

public class LocacaoJaExisteException extends RuntimeException {
    public LocacaoJaExisteException(String message) {
        super(message);
    }
}
