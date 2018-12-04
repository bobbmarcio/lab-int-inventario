package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorSalas;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Sala;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GerenciarSalasView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorSalas gerenciadorSalas;

  @Inject
  public GerenciarSalasView(
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
          inicieAlterarSala();
          break;
        case 3: // Apagar
          inicieApagarSala();
          break;
        case 4: // Listar
          inicieListarSalas();
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
    out.println("- Gerenciamento de salas:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar sala");
    out.println("     2) Editar sala");
    out.println("     3) Apagar sala");
    out.println("     4) Listar sala");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Cadastro de sala");
    out.println();

    out.print("> Digite o nome da sala: ");
    final String nome = in.nextLine();

    out.print("> Digite o código da sala: ");
    final String codigo = in.next();

    out.print("> Digite o id do prédio: ");
    final Long predioId = Long.parseLong(in.nextLine());

    out.print("> Digite o id do departamento: ");
    final Long departamentoId = Long.parseLong(in.nextLine());

    gerenciadorSalas.cadastrarNovaSala(nome, codigo, predioId, departamentoId);

    out.println();
    out.println("Sala cadastrada com sucesso");
    out.println();
  }

  private void inicieListarSalas() {
    out.println();
    out.println("-----------");
    out.println("Lista de salas");
    out.println();
    out.println("ID\tNome\tCódigo\tID do prédio\tID do departamento");
    out.println("----------------------------------------------");

    final List<Sala> salas = gerenciadorSalas.listarTodos();

    for (final Sala sala : salas) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%s\t%s",
          sala.getId(),
          sala.getNome(),
          sala.getCodigo(),
          sala.getPredioId(),
          sala.getDepartamentoId()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarSala() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID da sala a ser editado: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo nome do sala: ");
    final String nome = in.nextLine();

    out.print("Digite o novo código da sala: ");
    final String codigo = in.nextLine();

    out.print("Digite o novo id do prédio da sala: ");
    final Long predioId = Long.parseLong(in.nextLine());

    out.print("Digite o novo id do departamento da sala: ");
    final Long departamentoId = Long.parseLong(in.nextLine());

    gerenciadorSalas.atualizaSala(id, nome, codigo, predioId, departamentoId);
    out.println("\nSala alterada com sucesso");
  }

  private void inicieApagarSala() {
    out.println();
    out.println("-----------");
    out.println("Lista de salas");
    out.println();
    out.println("ID\tNome\tCódigo\tID do prédio\tID do departamento");
    out.println("----------------------------------------------");

    final List<Sala> salas = gerenciadorSalas.listarTodos();

    for (final Sala sala : salas) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%s\t%s",
          sala.getId(),
          sala.getNome(),
          sala.getCodigo(),
          sala.getPredioId(),
          sala.getDepartamentoId()
        )
      );
    }

    out.println();
    out.println("-----------");
    out.println();
    out.println("Apagar sala\n");

    out.print("> Digite o id da sala a ser apagada: ");
    final long id = Long.parseLong(in.nextLine());

    gerenciadorSalas.apagarSalaPorId(id);
    out.println("\nSala apagado com sucesso");
  }
}
