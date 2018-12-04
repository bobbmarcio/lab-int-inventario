package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Sala;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SalaRepository extends Repositorio {

  @Inject
  public SalaRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<Sala> encontreTodos() {
    try {
      final List<Sala> salas = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM sala",
        resultSet -> salas  .add(new Sala(resultSet))
      );

      return salas;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Sala> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM sala WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, Sala::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Sala> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM sala WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, Sala::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirSala(final Sala sala) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO sala (id, nome, codigo, predio_id, departamento_id) VALUES ((SELECT nextval('sala_id_seq')), ?, ?, ?, ?)"
      );

      statement.setString(1, sala.getNome());
      statement.setString(2, sala.getCodigo());
      statement.setLong(3, sala.getPredioId());
      statement.setLong(4, sala.getDepartamentoId());

      statement.execute();

      final Optional<Sala> persistido = encontrePorNome(sala.getNome());
      persistido.ifPresent(p -> sala.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizeSala(final long idASerEditado, final Sala sala) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE sala SET nome = ?, codigo = ?, predio_id = ?, departamento_id = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, sala.getNome());
      statement.setString(2, sala.getCodigo());
      statement.setLong(3, sala.getPredioId());
      statement.setLong(4, sala.getDepartamentoId());
      statement.setLong(5, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void apagaSala(final long idASerApagado) {
    try {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM sala WHERE id = ?"
      );

      statement.setLong(1, idASerApagado);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
