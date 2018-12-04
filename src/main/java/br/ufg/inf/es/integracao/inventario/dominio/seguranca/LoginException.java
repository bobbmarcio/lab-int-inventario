package br.ufg.inf.es.integracao.inventario.dominio.seguranca;

public class LoginException extends Exception {
  public LoginException(String message) {
    super(message);
  }

  public LoginException(String message, Throwable cause) {
    super(message, cause);
  }
}
