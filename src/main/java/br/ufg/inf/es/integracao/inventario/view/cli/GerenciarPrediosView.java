package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorPredios;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GerenciarPrediosView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorPredios gerenciadorPredios;

  @Inject
  public GerenciarPrediosView(
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
          inicieCadastro();
          break;
        case 2: // Editar
          inicieAlterarPredio();
          break;
        case 3: // Apagar
          inicieApagarPredio();
          break;
        case 4: // Listar
          inicieListarPredios();
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
    out.println("- Gerenciamento de prédios:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar prédio");
    out.println("     2) Editar prédio");
    out.println("     3) Apagar prédio");
    out.println("     4) Listar prédio");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Cadastro de prédio");
    out.println();

    out.print("> Digite o nome do prédio: ");
    final String nome = in.nextLine();

    out.print("> Digite o id da unidade: ");
    final Long unidadeId = Long.parseLong(in.nextLine());

    gerenciadorPredios.cadastrarNovoPredio(nome, unidadeId);

    out.println();
    out.println("Prédio cadastrado com sucesso");
    out.println();
  }

  private void inicieListarPredios() {
    out.println();
    out.println("-----------");
    out.println("Lista de prédios");
    out.println();
    out.println("ID\tNome\tUnidade");
    out.println("----------------------------------------------");

    final List<Predio> predios = gerenciadorPredios.listarTodos();

    for (final Predio predio : predios) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          predio.getId(),
          predio.getNome(),
          predio.getNomeUnidade()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarPredio() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID do prédio a ser editado: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo nome do prédio: ");
    final String nome = in.nextLine();

    out.print("Digite a nova id da unidade do predio: ");
    final Long unidadeId = Long.parseLong(in.nextLine());

    gerenciadorPredios.atualizaPredio(id, nome, unidadeId);
    out.println("\nPrédio alterado com sucesso");
  }

  private void inicieApagarPredio() {
    out.println();
    out.println("-----------");
    out.println("Lista de prédios");
    out.println();
    out.println("ID\tNome\tUnidade");
    out.println("----------------------------------------------");

    final List<Predio> predios = gerenciadorPredios.listarTodos();

    for (final Predio predio : predios) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          predio.getId(),
          predio.getNome(),
          predio.getIdUnidade()
        )
      );
    }

    out.println();
    out.println("-----------");
    out.println();
    out.println("Apagar predio\n");

    out.print("> Digite o id do predio a ser apagado: ");
    final long id = Long.parseLong(in.nextLine());

    gerenciadorPredios.apagarPredioPorId(id);
    out.println("\nPrédio apagado com sucesso");
  }
}
