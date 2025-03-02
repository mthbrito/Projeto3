package locadora.exception;

public class PagamentoNaoExisteException extends RuntimeException {
  public PagamentoNaoExisteException(String message) {
    super(message);
  }
}
