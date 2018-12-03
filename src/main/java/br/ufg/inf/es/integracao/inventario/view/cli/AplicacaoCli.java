package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.config.HookInicioAplicacao;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;

import javax.inject.Inject;
import java.util.Collection;

public class AplicacaoCli extends Aplicacao<String[]> {

  @Inject
  public AplicacaoCli(Collection<HookInicioAplicacao> hooksInicio) {
    super(hooksInicio);
  }

  @Override
  public void run(final String[] parametros) {
    super.run(parametros);
    // TODO: Implementar aplicação
  }

}
