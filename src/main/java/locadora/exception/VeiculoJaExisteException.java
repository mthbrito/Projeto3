package locadora.exception;

public class VeiculoJaExisteException extends RuntimeException {
    public VeiculoJaExisteException(String message) {
        super(message);
    }
}
