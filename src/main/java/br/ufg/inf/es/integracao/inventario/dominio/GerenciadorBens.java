package br.ufg.inf.es.integracao.inventario.dominio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.BemPatrimonial;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Departamento;
import br.ufg.inf.es.integracao.inventario.repositorio.BemPatrimonialRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 04/12/18.
 */
public class GerenciadorBens {
  @Inject
  private BemPatrimonialRepository bemPatrimonialRepository;

  public BemPatrimonial cadastrarNovoBemPatrimonial(
    final Date dataAquisicao,
    final String numeroTombamento,
    final String numeroNotaFiscal,
    final long vidautil,
    final String especificacao,
    final Date dataGarantia,
    final String marca,
    final String grupo,
    final BigDecimal valorCompra
  ) {
    final BemPatrimonial bemPatrimonial = new BemPatrimonial();
    bemPatrimonial.setDataAquisicao(dataAquisicao);
    bemPatrimonial.setDataGarantia(dataGarantia);
    bemPatrimonial.setEspecificacao(especificacao);
    bemPatrimonial.setMarca(marca);
    bemPatrimonial.setNumeroNotaFiscal(numeroNotaFiscal);
    bemPatrimonial.setNumeroTombamento(numeroTombamento);
    bemPatrimonial.setValorCompra(valorCompra);
    bemPatrimonial.setGrupo(grupo);
    bemPatrimonial.setVidaUtil(vidautil);

    bemPatrimonialRepository.inserirBemPatrimonial(bemPatrimonial);
    return bemPatrimonial;
  }

  public void apagarBemPorId(final int id) {
    bemPatrimonialRepository.deletarBemPatrimonial(id);
  }

  public List<BemPatrimonial> listarTodos() {
    return bemPatrimonialRepository.encontreTodos();
  }

  public void atualizaBem(
    final long id,
    final String numTombamento,
    final String numNotaFiscal,
    final String especificacao
  ) {
    final BemPatrimonial bem = new BemPatrimonial();
    bem.setNumeroTombamento(numTombamento);
    bem.setNumeroNotaFiscal(numNotaFiscal);
    bem.setEspecificacao(especificacao);

    bemPatrimonialRepository.atualize(id, bem);
  }
}

