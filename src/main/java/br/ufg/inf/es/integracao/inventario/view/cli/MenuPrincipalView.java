package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuPrincipalView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciarUsuariosView gerenciarUsuariosView;

  @Inject
  public MenuPrincipalView(
    PrintStream out,
    Scanner in,
    ContextoSeguranca contextoSeguranca
  ) {
    this.out = out;
    this.in = in;
    this.contextoSeguranca = contextoSeguranca;
  }

  public void inicie() {
    if (!contextoSeguranca.estaLogado()) {
      throw new RuntimeException("Apenas usuários logados podem ver o menu principal");
    }

    pergunteMenu();
  }

  private void pergunteMenu() {
    boolean saiu = false;

    while (!saiu) {
      printMenu();

      out.print("Digite o número da opção: ");
      final int escolha = Integer.parseInt(in.nextLine());

      switch (escolha) {
        case 1: // Gerenciar usuários
          gerenciarUsuariosView.inicie();
          break;
        case 99: // Sair
          saiu = true;
          break;

        default:
          out.println(
            String.format("Opção %d não reconhecida", escolha)
          );
      }

      out.println();
    }
  }

  private void printMenu() {
    out.println("- Menu principal");
    out.println();
    out.println("  Opções:");
    out.println("     1) Gerenciar usuários");
    out.println("    99) Sair");
    out.println();
  }
}
