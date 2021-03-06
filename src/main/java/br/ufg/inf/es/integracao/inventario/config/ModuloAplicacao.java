package br.ufg.inf.es.integracao.inventario.config;

import br.ufg.inf.es.integracao.inventario.config.database.ConexaoProvider;
import br.ufg.inf.es.integracao.inventario.config.parametros.Parametros;
import br.ufg.inf.es.integracao.inventario.config.parametros.ParametrosDesenvolvimento;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;
import br.ufg.inf.es.integracao.inventario.view.cli.AplicacaoCli;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Módulo de injeção das classes da aplicação.
 */
public class ModuloAplicacao extends AbstractModule {

  private final Ambiente ambiente;

  /**
   * Inicia o módulo.
   */
  public ModuloAplicacao(final Ambiente ambiente) {
    this.ambiente = ambiente;
  }

  /**
   * Configura e amarra as interfaces e classes necessárias para execução da
   * aplicação.
   */
  @Override
  protected void configure() {
    bind(new TypeLiteral<Aplicacao<String[]>>() {}).to(AplicacaoCli.class);
    bind(new TypeLiteral<Supplier<Connection>>() {}).to(ConexaoProvider.class);

    bind(new TypeLiteral<Collection<HookInicioAplicacao>>() {})
      .toProvider(ProviderHookInicioAplicacao.class);

    bind(Ambiente.class).toProvider(() -> ambiente);
    bind(Scanner.class).toProvider(() -> new Scanner(System.in));
    bind(PrintStream.class).toProvider(() -> System.out);

    if (ambiente == Ambiente.DESENVOLVIMENTO) {
      bind(Parametros.class).to(ParametrosDesenvolvimento.class);
    }
  }

}
