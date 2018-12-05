package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.BemPatrimonial;
import br.ufg.inf.es.integracao.inventario.repositorio.BemPatrimonialRepository;
import br.ufg.inf.es.integracao.inventario.repositorio.OrdemServicoRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by aluno on 05/12/18.
 */
public class GerenciadorInventario {
  @Inject
  private BemPatrimonialRepository bemPatrimonialRepository;

  public List<BemPatrimonial> listarTodos() {
    return bemPatrimonialRepository.encontreTodos();
  }
}
