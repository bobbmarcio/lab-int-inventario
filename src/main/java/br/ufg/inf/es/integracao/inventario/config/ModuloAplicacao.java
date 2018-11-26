package br.ufg.inf.es.integracao.inventario.config;

import br.ufg.inf.es.integracao.inventario.view.Aplicacao;
import br.ufg.inf.es.integracao.inventario.view.cli.AplicacaoCli;

public class ModuloAplicacao {

    public ModuloAplicacao() {
    }

    public Aplicacao<String[]> construirAplicacao() {
        return new AplicacaoCli();
    }

}
