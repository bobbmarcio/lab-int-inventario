package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.view.Aplicacao;

import javax.inject.Inject;

public class AplicacaoCli implements Aplicacao<String[]> {

  @Inject
  public AplicacaoCli() {
  }

  @Override
  public void run(final String[] parametros) {
    // TODO: Implementar aplicação
  }

}
