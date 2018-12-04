package br.ufg.inf.es.integracao.inventario.repositorio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class PredioRepository extends Repositorio {

  @Inject
  public PredioRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<Predio> encontreTodos() {
    try {
      final List<Predio> predios = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM predio",
        resultSet -> predios.add(new Predio(resultSet))
      );

      return predios;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Predio> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM predio WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, Predio::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Predio> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM predio WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, Predio::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirPredio(final Predio predio) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO predio (id, nome, unidade_id) VALUES ((SELECT nextval('predio_id_seq')), ?, ?)"
      );

      statement.setString(1, predio.getNome());
      statement.setLong(2, predio.getIdUnidade());

      statement.execute();

      final Optional<Predio> persistido = encontrePorNome(predio.getNome());
      persistido.ifPresent(p -> predio.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizePredio(final long idASerEditado, final Predio predio) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE predio SET nome = ?, unidade_id = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, predio.getNome());
      statement.setLong(2, predio.getIdUnidade());
      statement.setLong(3, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void apagaPredio(final long idASerApagado) {
    try {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM predio WHERE id = ?"
      );

      statement.setLong(1, idASerApagado);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
