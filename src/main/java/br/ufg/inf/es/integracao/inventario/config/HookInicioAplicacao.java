package br.ufg.inf.es.integracao.inventario.config;

/**
 * Função para ser executada no início de cada aplicação.
 */
public interface HookInicioAplicacao {

  /**
   * Executa o hook da aplicação.
   */
  void executar();

}
