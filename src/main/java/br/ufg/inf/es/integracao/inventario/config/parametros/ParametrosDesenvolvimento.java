package br.ufg.inf.es.integracao.inventario.config.parametros;

/**
 * Parametros da aplicação para o ambiente de desenvolvimento.
 * Normalmente tem os parâmetros "hard-coded" já que serão padronizados
 * nos ambientes de desenvolvimento
 */
public final class ParametrosDesenvolvimento implements Parametros {

  /**
   * Nome qualificado do driver JDBC para Postgres
   */
  private static final String NOME_CLASSE_DRIVER_POSTGRES =
    "org.postgresql.Driver";

  /**
   * URL para o banco de dados postgres resumida
   */
  private static final String URL_POSTGRES_PADRAO_DESENVOLVIMENTO =
    "jdbc:postgresql://localhost/";

  /**
   * Usuário a ser conectado o banco de dados
   */
  private static final String USUARIO_PADRAO_DESENVOLVIMENTO = "postgres";

  /**
   * Senha para o usuário parametrizado
   */
  private static final String SENHA_PADRAO_DESENVOLVIMENTO = "inventario$123";

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUrlJdbcBancoDeDados() {
    return URL_POSTGRES_PADRAO_DESENVOLVIMENTO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUsuarioBancoDeDados() {
    return USUARIO_PADRAO_DESENVOLVIMENTO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSenhaBancoDeDados() {
    return SENHA_PADRAO_DESENVOLVIMENTO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNomeDriverBancoDeDados() {
    return NOME_CLASSE_DRIVER_POSTGRES;
  }

}
