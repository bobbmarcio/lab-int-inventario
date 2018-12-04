package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UnidadeRepository {

  private final Supplier<Connection> connectionProvider;

  private static final Charset DDL_FILE_CHARSET = Charset.forName("UTF-8");
  private static final String INSERT_SQL = "/unidade/insert_unidade.sql";
  private static final String SELECT_SQL = "/unidade/select_unidade.sql";
  private static final String DELETE_SQL = "/unidade/delete_unidade.sql";

  @Inject
  public UnidadeRepository(final Supplier<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  public void save(Unidade unidade) throws IOException, SQLException {
    final String sql = this.loadSql(INSERT_SQL);

    final Connection connection = connectionProvider.get();

    final PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, unidade.getNome());
    statement.setString(2, unidade.getEndereco());
    statement.setString(3, unidade.getCidade());
    statement.setString(4, unidade.getUf());
    statement.setString(5, unidade.getCep());
    statement.executeUpdate();
  }

  public List<Unidade> fetchAll() throws IOException, SQLException {
    final List<Unidade> unidades = new ArrayList<>();

    final String sql = this.loadSql(SELECT_SQL);

    final Connection connection = connectionProvider.get();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
      final Unidade unidade = new Unidade();
      unidade.setId(resultSet.getLong("id"));
      unidade.setNome(resultSet.getString("nome"));
      unidade.setEndereco(resultSet.getString("endereco"));
      unidade.setCidade(resultSet.getString("cidade"));
      unidade.setUf(resultSet.getString("uf"));
      unidade.setCep(resultSet.getString("cep"));

      unidades.add(unidade);
    }

    return unidades;
  }

  public void delete(Long id) throws IOException, SQLException {
    final String sql = this.loadSql(DELETE_SQL);

    final Connection connection = connectionProvider.get();

    final PreparedStatement statement = connection.prepareStatement(sql);
    statement.setLong(1, id);
    statement.executeUpdate();
  }

  private String loadSql(String sqlName) throws IOException {
    final InputStream stream = getClass().getResourceAsStream(INSERT_SQL);

    if (stream == null) {
      throw new RuntimeException("Insert Sql não encontrado");
    }

    return IOUtils.toString(stream, DDL_FILE_CHARSET);
  }

}
