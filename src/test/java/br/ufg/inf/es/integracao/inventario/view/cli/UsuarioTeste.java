package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUsuarios;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioTeste {

  private GerenciadorUsuarios gerenciadorUsuarios;

  UsuarioTeste() {
    this.gerenciadorUsuarios = new GerenciadorUsuarios();
  }

  @Test
  public void testaInsertUsuario() {
    cadastrarNovoUsuario();
    final List<Usuario> usuarios = gerenciadorUsuarios.listarTodos();
    Assert.assertTrue(!usuarios.isEmpty());
  }

  @Test
  public void testefetchUsuario() throws IOException, SQLException {
    cadastrarNovoUsuario();
    final List<Usuario> usuarios = gerenciadorUsuarios.listarTodos();
    Assert.assertTrue(usuarios.size() > 0);
  }

  @Test
  public void testeDeleteUsuario() throws IOException, SQLException {
    cadastrarNovoUsuario();
    final List<Usuario> usuarios = gerenciadorUsuarios.listarTodos();
    if (!usuarios.isEmpty()) {
      gerenciadorUsuarios.apagarUsuarioPorId(usuarios.get(0).getId());

      final List<Usuario> usuariosAposRemocao = gerenciadorUsuarios.listarTodos();
      Assert.assertTrue(!(usuariosAposRemocao.size() > 0));
    }

    Assert.fail();
  }

  private void cadastrarNovoUsuario() {
    gerenciadorUsuarios.cadastrarNovoUsuario("Kaique Silva", "kaique.silva@gmail.com", "senha@Kaique");
  }
}
