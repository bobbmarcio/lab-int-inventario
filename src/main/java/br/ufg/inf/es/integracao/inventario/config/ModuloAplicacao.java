package br.ufg.inf.es.integracao.inventario.config;

import br.ufg.inf.es.integracao.inventario.config.database.ConexaoProvider;
import br.ufg.inf.es.integracao.inventario.config.parametros.Parametros;
import br.ufg.inf.es.integracao.inventario.config.parametros.ParametrosDesenvolvimento;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;
import br.ufg.inf.es.integracao.inventario.view.cli.AplicacaoCli;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import javax.inject.Provider;
import java.sql.Connection;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Módulo de injeção das classes da aplicação.
 */
public class ModuloAplicacao extends AbstractModule {

  /**
   * Inicia o módulo.
   */
  public ModuloAplicacao() {
  }

  /**
   * Configura e amarra as interfaces e classes necessárias para execução da
   * aplicação.
   */
  @Override
  protected void configure() {
    bind(new TypeLiteral<Aplicacao<String[]>>() {}).to(AplicacaoCli.class);
    bind(Parametros.class).to(ParametrosDesenvolvimento.class);
    bind(new TypeLiteral<Supplier<Connection>>() {}).to(ConexaoProvider.class);
    bind(new TypeLiteral<Collection<HookInicioAplicacao>>() {})
      .toProvider(ProviderHookInicioAplicacao.class);
  }

}
