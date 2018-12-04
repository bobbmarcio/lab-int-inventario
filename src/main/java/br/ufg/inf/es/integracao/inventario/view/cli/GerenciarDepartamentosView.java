package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorDepartamentos;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Departamento;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GerenciarDepartamentosView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorDepartamentos gerenciadorDepartamentos;

  @Inject
  public GerenciarDepartamentosView(
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
          inicieAlterarDepartamento();
          break;
        case 3: // Apagar
          inicieApagarDepartamento();
          break;
        case 4: // Listar
          inicieListarDepartamentos();
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
    out.println("- Gerenciamento de departamentos:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar departamento");
    out.println("     2) Editar departamento");
    out.println("     3) Apagar departamento");
    out.println("     4) Listar departamento");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Cadastro de departamento");
    out.println();

    out.print("> Digite o nome do departamento: ");
    final String nome = in.nextLine();

    out.print("> Digite o id do chefe: ");
    final Long chefe = Long.parseLong(in.nextLine());

    gerenciadorDepartamentos.cadastrarNovoDepartamento(nome, chefe);

    out.println();
    out.println("Departamento cadastrado com sucesso");
    out.println();
  }

  private void inicieListarDepartamentos() {
    out.println();
    out.println("-----------");
    out.println("Lista de departamentos");
    out.println();
    out.println("ID\tNome\tUnidade");
    out.println("----------------------------------------------");

    final List<Departamento> departamentos = gerenciadorDepartamentos.listarTodos();

    for (final Departamento departamento : departamentos) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          departamento.getId(),
          departamento.getNome(),
          departamento.getChefe()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarDepartamento() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID do departamento a ser editado: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo nome do departamento: ");
    final String nome = in.nextLine();

    out.print("Digite a nova id do chefe do departamento: ");
    final Long chefe = Long.parseLong(in.nextLine());

    gerenciadorDepartamentos.atualizaDepartamento(id, nome, chefe);
    out.println("\nDepartamento alterado com sucesso");
  }

  private void inicieApagarDepartamento() {
    out.println();
    out.println("-----------");
    out.println("Lista de departamentos");
    out.println();
    out.println("ID\tNome\tUnidade");
    out.println("----------------------------------------------");

    final List<Departamento> departamentos = gerenciadorDepartamentos.listarTodos();

    for (final Departamento departamento : departamentos) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          departamento.getId(),
          departamento.getNome(),
          departamento.getChefe()
        )
      );
    }

    out.println();
    out.println("-----------");
    out.println();
    out.println("Apagar departamento\n");

    out.print("> Digite o id do departamento a ser apagado: ");
    final long id = Long.parseLong(in.nextLine());

    gerenciadorDepartamentos.apagarDepartamentoPorId(id);
    out.println("\nDepartamento apagado com sucesso");
  }
}
