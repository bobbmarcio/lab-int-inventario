package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.OrdemServico;
import br.ufg.inf.es.integracao.inventario.repositorio.OrdemServicoRepository;

import javax.inject.Inject;

public class GerenciadorOrdemServico {

  @Inject
  private OrdemServicoRepository ordemServicoRepository;

  public OrdemServico cadastrarNovaOrdemServico(
    final Long responsavelId,
    final Long bemPatrimonialId
  ) {
    final OrdemServico ordemServico = new OrdemServico();
    ordemServico.setResponsavelId(responsavelId);
    ordemServico.setBemPatrimonialId(bemPatrimonialId);

    ordemServicoRepository.inserirOrdemServico(ordemServico);
    return ordemServico;
  }
}
