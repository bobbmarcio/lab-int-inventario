package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class UnidadeRepository extends Repositorio {

  @Inject
  public UnidadeRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<Unidade> encontreTodos() {
    try {
      final List<Unidade> unidades = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM unidade",
        resultSet -> unidades  .add(new Unidade(resultSet))
      );

      return unidades;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Unidade> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM unidade WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, Unidade::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Unidade> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM sala WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, Unidade::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirUnidade(final Unidade unidade) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO unidade (id, nome, endereco, cidade, uf, cep) VALUES ((SELECT nextval('unidade_id_seq')), ?, ?, ?, ?, ?)"
      );

      statement.setString(1, unidade.getNome());
      statement.setString(2, unidade.getEndereco());
      statement.setString(3, unidade.getCidade());
      statement.setString(4, unidade.getUf());
      statement.setString(5, unidade.getCep());

      statement.execute();

      final Optional<Unidade> persistido = encontrePorNome(unidade.getNome());
      persistido.ifPresent(p -> unidade.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizeUnidade(final long idASerEditado, final Unidade unidade) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE unidade SET nome = ?, endereco = ?, cidade = ?, uf = ?, cep = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, unidade.getNome());
      statement.setString(2, unidade.getEndereco());
      statement.setString(3, unidade.getCidade());
      statement.setString(4, unidade.getUf());
      statement.setLong(5, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void apagaUnidade(final long idASerApagado) {
    try {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM unidade WHERE id = ?"
      );

      statement.setLong(1, idASerApagado);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
