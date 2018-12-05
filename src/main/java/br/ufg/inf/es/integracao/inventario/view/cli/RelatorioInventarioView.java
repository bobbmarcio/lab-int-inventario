package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorInventario;
import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUsuarios;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.BemPatrimonial;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aluno on 05/12/18.
 */
public class RelatorioInventarioView {
  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorInventario gerenciador;

  @Inject
  public RelatorioInventarioView(
    final PrintStream out,
    final Scanner in,
    final ContextoSeguranca contextoSeguranca
  ) {
    this.out = out;
    this.in = in;
    this.contextoSeguranca = contextoSeguranca;
  }

  public void inicie() {
    boolean saiu = false;

    while (!saiu) {
      printOpcoes();
      out.print("  Digite a opção: ");

      final int escolha = Integer.parseInt(in.nextLine());

      switch (escolha) {
        case 1: // Cadastrar
          imprimirRelatorio();
          break;
        case 2: // Editar
          imprimirRelatorioEmpresa();
          break;
        case 99:
          saiu = true;
          break;
      }
    }
  }

  private void printOpcoes() {
    out.println("- Relatório de inventário:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Simples");
    out.println("     2) Detalhado");
    out.println("    99) Voltar");
    out.println();
  }

  private void imprimirRelatorio() {
    imprimirRelatorioEmpresa();
  }

  private void imprimirRelatorioEmpresa() {
    out.println();
    out.println("-----------");
    out.println("Relatório do inventário da empresa do usuário:");
    out.println();
    out.println("N. tombamento\tN. nota fiscal\tVida útil\t Valor compra\t Marca");
    out.println("----------------------------------------------");

    final List<BemPatrimonial> bens = gerenciador.listarTodos();

    for (final BemPatrimonial bem : bens) {
      out.println(
        String.format(
          "%s\t%s\t%d\t%f\t%s",
          bem.getNumeroTombamento(),
          bem.getNumeroNotaFiscal(),
          bem.getVidaUtil(),
          bem.getValorCompra(),
          bem.getMarca()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }
}
