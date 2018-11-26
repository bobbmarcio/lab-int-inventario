
package br.ufg.inf.es.integracao.inventario.view.cli;

import org.junit.Before;
import org.junit.Test;

public class AplicacaoCliTest {

    private AplicacaoCli aplicacao;

    @Before
    public void inicializa() {
        aplicacao = new AplicacaoCli();
    }

    @Test
    public void deveExecutarSemErrosComParametrosNulos() {
        aplicacao.run(null);
    }

    @Test
    public void deveExecutarSemErrosComParametrosVazios() {
        aplicacao.run(new String[0]);
    }

}
