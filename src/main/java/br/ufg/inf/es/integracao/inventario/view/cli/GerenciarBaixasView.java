package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorBaixa;
import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorUsuarios;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.BaixaBem;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import br.ufg.inf.es.integracao.inventario.infra.Util;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aluno on 04/12/18.
 */
public class GerenciarBaixasView {
  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorBaixa gerenciadorBaixa;

  @Inject
  public GerenciarBaixasView(
    final PrintStream out,
    final Scanner in,
    final ContextoSeguranca contextoSeguranca
  ) {
    this.out = out;
    this.in = in;
    this.contextoSeguranca = contextoSeguranca;
  }

  public void inicie() throws Exception {
    boolean saiu = false;

    while (!saiu) {
      printOpcoes();
      out.print("  Digite a opção: ");

      final int escolha = Integer.parseInt(in.nextLine());

      switch (escolha) {
        case 1: // Cadastrar
          inicieCadastro();
          break;
        case 2: // Apagar
          inicieApagar();
          break;
        case 3: // Listar
          inicieListar();
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
    out.println("     1) Dar baixa em um bem");
    out.println("     2) Cancelar baixa");
    out.println("     3) Listar baixas");
    out.println("     4) ");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() throws Exception {
    out.println();
    out.println("-----------");
    out.println("Cadastro de baixa de bem patrimonial:");
    out.println();

    out.print("> Digite a data da baixa: ");
    final Date data = Util.formataData(in.nextLine());

    out.print("> Digite o comentario: ");
    final String comentario = in.nextLine();

    out.print("> Digite o numero do bem patrimonial referente: ");
    final int id = Integer.parseInt(in.nextLine());

    gerenciadorBaixa.cadastrarNovaBaixa(data,comentario,id);

    out.println();
    out.println("Baixa cadastrada com sucesso");
    out.println();
  }
//  CREATE TABLE IF NOT EXISTS baixa (
//    id                 BIGSERIAL PRIMARY KEY,
//	"data"             DATE,
//    comentario         TEXT,
//    bem_patrimonial_id BIGINT REFERENCES bem_patrimonial
//  );
  private void inicieListar() {
    out.println();
    out.println("-----------");
    out.println("Lista de baixas");
    out.println();
    out.println("ID\tData\tComentario\tBem");
    out.println("----------------------------------------------");

    final List<BaixaBem> baixas = gerenciadorBaixa.listarTodos();

    for (final BaixaBem baixa : baixas) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%d",
          baixa.getId(),
          baixa.getData().toString(),
          baixa.getComentario(),
          baixa.getBemPatrimonialId()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }


  private void inicieApagar() {
    out.println();
    out.println("-----------");
    out.println();
    out.println("Cancelar baixa do bem: \n");

    out.print("> Digite o id da baixa a ser apagada: ");
    final int id = Integer.parseInt(in.nextLine());

    gerenciadorBaixa.apagarBemPorId(id);
    out.println("\nBaixa apagada com sucesso");
  }
}
