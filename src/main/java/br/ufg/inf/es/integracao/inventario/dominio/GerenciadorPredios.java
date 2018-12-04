package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import br.ufg.inf.es.integracao.inventario.repositorio.PredioRepository;
import br.ufg.inf.es.integracao.inventario.repositorio.SalaRepository;

import javax.inject.Inject;
import java.util.List;

public class GerenciadorPredios {

  @Inject
  private PredioRepository predioRepository;

  public Predio cadastrarNovoPredio(
    final String nome,
    final Long unidadeId
  ) {
    final Predio predio = new Predio();
    predio.setNome(nome);
    predio.setIdUnidade(unidadeId);

    predioRepository.inserirPredio(predio);
    return predio;
  }

  public void atualizaPredio(
    final long idDoPredio,
    final String novoNome,
    final Long novaUnidadeId
  ) {
    final Predio predio = new Predio();
    predio.setNome(novoNome);
    predio.setIdUnidade(novaUnidadeId);

    predioRepository.atualizePredio(idDoPredio, predio);
  }

  public void apagarPredioPorId(final long id) {
    predioRepository.apagaPredio(id);
  }

  public List<Predio> listarTodos() {
    return predioRepository.encontreTodos();
  }

}
