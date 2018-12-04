package br.ufg.inf.es.integracao.inventario.repositorio;

import java.sql.*;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Repositorio {

  protected final Supplier<Connection> connectionSupplier;

  protected Repositorio(Supplier<Connection> connectionSupplier) {
    this.connectionSupplier = connectionSupplier;
  }

  protected PreparedStatement prepareStatement(final String sql) throws SQLException {
    return connectionSupplier.get().prepareStatement(sql);
  }

  protected <T> Optional<T> singleResult(
    final ResultSet result,
    final Function<ResultSet, T> fn
  ) throws SQLException {
    if (result.next()) {
      return Optional.of(fn.apply(result));
    }

    return Optional.empty();
  }

  protected <T> Optional<T> singleResult(
    final PreparedStatement statement,
    final Function<ResultSet, T> fn
  ) throws SQLException {
    return singleResult(statement.executeQuery(), fn);
  }

  protected <T> Optional<T> singleResult(
    final String sql,
    final Function<ResultSet, T> fn
  ) throws SQLException {
    final Statement statement = connectionSupplier.get().createStatement();
    return singleResult(statement.executeQuery(sql), fn);
  }

  protected void iterateResultsOf(
    final ResultSet result,
    final Consumer<ResultSet> consumer
  ) throws SQLException {
    try {
      while (result.next()) {
        consumer.accept(result);
      }
    } finally {
      result.close();
    }
  }

  protected void iterateResultsOf(
    final PreparedStatement statement,
    final Consumer<ResultSet> consumer
  ) throws SQLException {
    iterateResultsOf(statement.executeQuery(), consumer);
  }

  protected void iterateResultsOf(
    final Statement statement,
    final String sql,
    final Consumer<ResultSet> consumer
  ) throws SQLException {
    iterateResultsOf(statement.executeQuery(sql), consumer);
  }

  protected void iterateResultsOf(
    final String sql,
    final Consumer<ResultSet> consumer
  ) throws SQLException {
    final Statement statement = connectionSupplier.get().createStatement();
    iterateResultsOf(statement.executeQuery(sql), consumer);
  }

}
