package br.ufg.inf.es.integracao.inventario;

import br.ufg.inf.es.integracao.inventario.config.ModuloAplicacao;
import br.ufg.inf.es.integracao.inventario.view.Aplicacao;

public class RunnerAplicacao {

    static void main(final String[] args) {
        final ModuloAplicacao modulo = new ModuloAplicacao();
        final Aplicacao<String[]> app = modulo.construirAplicacao();

        app.run(args);
    }

}
