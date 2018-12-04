package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.entidades.Usuario;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import br.ufg.inf.es.integracao.inventario.repositorio.RepositorioUsuario;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Scanner;

public class GerenciarUsuariosView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private RepositorioUsuario repositorioUsuario;

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
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() {
    out.println();
    out.println("Cadastro de usuário");
    out.println();

    out.print("  Digite o nome do usuário:");
    final String nome = in.nextLine();

    out.print("  Digite a senha do usuário:");
    final String senha = in.nextLine();

    out.print("  Digite o email do usuário:");
    final String email = in.nextLine();

    final Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(senha);

    repositorioUsuario.inserirUsuario(usuario);

    out.println("Usuário cadastrado com sucesso");
    out.println();
  }
}
