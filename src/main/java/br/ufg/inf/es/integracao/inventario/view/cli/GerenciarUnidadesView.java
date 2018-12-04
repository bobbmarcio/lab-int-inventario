package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUnidades;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GerenciarUnidadesView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorUnidades gerenciadorUnidades;

  @Inject
  public GerenciarUnidadesView(
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
          inicieAlterarUnidade();
          break;
        case 3: // Apagar
          inicieApagarUnidade();
          break;
        case 4: // Listar
          inicieListarUnidades();
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
    out.println("- Gerenciamento de unidades:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar unidade");
    out.println("     2) Editar unidade");
    out.println("     3) Apagar unidade");
    out.println("     4) Listar unidade");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Cadastro de unidade");
    out.println();

    out.print("> Digite o nome da unidade: ");
    final String nome = in.nextLine();

    out.print("> Digite o endereço da unidade: ");
    final String endereco = in.nextLine();

    out.print("> Digite a cidade da unidade: ");
    final String cidade = in.nextLine();

    out.print("> Digite a UF da unidade: ");
    final String uf = in.nextLine();

    out.print("> Digite o CEP da unidade: ");
    final String cep = in.nextLine();

    gerenciadorUnidades.cadastrarNovaUnidade(nome, endereco, cidade, uf , cep);

    out.println();
    out.println("Unidade cadastrada com sucesso");
    out.println();
  }

  private void inicieListarUnidades() {
    out.println();
    out.println("-----------");
    out.println("Lista de unidades");
    out.println();
    out.println("ID\tNome\tEndereço\tCidade\tUF\tCEP");
    out.println("----------------------------------------------");

    final List<Unidade> unidades = gerenciadorUnidades.listarTodos();

    for (final Unidade unidade : unidades) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%s\t%s\t%s",
          unidade.getId(),
          unidade.getNome(),
          unidade.getEndereco(),
          unidade.getCidade(),
          unidade.getUf(),
          unidade.getCep()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarUnidade() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID da unidade a ser editada: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo nome da unidade: ");
    final String nome = in.nextLine();

    out.print("Digite o novo endereço da unidade: ");
    final String endereco = in.nextLine();

    out.print("Digite a nova cidade da unidade: ");
    final String cidade = in.nextLine();

    out.print("Digite o novo UF da unidade: ");
    final String uf = in.nextLine();

    out.print("Digite o novo CEP da unidade: ");
    final String cep = in.nextLine();

    gerenciadorUnidades.atualizaUnidade(id, nome, endereco, cidade, uf, cep);
    out.println("\nUnidade alterada com sucesso");
  }

  private void inicieApagarUnidade() {
    out.println();
    out.println("-----------");
    out.println("Lista de unidades");
    out.println();
    out.println("ID\tNome\tEndereço\tCidade\tUF\tCEP");
    out.println("----------------------------------------------");

    final List<Unidade> unidades = gerenciadorUnidades.listarTodos();

    for (final Unidade unidade : unidades) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%s\t%s\t%s",
          unidade.getId(),
          unidade.getNome(),
          unidade.getEndereco(),
          unidade.getCidade(),
          unidade.getUf(),
          unidade.getCep()
        )
      );
    }

    out.println();
    out.println("-----------");
    out.println();
    out.println("Apagar unidade\n");

    out.print("> Digite o id da unidade a ser apagada: ");
    final long id = Long.parseLong(in.nextLine());

    gerenciadorUnidades.apagarUnidadePorId(id);
    out.println("\nUnidade apagado com sucesso");
  }
}
