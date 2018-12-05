package br.ufg.inf.es.integracao.inventario.repositorio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.BaixaBem;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public class BaixaBemRepository extends Repositorio {
  @Inject
  public BaixaBemRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public void inserirBaixaBem(final BaixaBem bem) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO baixa_bem (data, comentario) " +
          "VALUES (now(), ?)"
      );

      statement.setString(1, bem.getComentario());


      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deletarBaixaBem(final int id) {
    try
    {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM baixa WHERE id = ?"
      );

      statement.setLong(1, id);

      statement.execute();
    } catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public Optional<BaixaBem> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM baixa WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, BaixaBem::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualize(final long idASerEditado, final BaixaBem baixa) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE baixa SET comentario = ? , bem_patrimonial_id = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, baixa.getComentario());
      statement.setLong(2, baixa.getBemPatrimonialId());
      statement.setLong(3, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<BaixaBem> encontreTodos() {
    try {
      final List<BaixaBem> bens = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM baixa",
        resultSet -> bens.add(new BaixaBem(resultSet))
      );

      return bens;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
