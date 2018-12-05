package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorPredios;
import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUnidades;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PredioTeste {
  private GerenciadorPredios gerenciadorPredios;
  private GerenciadorUnidades gerenciadorUnidades;

  PredioTeste() {
    this.gerenciadorPredios = new GerenciadorPredios();
    this.gerenciadorUnidades = new GerenciadorUnidades();
  }

  @Test
  public void testeInsertPredio() {
    cadastrarNovoPredio();
    final List<Predio> predios = gerenciadorPredios.listarTodos();
    Assert.assertTrue(!predios.isEmpty());
  }

  @Test
  public void testefetchPredios() throws IOException, SQLException {
    cadastrarNovoPredio();
    final List<Predio> predios = gerenciadorPredios.listarTodos();
    Assert.assertTrue(predios.size() > 0);
  }

  @Test
  public void testeDeletePredio() throws IOException, SQLException {
    cadastrarNovoPredio();

    final List<Predio> predios = gerenciadorPredios.listarTodos();
    if (!predios.isEmpty()) {
      gerenciadorPredios.apagarPredioPorId(predios.get(0).getId());

      final List<Predio> prediosPosRemocao = gerenciadorPredios.listarTodos();
      Assert.assertTrue(!(prediosPosRemocao.size() > 0));
    }

    Assert.fail();
  }

  private void cadastrarNovoPredio() {
    Unidade unidade = obterUnidadeCadastrada();
    gerenciadorPredios.cadastrarNovoPredio("Predio um", unidade.getId());
  }

  private Unidade obterUnidadeCadastrada() {
    cadastrarNovaUnidade();
    final List<Unidade> unidades = gerenciadorUnidades.listarTodos();
    if (!unidades.isEmpty()) {
      return unidades.get(0);
    }

    throw new RuntimeException("Não existe unidade cadastrado");
  }

  private void cadastrarNovaUnidade() {
    gerenciadorUnidades.cadastrarNovaUnidade("Unidade", "Rua Viterita", "Aparecida de Goiania", "GO", "74955530");
  }
}
