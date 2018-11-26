package br.ufg.inf.es.integracao.inventario.config.database;

/**
 * Representa um erro ocorrido no momento de conexão com o banco de dados.
 */
public class ConexaoException extends RuntimeException {

  public ConexaoException(String message) {
    super(message);
  }

  public ConexaoException(String message, Throwable cause) {
    super(message, cause);
  }

}
