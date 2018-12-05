package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.BemPatrimonial;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Departamento;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.OrdemServico;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BemPatrimonialRepository extends Repositorio {

  @Inject
  public BemPatrimonialRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public void inserirBemPatrimonial(final BemPatrimonial bem) {
    try {
      final PreparedStatement statement = prepareStatement(
       "INSERT INTO bem_patrimonial (data_aquisicao, numero_tombamento, numero_nota_fiscal, vida_util, " +
          "especificacao, data_garantia, marca, grupo, valor_compra, incorporado, localizacao_atual_id) " +
         "VALUES (now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)"
      );

      statement.setDate(1, (Date) bem.getDataAquisicao());
      statement.setString(2, bem.getNumeroTombamento());
      statement.setString(3, bem.getNumeroNotaFiscal());
      statement.setLong(4, bem.getVidaUtil());
      statement.setString(5, bem.getEspecificacao());
      statement.setDate(6, (Date) bem.getDataGarantia());
      statement.setString(7, bem.getMarca());
      statement.setString(8, String.valueOf(bem.getGrupo()));
      statement.setBigDecimal(9, bem.getValorCompra());

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deletarBemPatrimonial(int id)
  {
    try
    {
      final PreparedStatement statement = prepareStatement(
        "DELETE FROM bem_patrimonial WHERE id = ?"
      );

      statement.setLong(1, id);

      statement.execute();
    } catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public List<BemPatrimonial> encontreTodos() {
    try {
      final List<BemPatrimonial> bens = new LinkedList<>();

      iterateResultsOf(
        "SELECT * FROM bem_patrimonial",
        resultSet -> bens.add(new BemPatrimonial(resultSet))
      );

      return bens;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<BemPatrimonial> encontrePorId(final Long id) {
    try {
      final PreparedStatement statement = prepareStatement(
        "SELECT * FROM bem_patrimonial WHERE id = ?"
      );

      statement.setLong(1, id);

      return singleResult(statement, BemPatrimonial::new);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void atualize(final long idASerEditado, final BemPatrimonial bemPatrimonial) {
    try {
      final PreparedStatement statement = prepareStatement(
        "UPDATE bem_patrimonial SET numero_tombamento = ? , numero_nota_fiscal = ? , especificacao = ?"
          + "WHERE id = ?"
      );

      statement.setString(1, bemPatrimonial.getNumeroTombamento());
      statement.setString(2, bemPatrimonial.getNumeroNotaFiscal());
      statement.setString(3, bemPatrimonial.getEspecificacao());
      statement.setLong(4, idASerEditado);

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
