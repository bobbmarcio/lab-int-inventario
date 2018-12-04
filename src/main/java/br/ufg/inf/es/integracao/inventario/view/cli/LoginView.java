package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.LoginException;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Scanner;

public class LoginView {

  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;
  private final MenuPrincipalView menuPrincipalView;

  @Inject
  public LoginView(
    final PrintStream out,
    final Scanner in,
    final ContextoSeguranca contextoSeguranca,
    final MenuPrincipalView menuPrincipalView
  ) {
    this.out = out;
    this.in = in;
    this.contextoSeguranca = contextoSeguranca;
    this.menuPrincipalView = menuPrincipalView;
  }

  public void inicie() {
    pergunteLogin();
  }

  private void pergunteLogin() {
    while (true) {
      String nome;
      String senha;

      out.println();
      out.println("Logue antes de continuar:");
      out.println();
      out.print("> Usuario: ");
      nome = in.nextLine();

      out.print("> Senha: ");
      senha = in.nextLine();

      try {
        contextoSeguranca.login(nome, senha);
        menuPrincipalView.inicie();
      } catch (LoginException e) {
        out.println();
        out.println(
          String.format("Erro ao logar: %s", e.getMessage())
        );
        out.println("Tente novamente");
      }
    }
  }
}
