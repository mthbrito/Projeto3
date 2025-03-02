package locadora.exception;

public class LocacaoNaoExisteException extends RuntimeException {
    public LocacaoNaoExisteException(String message) {
        super(message);
    }
}
