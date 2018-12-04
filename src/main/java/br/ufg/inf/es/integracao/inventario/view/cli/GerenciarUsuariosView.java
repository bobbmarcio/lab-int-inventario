package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUsuarios;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GerenciarUsuariosView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorUsuarios gerenciadorUsuarios;

  @Inject
  public GerenciarUsuariosView(
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
        case 3: // Apagar
        case 4: // Listar
          inicieListarUsuarios();
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
    out.println("- Gerenciamento de usuários:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar usuário");
    out.println("     2) Editar usuário");
    out.println("     3) Apagar usuário");
    out.println("     4) Listar usuários");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("-----------");
    out.println("Cadastro de usuário");
    out.println();

    out.print("> Digite o nome do usuário: ");
    final String nome = in.nextLine();

    out.print("> Digite a senha do usuário: ");
    final String senha = in.nextLine();

    out.print("> Digite o email do usuário: ");
    final String email = in.nextLine();

    gerenciadorUsuarios.cadastrarNovoUsuario(nome, email, senha);

    out.println();
    out.println("Usuário cadastrado com sucesso");
    out.println();
  }

  private void inicieListarUsuarios() {
    out.println();
    out.println("-----------");
    out.println("Lista de usuários");
    out.println();
    out.println("ID\tNome\tEmail");
    out.println("----------------------------------------------");

    final List<Usuario> usuarios = gerenciadorUsuarios.listarTodos();

    for (final Usuario usuario : usuarios) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          usuario.getId(),
          usuario.getNome(),
          usuario.getEmail()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarUsuario() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID do usuário a ser editado: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo nome do usuário: ");
    final String nome = in.nextLine();

    out.print("Digite a nova senha do usuário: ");
    final String senha = in.nextLine();

    out.print("Digite o novo email do usuário: ");
    final String email = in.nextLine();

    gerenciadorUsuarios.atualizaUsuario(id, nome, senha, email);
    out.println("\nUsuário alterado com sucesso");
  }
}
