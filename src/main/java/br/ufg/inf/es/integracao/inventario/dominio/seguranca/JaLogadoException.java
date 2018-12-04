package br.ufg.inf.es.integracao.inventario.dominio.seguranca;

public class JaLogadoException extends LoginException {
  public JaLogadoException(String message) {
    super(message);
  }

  public JaLogadoException(String message, Throwable cause) {
    super(message, cause);
  }
}
