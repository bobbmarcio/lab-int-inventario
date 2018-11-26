package br.ufg.inf.es.integracao.inventario;

import br.ufg.inf.es.integracao.inventario.config.ModuloAplicacao;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

public class RunnerAplicacao {

  public static void main(final String[] args) {
    final Injector injector = Guice.createInjector(new ModuloAplicacao());

    final Aplicacao<String[]> app = injector.getInstance(
      Key.get(new TypeLiteral<Aplicacao<String[]>>() {})
    );

    app.run(args);
  }

}
