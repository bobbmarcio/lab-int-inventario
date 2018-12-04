package br.ufg.inf.es.integracao.inventario.repositorio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.Departamento;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class DepartamentoRepository extends Repositorio {

  @Inject
  public DepartamentoRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<Departamento> encontreTodos() {
    try {
      final List<Departamento> departamentos = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM departamento",
        resultSet -> departamentos.add(new Departamento(resultSet))
      );

      return departamentos;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Departamento> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM departamento WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, Departamento::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Departamento> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM departamento WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, Departamento::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirDepartamento(final Departamento departamento) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO departamento (id, nome, chefe) VALUES ((SELECT nextval('departamento_id_seq')), ?, ?)"
      );

      statement.setString(1, departamento.getNome());
      statement.setLong(2, departamento.getChefe());

      statement.execute();

      final Optional<Departamento> persistido = encontrePorNome(departamento.getNome());
      persistido.ifPresent(p -> departamento.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizeDepartamento(final long idASerEditado, final Departamento departamento) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE departamento SET nome = ?, chefe = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, departamento.getNome());
      statement.setLong(2, departamento.getChefe());
      statement.setLong(3, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void apagaDepartamento(final long idASerApagado) {
    try {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM departamento WHERE id = ?"
      );

      statement.setLong(1, idASerApagado);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
