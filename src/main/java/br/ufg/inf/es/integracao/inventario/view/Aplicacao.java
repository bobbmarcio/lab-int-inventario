package br.ufg.inf.es.integracao.inventario.view;

/**
 * Representa o ponto de início da execução de uma aplicação.
 *
 * @param <P> Estrutura que carrega os parametros necessários para a aplicação.
 *            Caso não haja nenhum parametro, declarar como {@link java.lang.Void}
 */
public interface Aplicacao<P> {

  /**
   * Executa a aplicação. Geralmente deverá ser executada apenas uma vez, mas
   * não se faz obrigatório por contrato, segurança contra concorrência
   * também não garantida.
   *
   * @param parametros Parametros usados na configuração da aplicação, a
   *                   implementação deve ser segura contra argumentos nulos.
   */
  void run(final P parametros);

}
