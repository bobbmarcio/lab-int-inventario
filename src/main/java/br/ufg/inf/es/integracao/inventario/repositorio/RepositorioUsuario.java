package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class RepositorioUsuario extends Repositorio {

  @Inject
  public RepositorioUsuario(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public List<Usuario> encontreTodos() {
    try {
      final List<Usuario> usuarios = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM usuario",
        resultSet -> usuarios.add(new Usuario(resultSet))
      );

      return usuarios;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Usuario> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM usuario WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, Usuario::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Usuario> encontrePorNome(final String nome) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM usuario WHERE nome = ?"
      );

      statement.setString(1, nome);
      return singleResult(statement, Usuario::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void inserirUsuario(final Usuario usuario) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO usuario (id, nome, email, senha) VALUES ((SELECT nextval('usuario_id_seq')), ?, ?, ?)"
      );

      statement.setString(1, usuario.getNome());
      statement.setString(2, usuario.getEmail());
      statement.setString(3, usuario.getSenha());

      statement.execute();

      final Optional<Usuario> persistido = encontrePorNome(usuario.getNome());
      persistido.ifPresent(p -> usuario.setId(p.getId()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualizeUsuario(final long idASerEditado, final Usuario usuario) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE usuario SET nome = ?, email = ?, senha = ? "
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
