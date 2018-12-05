package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.BaixaBem;
import br.ufg.inf.es.integracao.inventario.repositorio.BaixaBemRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class GerenciadorBaixa {
  @Inject
  private BaixaBemRepository baixaBemRep;

  public BaixaBem cadastrarNovaBaixa(
    final Date data,
    final String comentario,
    final int bemPatrimonialId
  ) {
    final BaixaBem baixa = new BaixaBem();
    baixa.setComentario(comentario);
    baixa.setData(data);
    baixa.setBemPatrimonialId(bemPatrimonialId);

    baixaBemRep.inserirBaixaBem(baixa);
    return baixa;
  }

  public void apagarBemPorId(final int id) {
    baixaBemRep.deletarBaixaBem(id);
  }

  public List<BaixaBem> listarTodos() {
    return baixaBemRep.encontreTodos();
  }
}
