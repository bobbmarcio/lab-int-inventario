package br.ufg.inf.es.integracao.inventario.view.cli;

import br.ufg.inf.es.integracao.inventario.dominio.GerenciadorBens;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.BemPatrimonial;
import br.ufg.inf.es.integracao.inventario.dominio.seguranca.ContextoSeguranca;
import br.ufg.inf.es.integracao.inventario.infra.Util;

import javax.inject.Inject;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by aluno on 04/12/18.
 */
public class GerenciarBensView {
  private final PrintStream out;
  private final Scanner in;

  private final ContextoSeguranca contextoSeguranca;

  @Inject
  private GerenciadorBens gerenciadorBens;

  @Inject
  public GerenciarBensView(
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
        case 2: // Editar
          inicieAlterarBem();
          break;
        case 3: // Apagar
          inicieApagar();
          break;
        case 4: // Listar
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
    out.println("- Gerenciamento de bens patrimoniais:");
    out.println();
    out.println("  Opções:");
    out.println("     1) Cadastrar bem patrimonial");
    out.println("     2) Editar bem patrimonial");
    out.println("     3) Apagar bem patrimonial");
    out.println("     4) Listar bem patrimonial");
    out.println("    99) Voltar");
    out.println();
  }

  private void inicieCadastro() throws Exception {
    out.println();
    out.println("-----------");
    out.println("Cadastro de bem patrimonial");
    out.println();

    Locale us_ID = new Locale("en", "US");
    DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(us_ID);
    nf.setParseBigDecimal(true);

    final Date dataAq;
    final Date dataGarantia;
    out.print("> Digite a data de aquisicao: ");
    dataAq = Util.formataData(in.nextLine());

    out.print("> Digite o numero de tombamento: ");
    final String numTombamento = in.nextLine();

    out.print("> Digite o numero da nota fiscal: ");
    final String notaFiscal = in.nextLine();

    out.print("> Digite a vida util: ");
    final long vidaUtil = Long.parseLong(in.nextLine());

    out.print("> Digite a especificacao: ");
    final String especificacao = in.nextLine();

    out.print("> Digite a data de garantia: ");
    dataGarantia = Util.formataData(in.nextLine());
    out.print("> Digite a marca: ");

    final String marca = in.nextLine();
    out.print("> Digite o grupo: ");

    final String grupo = in.nextLine();
    out.print("> Digite o valor da compra: ");

    final BigDecimal bd = new BigDecimal(in.nextLine());

    gerenciadorBens.cadastrarNovoBemPatrimonial(
      dataAq, numTombamento, notaFiscal, vidaUtil,
      especificacao, dataGarantia, marca, grupo, bd
    );

    out.println();
    out.println("Bem patrimonial cadastrado com sucesso");
    out.println();
  }

  private void inicieListar() {
    out.println();
    out.println("-----------");
    out.println("Lista de bens patrimoniais");
    out.println();
    out.println("ID\tEspecificação\tNotaFiscal\tMarca\tTombamento");
    out.println("----------------------------------------------");

    final List<BemPatrimonial> bens = gerenciadorBens.listarTodos();

    for (final BemPatrimonial bem : bens) {
      out.println(
        String.format(
          "%d\t%s\t%s\t%s\t%s",
          bem.getId(),
          bem.getEspecificacao(),
          bem.getNumeroNotaFiscal(),
          bem.getMarca(),
          bem.getNumeroTombamento()
        )
      );
    }

    out.println("----------------------------------------------");
    out.println("Aperte qualquer tecla para continuar...");
    in.nextLine();
  }

  private void inicieAlterarBem() {
    out.println();
    out.println("-----------");
    out.println();

    out.print("Digite o ID do bem a ser editado: ");
    final int id = Integer.parseInt(in.nextLine());

    out.print("Digite o novo número de tombamento: ");
    final String numeroTomb = in.nextLine();

    out.print("Digite o novo número da nota fiscal: ");
    final String notaFiscal = in.nextLine();

    out.print("Digite a nova especificação: ");
    final String especificacao = in.nextLine();

    gerenciadorBens.atualizaBem(id, numeroTomb, notaFiscal, especificacao);
  }

  private void inicieApagar() {
    out.println();
    out.println("-----------");
    out.println("Lista de bens patrimoniais");
    out.println();
    out.println("ID\tNome\tUnidade");
    out.println("----------------------------------------------");

    final List<BemPatrimonial> bens = gerenciadorBens.listarTodos();

    for (final BemPatrimonial bem : bens) {
      out.println(
        String.format(
          "%d\t%s\t%s",
          bem.getId(),
          bem.getDataAquisicao(),
          bem.getEspecificacao()
        )
      );
    }

    out.println();
    out.println("-----------");
    out.println();
    out.println("Apagar bem patrimonial\n");

    out.print("> Digite o id do bem patrimonial a ser apagado: ");
    final int id = Integer.parseInt(in.nextLine());

    gerenciadorBens.apagarBemPorId(id);
    out.println("\nBem patrimonial apagado com sucesso");
  }
}
