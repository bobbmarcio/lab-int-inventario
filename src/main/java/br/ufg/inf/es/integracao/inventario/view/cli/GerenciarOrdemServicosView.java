package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorOrdemServico;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Scanner;

public class GerenciarOrdemServicosView {
  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorOrdemServico gerenciadorOrdemServico;

  @Inject
  public GerenciarOrdemServicosView(
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
        case 1: // Registrar Ordem de Serviço
          inicieCadastro();
          break;
        case 99: // Sair
          saiu = true;
          break;

        default:
          out.println("Opção não reconhecida");
      }
    }
  }

  private void printOpcoes() {
    out.println("- Gerenciamento de ordens de serviço:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Registrar ordem de serviço");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Registro de ordem de serviço");
    out.println();

    out.print("> Digite o id do responsável: ");
    final Long responsavelId = Long.parseLong(in.nextLine());

    out.print("> Digite o id do bem patrimonial: ");
    final Long bemPatrimonialId = Long.parseLong(in.nextLine());

    gerenciadorOrdemServico.cadastrarNovaOrdemServico(responsavelId, bemPatrimonialId);

    out.println();
    out.println("Ordem de serviço registrada com sucesso");
    out.println();
  }
}
