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
  private GerenciarPrediosView gerenciarPrediosView;

  @Inject
  private GerenciarSalasView gerenciarSalasView;

  @Inject
  private GerenciarDepartamentosView gerenciarDepartamentosView;

  @Inject
  private GerenciarOrdemServicosView gerenciarOrdemServicosView;

  @Inject
  private GerenciarUnidadesView gerenciarUnidadesView;

  @Inject
  private GerenciarBaixasView gerenciarBaixasView;

  @Inject
  private GerenciarBensView gerenciarBensView;

  @Inject
  private RelatorioInventarioView gerenciarInventarioView;

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

  public void inicie() throws Exception {
    if (!contextoSeguranca.estaLogado()) {
      throw new RuntimeException("Apenas usuários logados podem ver o menu principal");
    }

    pergunteMenu();
  }

  private void pergunteMenu() throws Exception {
    boolean saiu = false;

    while (!saiu) {
      printMenu();

      out.print("Digite o número da opção: ");
      final int escolha = Integer.parseInt(in.nextLine());

      switch (escolha) {
        case 1: // Gerenciar usuários
          gerenciarUsuariosView.inicie();
          break;

        case 2: // Gerenciar prédios
          gerenciarPrediosView.inicie();
          break;

        case 3: // Gerenciar salas
          gerenciarSalasView.inicie();
          break;

        case 4: //Gerenciar Departamentos
          gerenciarDepartamentosView.inicie();
          break;

        case 5:
          gerenciarUnidadesView.inicie();
          break;

        case 6:
          gerenciarOrdemServicosView.inicie();
          break;

        case 7:
          gerenciarBensView.inicie();
          break;

        case 8:
          gerenciarBaixasView.inicie();
          break;

        case 9:
          gerenciarInventarioView.inicie();
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
    out.println();
    out.println("- Menu principal");
    out.println();
    out.println("  Opções:");
    out.println("     1) Gerenciar usuários");
    out.println("     2) Gerenciar prédios");
    out.println("     3) Gerenciar salas");
    out.println("     4) Gerenciar departamentos");
    out.println("     5) Gerenciar unidades");
    out.println("     6) Registrar ordem de serviço");
    out.println("     7) Gerenciar bem patrimonial");
    out.println("     8) Baixar bem patrimonial");
    out.println("     9) Relatório de inventário");
    out.println("    99) Sair");
    out.println();
  }
}
