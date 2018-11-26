package br.ufg.inf.es.integracao.inventario.config.database;

import br.ufg.inf.es.integracao.inventario.config.parametros.Parametros;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provê uma conexão JDBC. A conexão deve ser compartilhada entre
 * várias chamadas do provedor. A classe não é segura contra chamadas
 * concorrentes.
 */
public class ConexaoProvider implements Provider<Connection> {

  /**
   * Parametros da aplicação.
   */
  private final Parametros parametros;

  /**
   * Conexão JDBC compartilhada durante a aplicação.
   */
  private Connection connexaoCompartilhada = null;

  /**
   * Necessita apenas dos parametros de banco de dados:
   * - {@link Parametros#getUrlJdbcBancoDeDados()}
   * - {@link Parametros#getNomeDriverBancoDeDados()}
   * - {@link Parametros#getUsuarioBancoDeDados()}
   * - {@link Parametros#getSenhaBancoDeDados()}
   *
   * @param parametros Bolsa de parametros da aplicação
   */
  @Inject
  public ConexaoProvider(final Parametros parametros) {
    this.parametros = parametros;
  }

  /**
   * Retorna uma instância compartilhada de conexação com o banco de dados.
   * Não segura em chamadas concorrentes.
   *
   * @return Conexação com o banco de dados compartilhada entre chamadas
   */
  @Override
  public Connection get() {
    if (connexaoCompartilhada == null) {
      try {
        connexaoCompartilhada = construirNovaConexao();
      } catch (final Exception e) {
        throw new ConexaoException(
          "Houve um erro ao se conectar com o banco de dados", e);
      }
    }

    return connexaoCompartilhada;
  }

  /**
   * Constrói uma nova conexão a partir dos parametros atuais.
   *
   * @return Nova conexão
   * @throws ClassNotFoundException Caso o driver parametrizado não seja
   *                                encontrado
   * @throws SQLException           Caso haja algum erro na conexão com o banco de dados
   */
  private Connection construirNovaConexao()
    throws ClassNotFoundException, SQLException {
    carregaDriver();

    return DriverManager.getConnection(
      parametros.getUrlJdbcBancoDeDados(),
      parametros.getUsuarioBancoDeDados(),
      parametros.getSenhaBancoDeDados()
    );
  }

  /**
   * Carrega a classe do driver JDBC parametrizado.
   *
   * @throws ClassNotFoundException Classe JDBC parametrizada não encontrada
   */
  private void carregaDriver() throws ClassNotFoundException {
    Class.forName(parametros.getNomeDriverBancoDeDados());
  }

}
