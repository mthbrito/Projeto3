package locadora.exception;

public class VeiculoNaoExisteException extends RuntimeException {
    public VeiculoNaoExisteException(String message) {
        super(message);
    }
}
