package br.ufg.inf.es.integracao.inventario.view;

import br.ufg.inf.es.integracao.inventario.config.HookInicioAplicacao;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

/**
 * Representa o ponto de início da execução de uma aplicação.
 *
 * @param <P> Estrutura que carrega os parametros necessários para a aplicação.
 *            Caso não haja nenhum parametro, declarar como {@link java.lang.Void}
 */
public abstract class Aplicacao<P> {

  private final Collection<HookInicioAplicacao> hooksInicio;

  @Inject
  protected Aplicacao(final Collection<HookInicioAplicacao> hooksInicio) {
    this.hooksInicio = hooksInicio;
  }

  /**
   * Executa a aplicação. Geralmente deverá ser executada apenas uma vez, mas
   * não se faz obrigatório por contrato, segurança contra concorrência
   * também não garantida.
   *
   * @param parametros Parametros usados na configuração da aplicação, a
   *                   implementação deve ser segura contra argumentos nulos.
   */
  public void run(final P parametros) {
    rodarHooks();
  }

  @PostConstruct
  private void rodarHooks() {
    for (final HookInicioAplicacao hook : hooksInicio) {
      hook.executar();
    }
  }

}
