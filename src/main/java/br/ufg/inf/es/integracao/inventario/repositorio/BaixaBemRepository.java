package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.BaixaBem;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import org.apache.commons.io.IOUtils;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BaixaBemRepository extends Repositorio {
  @Inject
  public BaixaBemRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<BaixaBem> encontreTodos() {
    try {
      final List<BaixaBem> baixas = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM baixabem",
        resultSet -> baixas.add(new BaixaBem(resultSet))
      );

      return baixas;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<BaixaBem> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM baixabem WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, BaixaBem::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<BaixaBem> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM baixabem WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, BaixaBem::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirBaixaBem(final BaixaBem baixa) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO baixabem (nome, email, senha) VALUES (?, ?, ?)"
      );

      statement.setString(1, baixa.getNome());
      statement.setString(2, baixa.getEmail());
      statement.setString(3, baixa.getSenha());

      statement.execute();

      final Optional<BaixaBem> persistido = encontrePorNome(baixa.getNome());
      persistido.ifPresent(p -> baixa.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizeBem(final long idASerEditado, final BaixaBem baixa) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE baixabem SET nome = ?, email = ?, senha = ? "
          + "WHERE id = ?"
      );

      statement.setString(1, usuario.getNome());
      statement.setString(2, usuario.getEmail());
      statement.setString(3, usuario.getSenha());
      statement.setLong(4, idASerEditado);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }
}
