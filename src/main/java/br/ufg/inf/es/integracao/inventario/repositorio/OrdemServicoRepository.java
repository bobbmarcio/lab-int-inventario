package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.OrdemServico;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Supplier;

public class OrdemServicoRepository extends Repositorio {

  @Inject
  public OrdemServicoRepository(final Supplier<Connection> connectionSupplier) {
    super(connectionSupplier);
  }

  public void inserirOrdemServico(final OrdemServico ordemServico) {
    try {
      final PreparedStatement statement = prepareStatement(
        "INSERT INTO ordem_servico (id, data_criacao, situacao, inicio_servico, fim_servico, responsavel_id, bem_patrimonial_id) VALUES ((SELECT nextval('ordem_servico_id_seq')), now(), 'ABERTA', null, null, ?, ?)"
      );

      statement.setLong(1, ordemServico.getResponsavelId());
      statement.setLong(2, ordemServico.getBemPatrimonialId());

      statement.execute();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
