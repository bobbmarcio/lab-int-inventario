package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUnidades;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UnidadeTeste {

  private GerenciadorUnidades gerenciadorgnidades ;

  @Before
  private void inicializar() {
    this.gerenciadorgnidades = new GerenciadorUnidades();
  }

  @Test
  public void testeInsertUnidade() throws IOException, SQLException {
      gerenciadorgnidades.cadastrarNovaUnidade("Unidade", "Rua Viterita", "Aparecida de Goiania", "GO", "74955530");
      final List<Unidade> unidades = gerenciadorgnidades.listarTodos();
      Assert.assertTrue(!unidades.isEmpty());
  }

  @Test
  public void testeDeleteUnidade() throws IOException, SQLException {
    gerenciadorgnidades.cadastrarNovaUnidade("Unidade", "Rua Viterita", "Aparecida de Goiania", "GO", "74955530");
    final List<Unidade> unidades = gerenciadorgnidades.listarTodos();
    if (!unidades.isEmpty()) {
      gerenciadorgnidades.apagarUnidadePorId(unidades.get(0).getId());

      final List<Unidade> unidadesPosRemocao = gerenciadorgnidades.listarTodos();
      Assert.assertTrue(!(unidadesPosRemocao.size() > 0));

    }

    Assert.fail();
  }

  @Test
  public void testefetchUnidades() throws IOException, SQLException {
    gerenciadorgnidades.cadastrarNovaUnidade("Unidade", "Rua Viterita", "Aparecida de Goiania", "GO", "74955530");
    final List<Unidade> unidades = gerenciadorgnidades.listarTodos();
    Assert.assertTrue(unidades.size() > 0);
  }
}
