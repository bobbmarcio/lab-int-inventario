package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.config.HookInicioAplicacao;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;

import javax.inject.Inject;
import java.util.Collection;

public class AplicacaoCli extends Aplicacao<String[]> {

  private final LoginView loginView;

  @Inject
  public AplicacaoCli(
    final Collection<HookInicioAplicacao> hooksInicio,
    final LoginView loginView
  ) {
    super(hooksInicio);
    this.loginView = loginView;
  }

  @Override
  public void run(final String[] parametros) {
    super.run(parametros);
    loginView.inicie();
  }

}
