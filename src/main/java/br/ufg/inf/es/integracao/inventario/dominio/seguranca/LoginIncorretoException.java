package br.ufg.inf.es.integracao.inventario.dominio.seguranca;

public class LoginIncorretoException extends LoginException {
  public LoginIncorretoException(String message) {
    super(message);
  }

  public LoginIncorretoException(String message, Throwable cause) {
    super(message, cause);
  }
}
