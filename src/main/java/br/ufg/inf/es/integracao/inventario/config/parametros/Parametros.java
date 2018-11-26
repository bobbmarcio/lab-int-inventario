package br.ufg.inf.es.integracao.inventario.config.parametros;

/**
 * Representa os parametros usados na aplicação, podem ser externos e o
 * resultado dos métodos pode ter retornos diferentes durante a execução
 * ou não.
 */
public interface Parametros {

  /**
   * Retorna a URL JDBC a ser usada em conexões ao banco de dados.
   * Deve retornar o mesmo valor durante toda a execução da aplicação.
   *
   * @return URL JDBC apontando pro banco de dados.
   */
  String getUrlJdbcBancoDeDados();

  /**
   * Retorna o nome do usuário a ser credenciado no banco de dados.
   * Deve retornar o mesmo valor durante toda a execução da aplicação.
   *
   * @return Nome de um usuário credenciável
   */
  String getUsuarioBancoDeDados();

  /**
   * Retorna senha para o usuário passado no método
   * {@link #getUsuarioBancoDeDados()} para credenciamento no banco de dados.
   * Deve retornar o mesmo valor durante toda a execução da aplicação.
   *
   * @return Senha do usuário em {@link #getUsuarioBancoDeDados()}
   */
  String getSenhaBancoDeDados();

  /**
   * Retorna o nome qualificado da classe Java do driver JDBC usado para
   * comunicação com o banco de dados.
   * Deve retornar o mesmo valor durante toda a execução da aplicação.
   *
   * @return Nome qualificado da classe do Driver JDBC sendo usado
   */
  String getNomeDriverBancoDeDados();

}
