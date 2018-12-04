package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import org.apache.commons.io.IOUtils;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BemPatrimonialRepository {
  private final Supplier<Connection> connectionProvider;

  private static final Charset DDL_FILE_CHARSET = Charset.forName("UTF-8");
  private static final String INSERT_SQL = "/bempatrimonial/insert_bempatrimonial.sql";
  private static final String SELECT_SQL = "/bempatrimonial/select_bempatrimonial.sql";
  private static final String DELETE_SQL = "/bempatrimonial/delete_bempatrimonial.sql";

  @Inject
  public BemPatrimonialRepository(final Supplier<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  public void save(BemPatrimonial bemPatrimonial) throws IOException, SQLException {
    final String sql = this.loadSql(INSERT_SQL);

    final Connection connection = connectionProvider.get();

    final PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, bemPatrimonial.get());
    statement.executeUpdate();
  }

  public List<BemPatrimonial> fetchAll() throws IOException, SQLException {
    final List<BemPatrimonial> bens = new ArrayList<>();

    final String sql = this.loadSql(SELECT_SQL);

    final Connection connection = connectionProvider.get();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
      final BemPatrimonial bem = new BemPatrimonial();
      bem.setId(resultSet.getLong("id"));
      bem.setNome(resultSet.getString("nome"));
      bem.setUnidadeId(resultSet.getLong("unidade_id"));

      bens.add(bem);
    }

    return bens;
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
