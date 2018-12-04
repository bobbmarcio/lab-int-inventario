package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Sala;
import org.apache.commons.io.IOUtils;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SalaRepository {

  private final Supplier<Connection> connectionProvider;

  private static final Charset DDL_FILE_CHARSET = Charset.forName("UTF-8");
  private static final String INSERT_SQL = "/sala/insert_sala.sql";
  private static final String SELECT_SQL = "/sala/select_sala.sql";
  private static final String DELETE_SQL = "/sala/delete_sala.sql";

  @Inject
  public SalaRepository(final Supplier<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  public void save(Sala sala) throws IOException, SQLException {
    final String sql = this.loadSql(INSERT_SQL);

    final Connection connection = connectionProvider.get();

    final PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, sala.getNome());
    statement.setString(2, sala.getCodigo());
    statement.executeUpdate();
  }

  public List<Sala> fetchAll() throws IOException, SQLException {
    final List<Sala> salas = new ArrayList<>();

    final String sql = this.loadSql(SELECT_SQL);

    final Connection connection = connectionProvider.get();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
      final Sala sala = new Sala();
      sala.setId(resultSet.getLong("id"));
      sala.setNome(resultSet.getString("nome"));
      sala.setCodigo(resultSet.getString("codigo"));


      salas.add(sala);
    }

    return salas;
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
