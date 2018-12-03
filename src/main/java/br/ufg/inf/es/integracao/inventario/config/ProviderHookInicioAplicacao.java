package br.ufg.inf.es.integracao.inventario.config;

import br.ufg.inf.es.integracao.inventario.config.database.HookExecutarDdl;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collection;
import java.util.Collections;

public class ProviderHookInicioAplicacao
  implements Provider<Collection<HookInicioAplicacao>> {

  private final HookExecutarDdl hookDdl;

  @Inject
  public ProviderHookInicioAplicacao(final HookExecutarDdl hookDdl) {
    this.hookDdl = hookDdl;
  }

  @Override
  public Collection<HookInicioAplicacao> get() {
    return Collections.singleton(hookDdl);
  }
}
